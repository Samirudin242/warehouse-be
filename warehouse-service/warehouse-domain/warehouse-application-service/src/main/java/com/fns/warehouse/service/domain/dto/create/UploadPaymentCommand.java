package com.fns.warehouse.service.domain.dto.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class UploadPaymentCommand {
    private UUID order_id;
    private String url_payment;
}
