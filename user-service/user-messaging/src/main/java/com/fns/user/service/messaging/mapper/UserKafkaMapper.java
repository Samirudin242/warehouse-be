package com.fns.user.service.messaging.mapper;

import com.fns.user.service.domain.entity.User;
import com.fns.user.service.messaging.model.UserKafkaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserKafkaMapper {
    public  UserKafkaModel convertToUser(User user) {
        log.info("Converting to user, {}", user);
        return new UserKafkaModel(
                user.getId(),
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

