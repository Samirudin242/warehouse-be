package com.fns.user.service.dataaccess.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID id;

    private String name;

    private String user_name;

    private String password;

    private String email;

    private String profile_picture;

    private String phone_number;

    private Boolean is_verified;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private UserRoleEntity user_role;

    public UserEntity(LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profile_picture='" + profile_picture + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", is_verified=" + is_verified +
                ", user_role=" + user_role +
                '}';
    }

}
