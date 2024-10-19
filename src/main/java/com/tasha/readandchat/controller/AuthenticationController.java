package com.tasha.readandchat.controller;

import com.tasha.readandchat.dto.LoginUserDto;
import com.tasha.readandchat.dto.RegisterUserDto;
import com.tasha.readandchat.dto.UserDto;
import com.tasha.readandchat.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody RegisterUserDto registerUserDto){
        return ResponseEntity.ok(
                authenticationService.signup(registerUserDto)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto loginUserDto){
        return ResponseEntity.ok(
                authenticationService.login(loginUserDto)
        );
    }

}
