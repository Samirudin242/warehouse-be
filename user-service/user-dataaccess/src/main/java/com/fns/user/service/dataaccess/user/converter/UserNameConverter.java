package com.fns.user.service.dataaccess.user.converter;

import com.fns.user.service.domain.valueObject.UserName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserNameConverter implements AttributeConverter<UserName, String> {

    @Override
    public String convertToDatabaseColumn(UserName userName) {
        return (userName != null) ? userName.getUsername() : null;
    }

    @Override
    public UserName convertToEntityAttribute(String dbData) {
        return (dbData != null) ? new UserName(dbData) : null;
    }
}