package com.example.project.Book;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public Book findById(Integer id) {
    return bookRepository.findById(id).orElseThrow();
  }

  @Override
  public Book save(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book update(Integer id, Book book) {
    Book bookToUpdate = findById(id);
    bookToUpdate.setPages(book.getPages());
    bookToUpdate.setReleasedDate(book.getReleasedDate());
    bookToUpdate.setTitle(book.getTitle());
    return bookRepository.save(bookToUpdate);
  }

  @Override
  public void delete(Integer id) {
    bookRepository.deleteById(id);
  }
}
