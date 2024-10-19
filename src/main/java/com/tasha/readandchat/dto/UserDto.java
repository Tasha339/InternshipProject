package com.tasha.readandchat.dto;

import com.tasha.readandchat.entity.UserAuthority;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;
    private String fullName;
    private String email;
    private List<UserAuthority> authorities;
}
