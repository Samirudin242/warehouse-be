package com.fns.user.service.messaging.mapper;

import com.fns.user.service.domain.entity.User;
import com.fns.user.service.messaging.model.UserKafkaModel;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaMapper {
    public  UserKafkaModel convertToUser(User user) {
        return new UserKafkaModel(
                user.getName(),
                user.getUser_name(),
                user.getEmail(),
                user.getProfile_picture(),
                user.getRole_id().toString(),
                user.getPhone_number(),
                user.getAddress(),
                user.getProvince(),
                user.getCity(),
                user.getProvince_id(),
                user.getCity_id(),
                user.getPostal_code(),
                user.getIs_verified()
        );
    }
}

