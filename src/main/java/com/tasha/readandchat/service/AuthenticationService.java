package com.tasha.readandchat.service;

import com.tasha.readandchat.dto.LoginUserDto;
import com.tasha.readandchat.dto.RegisterUserDto;
import com.tasha.readandchat.dto.UserDto;
import com.tasha.readandchat.entity.User;
import com.tasha.readandchat.entity.UserAuthority;
import com.tasha.readandchat.entity.UserAuthorityEnum;
import com.tasha.readandchat.repository.UserAuthorityRepo;
import com.tasha.readandchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserAuthorityRepo userAuthorityRepo;
    private final UserService userService;

    public UserDto signup(RegisterUserDto registerUserDto){
        UserAuthority userA = userAuthorityRepo.findByUserAuthority(UserAuthorityEnum.ROLE_USER);
        UserAuthority admin = userAuthorityRepo.findByUserAuthority(UserAuthorityEnum.ROLE_ADMIN);
        User user =  User.builder()
                .fullName(registerUserDto.getFullName())
                .email(registerUserDto.getEmail())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .build();
        if(userRepository.findAll().isEmpty()){
            user.setUserAuthorities(Arrays.asList(
                    userA, admin
            ));
        } else {
            user.setUserAuthorities(Collections.singletonList(userA));
        }

        return userService.toUserDto(userRepository.save(user));
    }

    public String login(LoginUserDto loginUserDto){
        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getEmail(),
                        loginUserDto.getPassword()
                )
        );
        return jwtService.generateToken(
                userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow()
        );
    }
}
