package com.tasha.readandchat.controller;

import com.tasha.readandchat.dto.BookResponseDto;
import com.tasha.readandchat.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/lib")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/books")
    public List<BookResponseDto> getBooks() {
        return libraryService.getBooks();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<BookResponseDto>> addBook(@RequestParam("book") MultipartFile book) throws IOException {
        return ResponseEntity.ok().body(libraryService.addBook(book));
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<BookResponseDto>> removeBook(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(libraryService.removeBook(id));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Integer id) throws DataFormatException, IOException {
        var data = libraryService.readBook(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE))
                .body(data);
    }

}

