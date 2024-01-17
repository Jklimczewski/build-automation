package com.example.project.Book;

import java.util.List;

public interface BookService {

  List<Book> findAll();

  Book findById(Integer id);

  Book save(Book book);

  Book update(Integer id, Book book);

  void delete(Integer id);

}
