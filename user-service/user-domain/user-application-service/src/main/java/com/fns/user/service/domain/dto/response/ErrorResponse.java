package com.fns.user.service.domain.dto.response;

import lombok.*;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String timestamp;

}
