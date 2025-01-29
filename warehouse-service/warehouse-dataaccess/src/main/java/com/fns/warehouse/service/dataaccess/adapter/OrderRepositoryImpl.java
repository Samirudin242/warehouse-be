package com.fns.warehouse.service.dataaccess.adapter;

import com.fns.warehouse.service.dataaccess.entity.OrderEntity;
import com.fns.warehouse.service.dataaccess.entity.OrderItemEntity;
import com.fns.warehouse.service.dataaccess.entity.PaymentEntity;
import com.fns.warehouse.service.dataaccess.mapper.OrderDataAccessMapper;
import com.fns.warehouse.service.dataaccess.repository.OrderItemJpaRepository;
import com.fns.warehouse.service.dataaccess.repository.OrderJpaRepository;
import com.fns.warehouse.service.dataaccess.repository.PaymentJpaRepository;
import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.ports.output.repository.OrderRepository;
import enitity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final PaymentJpaRepository paymentJpaRepository;

    private final OrderDataAccessMapper orderDataAccessMapper;

    private final Storage storage;
    private final String bucketName;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository, OrderItemJpaRepository orderItemJpaRepository, PaymentJpaRepository paymentJpaRepository, OrderDataAccessMapper orderDataAccessMapper, Storage storage, @Value("${google.cloud.storage.bucket-name}") String bucketName) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderItemJpaRepository = orderItemJpaRepository;
        this.paymentJpaRepository = paymentJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
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
}
