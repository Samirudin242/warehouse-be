package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.CreateOrderCommand;
import com.fns.warehouse.service.domain.dto.create.UpdateStatusOrder;
import com.fns.warehouse.service.domain.dto.create.ShippingOrderCommand;
import com.fns.warehouse.service.domain.dto.create.UploadPaymentCommand;
import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.dto.response.CreateOrderResponse;
import com.fns.warehouse.service.domain.dto.response.UpdateStatusOrderResponse;
import com.fns.warehouse.service.domain.dto.response.ShippingOrderResponse;
import com.fns.warehouse.service.domain.dto.response.UploadPaymentResponse;
import com.fns.warehouse.service.domain.ports.input.service.OrderApplicationService;
import com.fns.warehouse.service.domain.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCommandHandler orderCommandHandler;
    private final OrderRepository orderRepository;

    public OrderApplicationServiceImpl(OrderCommandHandler orderCommandHandler, OrderRepository orderRepository) {
        this.orderCommandHandler = orderCommandHandler;
        this.orderRepository = orderRepository;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public List<GetOrderResponse> getAllOrderUser(UUID userId) {
        return orderCommandHandler.getAllOrder(userId);
    }

    @Override
    public String uploadPayment(UUID orderId, MultipartFile file) throws IOException {
        return orderRepository.uploadPayment(orderId, file);
    }

    @Override
    public Page<GetOrderResponse> getOrders(Integer page, Integer size, String status) {
        return orderRepository.getOrders(page, size, status);
    }

    @Override
    public UploadPaymentResponse uploadPayment(UploadPaymentCommand uploadPaymentCommand) {
        return orderCommandHandler.uploadPayment(uploadPaymentCommand);
    }

    @Override
    public ShippingOrderResponse shipOrder(ShippingOrderCommand shippingOrderCommand) {
        return orderCommandHandler.shipOrder(shippingOrderCommand);
    }

    @Override
    public UpdateStatusOrderResponse receivedOrder(UpdateStatusOrder updateStatusOrderCommand) {
        return orderRepository.receiveOrder(updateStatusOrderCommand);
    }

    @Override
    public UpdateStatusOrderResponse cancelOrder(UpdateStatusOrder updateStatusOrderCommand) {
        return orderRepository.cancelOrder(updateStatusOrderCommand);
    }
}
