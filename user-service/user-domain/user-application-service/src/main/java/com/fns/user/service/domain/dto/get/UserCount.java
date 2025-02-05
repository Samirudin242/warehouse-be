package com.fns.user.service.domain.dto.get;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCount {
    private Integer totalUser;
    private Integer percentage;
}
