package com.fns.user.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@Data
public class ProfilePhotoResponse {
    private String url;
    private String message;
}
