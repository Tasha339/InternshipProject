package com.tasha.readandchat.service;

import com.tasha.readandchat.dto.BookResponseDto;
import com.tasha.readandchat.dto.UserDto;
import com.tasha.readandchat.entity.Book;
import com.tasha.readandchat.entity.User;
import com.tasha.readandchat.entity.UserAuthority;
import com.tasha.readandchat.entity.UserAuthorityEnum;
import com.tasha.readandchat.repository.BookRepository;
import com.tasha.readandchat.repository.UserAuthorityRepo;
import com.tasha.readandchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final UserAuthorityRepo userAuthorityRepo;



    public UserDto createAdmin(Integer id){
        var user = userRepository.findById(id).orElseThrow();
        UserAuthority userA = userAuthorityRepo.findByUserAuthority(UserAuthorityEnum.ROLE_USER);
        UserAuthority admin = userAuthorityRepo.findByUserAuthority(UserAuthorityEnum.ROLE_ADMIN);
        user.setUserAuthorities(List.of(userA, admin));
        return toUserDto(user);
    }

    //converts an admin back to normal user
    public UserDto unAdmin(Integer id){
        var user = userRepository.findById(id).orElseThrow();
        var userA = userAuthorityRepo.findByUserAuthority(UserAuthorityEnum.ROLE_USER);
        user.setUserAuthorities(Collections.singletonList(userA));
        return toUserDto(user);
    }

    public List<UserDto> getUsers(){
        List<UserDto> userDtos = new ArrayList<>();
        var users = userRepository.findAll();
        for(var user : users){
            userDtos.add(toUserDto(user));
        }
        return userDtos;
    }

    public String deleteUser(Integer id){
        userRepository.deleteById(id);
        return "User deleted";
    }

    public User authenticatedUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public UserDto toUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .authorities(user.getUserAuthorities())
                .build();
    }
    //    public List<BookResponseDto> userBooks(){
//          var user = authenticatedUser();
//          return bookService.toBookResponseDtos(userRepository.findBooksById(user.getId()));
//    }
//
//    public List<BookResponseDto> addUserBook(Integer id){
//        var user = authenticatedUser();
//        var book = bookRepository.findById(id).orElseThrow();
//        user.getUserBooks().add(book);
//        book.getReaders().add(user);
//        return bookService.toBookResponseDtos(user.getUserBooks());
//    }
//
//    public List<BookResponseDto> deleteUserBook(Integer id){
//        var user = authenticatedUser();
//        user.getUserBooks().remove(
//                bookRepository.findById(id).orElseThrow()
//        );
//        return bookService.toBookResponseDtos(user.getUserBooks());
//    }
}
