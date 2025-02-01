package com.fns.warehouse.service.domain.ports.input.service;

import com.fns.warehouse.service.domain.dto.create.CreateOrderCommand;
import com.fns.warehouse.service.domain.dto.create.UpdateStatusOrder;
import com.fns.warehouse.service.domain.dto.create.ShippingOrderCommand;
import com.fns.warehouse.service.domain.dto.create.UploadPaymentCommand;
import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.dto.response.CreateOrderResponse;
import com.fns.warehouse.service.domain.dto.response.UpdateStatusOrderResponse;
import com.fns.warehouse.service.domain.dto.response.ShippingOrderResponse;
import com.fns.warehouse.service.domain.dto.response.UploadPaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public interface OrderApplicationService {
    CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand);

    List<GetOrderResponse> getAllOrderUser(UUID userId);

    String uploadPayment(UUID orderId, MultipartFile file) throws IOException;

    Page<GetOrderResponse> getOrders(Integer page, Integer size, String status);

    UploadPaymentResponse uploadPayment(UploadPaymentCommand uploadPaymentCommand);

    ShippingOrderResponse shipOrder(ShippingOrderCommand shippingOrderCommand);

    UpdateStatusOrderResponse receivedOrder(UpdateStatusOrder updateStatusOrderCommand);

    UpdateStatusOrderResponse cancelOrder(UpdateStatusOrder updateStatusOrderCommand);

}
