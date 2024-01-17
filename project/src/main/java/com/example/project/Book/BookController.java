package com.example.project.Book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> findAll() {
    return bookService.findAll();
  }

  @GetMapping("/{id}")
  public Book findById(@PathVariable Integer id) {
    return bookService.findById(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Book create(@RequestBody Book book) {
    return bookService.save(book);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{id}")
  public Book update(@RequestBody Book book, @PathVariable Integer id) {
    return bookService.update(id, book);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    bookService.delete(id);
  }
}
