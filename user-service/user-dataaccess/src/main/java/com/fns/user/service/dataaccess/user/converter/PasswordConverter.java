package com.fns.user.service.dataaccess.user.converter;

import com.fns.user.service.domain.valueObject.Password;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PasswordConverter implements AttributeConverter<Password, String> {

    @Override
    public String convertToDatabaseColumn(Password password) {
        return password != null ? password.getHashedPassword() : null;
    }

    @Override
    public Password convertToEntityAttribute(String dbData) {
        return dbData != null ? new Password(dbData) : null;
    }
}
