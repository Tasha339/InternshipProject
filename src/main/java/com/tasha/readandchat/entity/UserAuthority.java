package com.tasha.readandchat.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Enumerated(EnumType.STRING)
    private UserAuthorityEnum userAuthority;

    @Override
    public String getAuthority() {
        return userAuthority.name();
    }
}
