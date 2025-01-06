package com.fns.warehouse.service.domain.ports.output.message;
import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public interface WarehouseMessagePublisher extends DomainEventPublisher<WarehouseCreatedEvent> {
}
