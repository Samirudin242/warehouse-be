package com.fns.product.service.messaging.mapper;

import com.fns.product.service.domain.dto.message.UserModel;
import com.fns.product.service.messaging.model.UserKafkaModel;
import org.springframework.stereotype.Component;

@Component
public class UserMessagingMapper {
    public UserModel mapToUserModel(UserKafkaModel userKafkaModel) {

        return UserModel.builder()
                .id(userKafkaModel.getId())
                .name(userKafkaModel.getName())
                .userName(userKafkaModel.getUserName())
                .email(userKafkaModel.getEmail())
                .phoneNumber(userKafkaModel.getPhoneNumber())
                .profilePicture(userKafkaModel.getProfilePicture())
                .address(userKafkaModel.getAddress())
                .cityId(userKafkaModel.getCityId())
                .provinceId(userKafkaModel.getProvinceId())
                .roleId(userKafkaModel.getRoleId())
                .build();
    }
}
