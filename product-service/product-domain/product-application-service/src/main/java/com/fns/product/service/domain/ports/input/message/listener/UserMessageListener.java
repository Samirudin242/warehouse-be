package com.fns.product.service.domain.ports.input.message.listener;

import com.fns.product.service.domain.dto.message.UserModel;

public interface UserMessageListener {
    void savedUser(UserModel userModel);
    void editUser(UserModel userModel);
}
