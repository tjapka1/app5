package ru.tjapka.springCourse.Service.stand;

import ru.tjapka.springCourse.Models.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(long id);
    Book addBook(Book book);
    Book updateBook(long id, Book updatedbook);
    void deleteBook(long id);
    void release(long bookId);
    void assign(long bookId, long PersonId);
}
