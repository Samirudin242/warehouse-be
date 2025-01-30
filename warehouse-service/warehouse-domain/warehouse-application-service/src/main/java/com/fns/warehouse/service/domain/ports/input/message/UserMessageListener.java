package com.fns.warehouse.service.domain.ports.input.message;

import com.fns.warehouse.service.domain.dto.message.UserModel;

public interface UserMessageListener {

     void savedUser(UserModel userModel);

}
