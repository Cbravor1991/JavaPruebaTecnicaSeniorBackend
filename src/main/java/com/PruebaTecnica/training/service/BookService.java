package com.PruebaTecnica.training.service;


import com.PruebaTecnica.training.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    Book createBook(Book book);
    Optional<Book> getBookById(UUID id);

    Page<Book> getAllBooks(int page, int size, String sortBy);

    Book updateBook(UUID id, Book book);
    void deleteBook(UUID id);

    List<Book> searchBooks(String title, String author, String genre, String startDate, String endDate);


}