package com.tasha.readandchat.service;

import com.tasha.readandchat.dto.BookResponseDto;
import com.tasha.readandchat.entity.Book;
import com.tasha.readandchat.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final FileUtils bookUtils;

    public List<BookResponseDto> getBooks(){
        return bookService.toBookResponseDtos(bookRepository.findAll());
    }

    @Transactional
    public List<BookResponseDto> addBook(MultipartFile file) throws IOException {
        var book = Book.builder()
                .title(file.getOriginalFilename())
                .bookData(bookUtils.compressFile(file.getBytes()))
                .build();
        bookRepository.save(book);
        return bookService.toBookResponseDtos(bookRepository.findAll());
    }

    public  List<BookResponseDto> removeBook(Integer bookId){
        bookRepository.deleteById(bookId);
        return bookService.toBookResponseDtos(bookRepository.findAll());
    }

    @Transactional
    public byte[] readBook(Integer id) throws DataFormatException, IOException {
         return bookUtils.decompressFile(
                 bookRepository.findById(id).orElseThrow().getBookData()
         );
    }
}
