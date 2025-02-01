package com.fns.warehouse.service.domain.ports.output.repository;

import com.fns.warehouse.service.domain.dto.create.*;
import com.fns.warehouse.service.domain.dto.get.GetOrderResponse;
import com.fns.warehouse.service.domain.dto.response.UpdateStatusOrderResponse;
import com.fns.warehouse.service.domain.dto.response.ShippingOrderResponse;
import com.fns.warehouse.service.domain.dto.response.UpdateStockResponse;
import com.fns.warehouse.service.domain.dto.response.UploadPaymentResponse;
import enitity.Order;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order saveOrder(Order order);

    List<GetOrderResponse> getAllOrder(UUID userId);

    String uploadPayment(UUID orderId, MultipartFile file) throws IOException;

    Page<GetOrderResponse> getOrders(Integer page, Integer size, String status);

    UploadPaymentResponse uploadPayment(UploadPaymentCommand uploadPaymentCommand);

    ShippingOrderResponse shipOrder(ShippingOrderCommand shipOrderCommand);

    UpdateStockResponse updateStock(UpdateStockCommand updateStockCommand);

    void createSales(CreateSalesCommand createSalesCommand);

    void createMutationStock(CreateMutationStock createMutationStock);

    UpdateStatusOrderResponse receiveOrder(UpdateStatusOrder updateStatusOrder);

    UpdateStatusOrderResponse cancelOrder(UpdateStatusOrder updateStatusOrder);

}
