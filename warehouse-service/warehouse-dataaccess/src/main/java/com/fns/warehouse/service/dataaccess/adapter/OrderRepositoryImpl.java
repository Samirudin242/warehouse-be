package com.fns.warehouse.service.dataaccess.adapter;

import com.fns.warehouse.service.dataaccess.entity.*;
import com.fns.warehouse.service.dataaccess.mapper.OrderDataAccessMapper;
import com.fns.warehouse.service.dataaccess.mapper.SalesReportDataAccessMapper;
import com.fns.warehouse.service.dataaccess.repository.*;
import com.fns.warehouse.service.domain.dto.create.*;
import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.dto.response.ShippingOrderResponse;
import com.fns.warehouse.service.domain.dto.response.UpdateStockResponse;
import com.fns.warehouse.service.domain.dto.response.UploadPaymentResponse;
import com.fns.warehouse.service.domain.entity.OrderStatus;
import com.fns.warehouse.service.domain.ports.output.repository.OrderRepository;
import enitity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import java.io.IOException;
import java.util.*;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final PaymentJpaRepository paymentJpaRepository;
    private final StockJpaRepository stockJpaRepository;
    private final SalesJpaReportEntity salesJpaReportEntity;
    private final StockMutationJpaRepository stockMutationJpaRepository;

    private final OrderDataAccessMapper orderDataAccessMapper;
    private final SalesReportDataAccessMapper salesReportDataAccessMapper;
    private final Storage storage;
    private final String bucketName;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository, OrderItemJpaRepository orderItemJpaRepository, PaymentJpaRepository paymentJpaRepository, StockJpaRepository stockJpaRepository, SalesJpaReportEntity salesJpaReportEntity, StockMutationJpaRepository stockMutationJpaRepository, OrderDataAccessMapper orderDataAccessMapper, SalesReportDataAccessMapper salesReportDataAccessMapper, Storage storage, @Value("${google.cloud.storage.bucket-name}") String bucketName) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderItemJpaRepository = orderItemJpaRepository;
        this.paymentJpaRepository = paymentJpaRepository;
        this.stockJpaRepository = stockJpaRepository;
        this.salesJpaReportEntity = salesJpaReportEntity;
        this.stockMutationJpaRepository = stockMutationJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
        this.salesReportDataAccessMapper = salesReportDataAccessMapper;
        this.storage = storage;
        this.bucketName = bucketName;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderDataAccessMapper.orderToOrderEntity(order);

        OrderEntity savedOrderEntity = orderJpaRepository.save(orderEntity);

        List<OrderItemEntity> orderItemEntities = orderDataAccessMapper.orderToOrderItemEntities(order, savedOrderEntity);
        orderItemJpaRepository.saveAll(orderItemEntities);

        PaymentEntity paymentEntity = orderDataAccessMapper.orderToPayment(order, savedOrderEntity);

        paymentJpaRepository.save(paymentEntity);

        return orderDataAccessMapper.orderFromEntity(savedOrderEntity);
    }

    @Override
    @Transactional
    public List<GetOrderResponse> getAllOrder(UUID userId) {
        List<OrderEntity> orders = orderJpaRepository.findByUser_IdOrderByOrderDateDesc(userId);

        if (orders.isEmpty()) {
            return Collections.emptyList();
        }

        return orderDataAccessMapper.getAllOrders(orders);
    }

    @Override
    public String uploadPayment(UUID orderId, MultipartFile file) throws IOException {
        // Validate file size
        if (file.getSize() > 1_048_576) {
            throw new IllegalArgumentException("File size exceeds 1 MB limit");
        }

        // Validate file content type
        if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
            throw new IllegalArgumentException("Invalid file type. Only image files are allowed.");
        }

        String fileName = String.format("warehouse/%s/%s", orderId, file.getOriginalFilename());
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        // Return the public URL of the uploaded file
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }

    @Override
    @Transactional
    public Page<GetOrderResponse> getOrders(Integer page, Integer size, String status) {

        Pageable pageable = PageRequest.of(page, size);

        Page<OrderEntity> orderEntities;

        orderEntities  = orderJpaRepository.findAll(pageable);

        List<GetOrderResponse> responses = orderEntities.stream()
                .map(orderDataAccessMapper::getOrderResponse)
                .toList();

        return new PageImpl<>(responses, pageable, orderEntities.getTotalElements());

    }

    @Override
    @Transactional
    public UploadPaymentResponse uploadPayment(UploadPaymentCommand uploadPaymentCommand) {
        PaymentEntity paymentEntity = paymentJpaRepository.findByOrder_Id(uploadPaymentCommand.getOrder_id());

        paymentEntity.setPayment_date(new Date());
        paymentEntity.setPayment_proof(uploadPaymentCommand.getUrl_payment());

        paymentJpaRepository.save(paymentEntity);

        return UploadPaymentResponse.builder()
                .order_id(uploadPaymentCommand.getOrder_id())
                .payment_url(uploadPaymentCommand.getUrl_payment())
                .build();
    }

    @Override
    @Transactional
    public ShippingOrderResponse shipOrder(ShippingOrderCommand shipOrderCommand) {
        OrderEntity orderEntity = orderJpaRepository.findById(shipOrderCommand.getOrder_id())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderEntity.setStatus(OrderStatus.SHIPPING);

        orderJpaRepository.save(orderEntity);

        return ShippingOrderResponse.builder()
                .order_id(shipOrderCommand.getOrder_id())
                .username(orderEntity.getUser().getUser_name())
                .user_address(orderEntity.getUser_address())
                .message("Success fully shipped order")
                .build();
    }

    @Override
    @Transactional
    public UpdateStockResponse updateStock(UpdateStockCommand updateStockCommand) {
        StockEntity stockEntity = stockJpaRepository.findByProduct_IdAndWarehouse_Id(updateStockCommand.getProductId(), updateStockCommand.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        Integer reduceUpdateStock = stockEntity.getQuantity() > updateStockCommand.getQuantity() ? stockEntity.getQuantity() - updateStockCommand.getQuantity() : 0;

        int updateStockLeft = updateStockCommand.getQuantity()  - stockEntity.getQuantity();

        stockEntity.setQuantity(reduceUpdateStock);
        stockJpaRepository.save(stockEntity);

        return UpdateStockResponse.builder()
                .message("Successfully update stock")
                .stock_id(stockEntity.getId())
                .warehouseId(stockEntity.getWarehouse().getId())
                .productId(stockEntity.getProduct().getId())
                .updateStock(updateStockCommand.getQuantity())
                .updateStockLeft(updateStockLeft)
                .isUpdateAnotherStock(updateStockLeft > 0)
                .build();
    }

    @Override
    @Transactional
    public void createSales(CreateSalesCommand createSalesCommand) {
        SalesReportEntity salesReportEntity = salesReportDataAccessMapper.salesReportEntity(createSalesCommand);

        salesJpaReportEntity.save(salesReportEntity);
    }

    @Override
    @Transactional
    public void createMutationStock(CreateMutationStock createMutationStock) {
        StockMutationEntity mutationEntity = orderDataAccessMapper.stockMutationEntity(createMutationStock);
        stockMutationJpaRepository.save(mutationEntity);
    }

}
