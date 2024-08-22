package com.PruebaTecnica.training.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.PruebaTecnica.training.model.Author;
import com.PruebaTecnica.training.model.Book;
import com.PruebaTecnica.training.model.Genre;
import com.PruebaTecnica.training.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private Author author;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        //Necesito el autor
        author = new Author();
        //para generar el tipo UUID uso un random ya que un valor fijo no funciona
        author.setId(UUID.randomUUID());
        author.setFullName("Antonio Santa Ana");
        author.setDateOfBirth(LocalDate.of(1963, 1, 1));
        author.setNationality("Argentina");

        // Necesito el libro
        book = new Book();
        book.setTitle("Los ojos del perro siberiano");
        book.setAuthor(author);
        book.setIsbn("100");
        book.setPublicationDate(LocalDate.of(2000, 1, 1));
        book.setNumberOfPages(500);
        book.setGenre(Genre.DRAMA);
    }

    @Test
    public void testCreateBook() {
        // Simular el comportamiento del repositorio al guardar un libro
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        // Llamar al servicio para crear un libro
        Book createdBook = bookService.createBook(book);
        assertNotNull(createdBook);
        assertEquals(book.getTitle(), createdBook.getTitle());
        assertEquals(book.getAuthor(), createdBook.getAuthor());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testGetBookById() {

        UUID bookId = book.getId();
        //uso optional para manejar el caso que no haya libro en forma adecuada
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        Optional<Book> foundBook = bookService.getBookById(bookId);
        assertTrue(foundBook.isPresent());
        assertEquals(book.getTitle(), foundBook.get().getTitle());
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    public void testGetAllBooks() {

        //necesito configura la cantidad de libros que quiero obtener por eso usa Pageable
        Pageable pageable = PageRequest.of(0, 10, Sort.by("title"));
        List<Book> bookList = List.of(book);
        Page<Book> bookPage = new PageImpl<>(bookList, pageable, bookList.size());
        when(bookRepository.findAll(pageable)).thenReturn(bookPage);
        Page<Book> result = bookService.getAllBooks(0, 10, "title");
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals(book.getTitle(), result.getContent().get(0).getTitle());
        verify(bookRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testUpdateBook() {
        UUID bookId = UUID.randomUUID();
        when(bookRepository.existsById(bookId)).thenReturn(true);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book updatedBook = bookService.updateBook(bookId, book);
        assertNotNull(updatedBook);
        assertEquals(book.getTitle(), updatedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
        verify(bookRepository, times(1)).existsById(bookId);
    }

    @Test
    public void testDeleteBook() {
        UUID bookId = UUID.randomUUID();
        // Es necesario usar doNothing(), al momento de borrar el libro
        doNothing().when(bookRepository).deleteById(bookId);
        bookService.deleteBook(bookId);
        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    public void testSearchBooks() {

        when(bookRepository.findAll()).thenReturn(List.of(book));
        List<Book> result = bookService.searchBooks("Los ojos del perro siberiano", "Antonio Santa Ana", "Drama", "2000-01-01", "2024-12-31");
        assertEquals(1, result.size());
        assertEquals(book.getTitle(), result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }


}