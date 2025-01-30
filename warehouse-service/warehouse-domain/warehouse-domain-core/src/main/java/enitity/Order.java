package enitity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@Data
public class Order {
    private UUID id;
    private UUID user_id;
    private Date order_date;
    private Double total_amount;
    private Double total_shipping;
    private String user_address;
    private Double user_latitude;
    private Double user_longitude;
    private String status;
    private UUID warehouse_id;
    private String payment_method;
    private List<OrderItem> orders;
}
