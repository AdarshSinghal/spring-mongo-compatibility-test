package com.ad.mongo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ad.mongo.controller.BookController;
import com.ad.mongo.entity.Book;
import com.ad.mongo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class BookControllerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBookTest() {
        // Arrange
        Book book = getBook1();

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        String result = bookController.saveBook(book);

        // Assert
        assertEquals("Added Successfully", result);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void getBooksTest() {
        // Arrange
        Book book1 = getBook1();
        Book book2 = getBook2();

        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);

        // Act
        List<Book> result = bookController.getBooks();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Book 1", result.get(0).getBookName());
        assertEquals("Book 2", result.get(1).getBookName());
        verify(bookRepository, times(1)).findAll();
    }

    private static Book getBook2() {
        Book book2 = new Book();
        book2.setBookName("Book 2");
        book2.setAuthorName("Author 2");
        return book2;
    }

    private static Book getBook1() {
        Book book1 = new Book();
        book1.setBookName("Book 1");
        book1.setAuthorName("Author 1");
        return book1;
    }

    @Test
    void deleteBookTest() {
        // Arrange
        int bookId = 1;

        // Act
        String result = bookController.deleteBook(bookId);

        // Assert
        assertEquals("Deleted Successfully", result);
        verify(bookRepository, times(1)).deleteById(bookId);
    }

}
