package com.fns.warehouse.service.domain.ports.output.repository;

import com.fns.warehouse.service.domain.entity.User;

public interface UserRepository {
    User save(User user);
}
