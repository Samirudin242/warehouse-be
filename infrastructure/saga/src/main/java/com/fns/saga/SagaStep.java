package com.fns.saga;

import com.fns.domain.event.*;

public interface SagaStep<T, S extends DomainEvent, U extends DomainEvent> {
    S process(T  data);
    U rollback(T data);
}
