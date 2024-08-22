package com.PruebaTecnica.training.service;

import com.PruebaTecnica.training.model.Book;
import com.PruebaTecnica.training.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(UUID id) {
        return bookRepository.findById(id);
    }

    @Override
    public Page<Book> getAllBooks(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book updateBook(UUID id, Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }



    @Override
    public List<Book> searchBooks(String title, String author, String genre, String startDate, String endDate) {
        //Necesito todos los libros
        List<Book> allBooks = bookRepository.findAll();
        //Necesito hacer el filtrado convierto a stream para esto
        return allBooks.stream()
                .filter(book -> (title == null || book.getTitle().toLowerCase().contains(title.toLowerCase())) &&
                        (author == null || book.getAuthor().getFullName().toLowerCase().contains(author.toLowerCase())) &&
                        (genre == null || book.getGenre().name().equalsIgnoreCase(genre)) &&
                        (startDate == null || book.getPublicationDate().isAfter(LocalDate.parse(startDate).minusDays(1))) &&
                        (endDate == null || book.getPublicationDate().isBefore(LocalDate.parse(endDate).plusDays(1))))
                //convierto el flujo de libros filtrados en una lista uso collect
                .collect(Collectors.toList());
    }

}