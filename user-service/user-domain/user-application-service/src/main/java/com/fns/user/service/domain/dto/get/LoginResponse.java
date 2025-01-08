package com.fns.user.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Data
public class LoginResponse {
    private String message;
    private String accessToken;
    private Boolean isSuccess;
}
