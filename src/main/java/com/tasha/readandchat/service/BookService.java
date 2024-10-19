package com.tasha.readandchat.service;

import com.tasha.readandchat.dto.BookResponseDto;
import com.tasha.readandchat.entity.Book;
import com.tasha.readandchat.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    public BookResponseDto toBookResponseDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .build();
    }

    public List<BookResponseDto> toBookResponseDtos(List<Book> books) {
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for (Book book : books) {
            bookResponseDtos.add(toBookResponseDto(book));
        }
        return bookResponseDtos;
    }
}
