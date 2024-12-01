//package com.fns.domain.valueObject;
//
//import javax.validation.constraints.NotNull;
//
//public class EmailAddress {
//    private final String email;
//
//    public EmailAddress(@NotNull EmailAddress email) {
//        if(!isValidEmail(String.valueOf(email))) {
//            throw new IllegalArgumentException("Invalid email format");
//        }
//        this.email = String.valueOf(email);
//    }
//
//    private boolean isValidEmail(String email) {
//        return email.contains("@") && email.contains(".");
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//}


package com.fns.domain.valueobject;

import javax.validation.constraints.NotNull;

public class EmailAddress {
    private final String email;

    // Constructor now takes a String for the email parameter
    public EmailAddress(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return email;
    }
}