package com.tasha.readandchat.repository;

import com.tasha.readandchat.entity.Book;
import com.tasha.readandchat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);
    List<Book> findBooksById(Integer userId);
}