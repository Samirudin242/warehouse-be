package com.fns.user.service.dataaccess.user.converter;

import com.fns.domain.valueobject.EmailAddress;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmailAddressConverter implements AttributeConverter<EmailAddress, String> {

    @Override
    public String convertToDatabaseColumn(EmailAddress emailAddress) {
        if (emailAddress == null) {
            return null;
        }
        return emailAddress.getEmail();
    }

    @Override
    public EmailAddress convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        return new EmailAddress(dbData);
    }
}
