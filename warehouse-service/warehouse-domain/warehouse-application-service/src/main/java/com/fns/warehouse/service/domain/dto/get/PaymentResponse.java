package com.fns.warehouse.service.domain.dto.get;

import com.fns.warehouse.service.domain.entity.PaymentType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Builder
public class PaymentResponse {

    private UUID id;

    private PaymentType payment_type;

    private Date payment_date;

    private String payment_proof;


}
