package com.example.project.Book;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookServiceTest {

  private BookRepository bookRepository;
  private BookServiceImpl bookService;

  @BeforeEach
  void setUp() {
    bookRepository = mock(BookRepository.class);
    bookService = new BookServiceImpl(bookRepository);
  }

  @Test
  void testFindAll() {
    // Arrange
    Book book1 = new Book(1, "Title1", 100, LocalDate.now());
    Book book2 = new Book(2, "Title2", 150, LocalDate.now());
    when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

    // Act
    List<Book> result = bookService.findAll();

    // Assert
    assertEquals(2, result.size());
    assertEquals(book1, result.get(0));
    assertEquals(book2, result.get(1));
  }

  @Test
  void testFindById() {
    // Arrange
    Book book = new Book(1, "Title", 100, LocalDate.now());
    when(bookRepository.findById(1)).thenReturn(Optional.of(book));

    // Act
    Book result = bookService.findById(1);

    // Assert
    assertEquals(book, result);
  }

  @Test
  void testSave() {
    // Arrange
    Book book = new Book(1, "Title", 100, LocalDate.now());
    when(bookRepository.save(book)).thenReturn(book);

    // Act
    Book result = bookService.save(book);

    // Assert
    assertEquals(book, result);
  }

  @Test
  void testUpdate() {
    // Arrange
    Book existingBook = new Book(1, "Title", 100, LocalDate.now());
    Book updatedBook = new Book(1, "UpdatedTitle", 150, LocalDate.now());

    when(bookRepository.findById(1)).thenReturn(Optional.of(existingBook));
    when(bookRepository.save(existingBook)).thenReturn(updatedBook);

    // Act
    Book result = bookService.update(1, updatedBook);

    // Assert
    assertEquals(updatedBook, result);
    assertEquals("UpdatedTitle", result.getTitle());
    assertEquals(150, result.getPages());
  }

  @Test
  void testDelete() {
    // Arrange
    int bookId = 1;

    // Act
    bookService.delete(bookId);

    // Assert
    verify(bookRepository, times(1)).deleteById(bookId);
  }
}
