package com.fns.domain.valueobject;

import java.util.regex.Pattern;

public class phoneNumber {
    private final String countryCode;
    private final String number;


    public phoneNumber(String countryCode, String number) {
        if (!isValidCountryCode(countryCode)) {
            throw new IllegalArgumentException("Invalid country code format");
        }
        if (!isValidPhoneNumber(number)) {
            throw new IllegalArgumentException("Phone number must be 10 digits");
        }
        this.countryCode = countryCode;
        this.number = number;
    }

    private boolean isValidCountryCode(String countryCode) {
        return Pattern.matches("\\+\\d{1,3}", countryCode);
    }

    private boolean isValidPhoneNumber(String number) {
        return Pattern.matches("\\d{10}", number);
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

}
