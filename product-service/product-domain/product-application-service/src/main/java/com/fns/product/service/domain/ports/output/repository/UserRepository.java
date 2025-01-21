package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.dto.message.UserModel;

public interface UserRepository {

    void saveUser(UserModel user);

}
