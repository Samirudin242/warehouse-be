package enitity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Stock {

    private UUID id;
    private Integer quantity;
    private UUID product_id;
    private UUID warehouse_id;
}
