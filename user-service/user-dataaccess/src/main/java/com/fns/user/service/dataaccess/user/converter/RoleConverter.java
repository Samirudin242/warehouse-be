package com.fns.user.service.dataaccess.user.converter;

import com.fns.user.service.domain.valueObject.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return (role != null) ? role.getRoleName() : null;
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return (dbData != null) ? new Role(dbData) : null;
    }
}