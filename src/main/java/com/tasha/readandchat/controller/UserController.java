package com.tasha.readandchat.controller;

import com.tasha.readandchat.dto.BookResponseDto;
import com.tasha.readandchat.dto.UserDto;
import com.tasha.readandchat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/create-admin/{id}")
    public ResponseEntity<UserDto> createAdmin(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(userService.createAdmin(id));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

    @PostMapping("/unadmin/{id}")
    public ResponseEntity<UserDto> unAdmin(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(userService.unAdmin(id));
    }

}
