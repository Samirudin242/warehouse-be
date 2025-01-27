package com.fns.warehouse.service.domain.dto.create;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class UserCommand {
    Double longitude;
    Double latitude;
}
