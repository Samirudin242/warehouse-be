package com.fns.warehouse.service.domain.ports.output.repository;

import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import enitity.Order;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order saveOrder(Order order);

    List<GetOrderResponse> getAllOrder(UUID userId);

    String uploadPayment(UUID orderId, MultipartFile file) throws IOException;

}
