package com.example.project.Book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class BookIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testFindAll() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void testFindById() throws Exception {
    Book book = new Book(null, "Integration Test Title", 200, LocalDate.now());
    bookRepository.save(book);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/{id}", book.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Integration Test Title"));
  }

  @Test
  public void testCreate() throws Exception {
    Book newBook = new Book(null, "New Book Title", 150, LocalDate.now());

    mockMvc.perform(MockMvcRequestBuilders.post("/api")
        .content(objectMapper.writeValueAsString(newBook))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("New Book Title"));
  }

  @Test
  public void testUpdate() throws Exception {
    Book existingBook = new Book(null, "Existing Book Title", 180, LocalDate.now());
    bookRepository.save(existingBook);

    Book updatedBook = new Book(existingBook.getId(), "Updated Book Title", 200, LocalDate.now());

    mockMvc.perform(MockMvcRequestBuilders.put("/api/{id}", existingBook.getId())
        .content(objectMapper.writeValueAsString(updatedBook))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Book Title"));
  }

  @Test
  public void testDelete() throws Exception {
    Book bookToDelete = new Book(null, "Book to Delete", 120, LocalDate.now());
    bookRepository.save(bookToDelete);

    mockMvc.perform(MockMvcRequestBuilders.delete("/api/{id}", bookToDelete.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk());

    assert !bookRepository.existsById(bookToDelete.getId());
  }
}
