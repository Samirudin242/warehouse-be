package com.fns.user.service.dataaccess.user.entity;

import com.fns.domain.valueobject.EmailAddress;
import com.fns.user.service.dataaccess.user.converter.EmailAddressConverter;
import com.fns.user.service.dataaccess.user.converter.PasswordConverter;
import com.fns.user.service.dataaccess.user.converter.RoleConverter;
import com.fns.user.service.dataaccess.user.converter.UserNameConverter;
import com.fns.user.service.domain.valueObject.Password;
import com.fns.user.service.domain.valueObject.Role;
import com.fns.user.service.domain.valueObject.UserName;
import com.fns.user.service.domain.valueObject.UserRoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID id;

//    @Convert(converter = UserNameConverter.class)
    private String userName;

//    @Convert(converter = PasswordConverter.class)
    private String password;

//    @Convert(converter = EmailAddressConverter.class)
    private String email;

//    @Convert(converter = RoleConverter.class)
    private String userRole;

}
