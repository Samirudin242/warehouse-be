package com.fns.product.service.domain.ports.input.message.listener;

import com.fns.product.service.domain.dto.message.UserModel;
import com.fns.product.service.domain.dto.message.WarehouseModel;

public interface UserMessageListener {
    void savedUser(UserModel userModel);
}
