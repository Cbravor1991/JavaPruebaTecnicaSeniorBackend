package com.PruebaTecnica.training.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.PruebaTecnica.training.model.Author;

public interface AuthorService {

    Author createAuthor(Author author);

    Optional<Author> getAuthorById(UUID id);

    List<Author> getAllAuthors();

    Author updateAuthor(UUID id, Author author);

    void deleteAuthor(UUID id);
}