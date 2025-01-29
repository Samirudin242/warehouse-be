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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final PaymentJpaRepository paymentJpaRepository;

    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository, OrderItemJpaRepository orderItemJpaRepository, PaymentJpaRepository paymentJpaRepository, OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderItemJpaRepository = orderItemJpaRepository;
        this.paymentJpaRepository = paymentJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
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
}
