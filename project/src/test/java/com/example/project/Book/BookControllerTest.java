package com.example.project.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BookControllerTest {

  private BookService bookService;
  private BookController bookController;

  @Test
  void testFindAll() {
    // Arrange
    bookService = mock(BookService.class);
    bookController = new BookController(bookService);

    List<Book> books = Arrays.asList(new Book(1, "Title1", 100, LocalDate.now()),
        new Book(2, "Title2", 150, LocalDate.now()));
    when(bookService.findAll()).thenReturn(books);

    // Act
    List<Book> result = bookController.findAll();

    // Assert
    assertEquals(2, result.size());
    assertEquals(books, result);
  }

  @Test
  void testFindById() {
    // Arrange
    bookService = mock(BookService.class);
    bookController = new BookController(bookService);

    Book book = new Book(1, "Title", 100, LocalDate.now());
    when(bookService.findById(1)).thenReturn(book);

    // Act
    Book result = bookController.findById(1);

    // Assert
    assertEquals(book, result);
  }

  @Test
  void testCreate() {
    // Arrange
    bookService = mock(BookService.class);
    bookController = new BookController(bookService);

    Book newBook = new Book(null, "NewTitle", 120, LocalDate.now());
    when(bookService.save(newBook)).thenReturn(newBook);

    // Act
    Book result = bookController.create(newBook);

    // Assert
    assertEquals(newBook, result);
  }

  @Test
  void testUpdate() {
    // Arrange
    bookService = mock(BookService.class);
    bookController = new BookController(bookService);

    int bookId = 1;
    Book updatedBook = new Book(bookId, "UpdatedTitle", 150, LocalDate.now());
    when(bookService.update(bookId, updatedBook)).thenReturn(updatedBook);

    // Act
    Book result = bookController.update(updatedBook, bookId);

    // Assert
    assertEquals(updatedBook, result);
  }

  @Test
  void testDelete() {
    // Arrange
    bookService = mock(BookService.class);
    bookController = new BookController(bookService);

    int bookId = 1;

    // Act
    bookController.delete(bookId);

    // Assert
    // If the method returns void, there's nothing to assert
    verify(bookService, times(1)).delete(bookId);
  }
}
