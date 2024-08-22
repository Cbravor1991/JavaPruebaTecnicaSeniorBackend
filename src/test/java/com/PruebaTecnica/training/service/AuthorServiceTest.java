package com.PruebaTecnica.training.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.PruebaTecnica.training.model.Author;
import com.PruebaTecnica.training.repository.AuthorRepository;
import com.PruebaTecnica.training.service.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        author = new Author();
        author.setId(UUID.randomUUID());
        author.setFullName("Jorge Luis Borges");
        author.setDateOfBirth(LocalDate.of(1899, 8, 24));
        author.setNationality("argentino");
    }

    @Test
    public void testCreateAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        Author createdAuthor = authorService.createAuthor(author);
        assertNotNull(createdAuthor);
        assertEquals("Jorge Luis Borges", createdAuthor.getFullName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void testGetAuthorById() {
        UUID authorId = UUID.randomUUID();
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        Optional<Author> foundAuthor = authorService.getAuthorById(authorId);
        assertTrue(foundAuthor.isPresent());
        assertEquals("Jorge Luis Borges", foundAuthor.get().getFullName());
        verify(authorRepository, times(1)).findById(authorId);
    }

    @Test
    public void testUpdateAuthor() {
        UUID authorId = UUID.randomUUID();
        when(authorRepository.existsById(authorId)).thenReturn(true);
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        Author updatedAuthor = authorService.updateAuthor(authorId, author);
        assertNotNull(updatedAuthor);
        assertEquals("Jorge Luis Borges", updatedAuthor.getFullName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void testDeleteAuthor() {
        UUID authorId = UUID.randomUUID();
        doNothing().when(authorRepository).deleteById(authorId);
        authorService.deleteAuthor(authorId);
        verify(authorRepository, times(1)).deleteById(authorId);
    }

    @Test
    public void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(author));
        List<Author> authors = authorService.getAllAuthors();
        assertEquals(1, authors.size());
        verify(authorRepository, times(1)).findAll();
    }
}
