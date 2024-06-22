package ru.tjapka.springCourse.Service.stand;

import ru.tjapka.springCourse.Models.Book;
import ru.tjapka.springCourse.Models.Person;

import java.util.Date;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks(boolean sortByYear);
    List<Book> getAllBooksWithPagination(int page, int booksPerPage, boolean sortByYear);
    Book getBookById(long id);
    void addBook(Book book);
    void updateBook(long id, Book updatedbook);
    void deleteBook(long id);
    Person getBookOwner(long bookId);
    void release(long bookId);
    void assign(long bookId, long PersonId, Date takeAt);
    List<Book> searchBookByTitle(String title);
}
