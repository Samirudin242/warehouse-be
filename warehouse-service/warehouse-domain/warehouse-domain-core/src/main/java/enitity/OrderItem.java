package enitity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@Data
public class OrderItem {
    private UUID id;
    private UUID order_id;
    private Integer quantity;
    private Double price;
    private UUID product_id;
    private UUID size_id;
    private UUID color_id;
    private List<UUID> warehouse;
}
