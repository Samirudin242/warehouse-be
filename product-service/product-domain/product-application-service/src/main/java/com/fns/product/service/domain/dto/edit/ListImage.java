package com.fns.product.service.domain.dto.edit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ListImage {
    UUID id;
    String image_url;
}
