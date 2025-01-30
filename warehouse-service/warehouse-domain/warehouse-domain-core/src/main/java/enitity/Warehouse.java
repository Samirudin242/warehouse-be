package enitity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class Warehouse {

    private final UUID id;
    private final String name;
    private final UUID admin_id;
}
