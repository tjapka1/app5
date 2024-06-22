package ru.tjapka.springCourse.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.tjapka.springCourse.Models.Book;
import ru.tjapka.springCourse.Models.Person;
import ru.tjapka.springCourse.Reposotory.BookRepository;
import ru.tjapka.springCourse.Reposotory.PersonRepository;
import ru.tjapka.springCourse.Service.stand.BookService;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PersonRepository personRepository;
    @Override
    public List<Book> getAllBooks(boolean sortByYear) {
        if (sortByYear) {
            return bookRepository.findAll(Sort.by("year"));
        }else {
            return bookRepository.findAll();
        }
    }
    @Override
    public List<Book> getAllBooksWithPagination(int page, int booksPerPage, boolean sortByYear) {
        if (sortByYear) {
            return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        }else {
            return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        }
    }
    @Override
    public Book getBookById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }
    @Override
    public void updateBook(long id, Book updatedbook) {
        Book toUpdatebook = bookRepository.findById(id).orElse( new Book());
        toUpdatebook.setTitle(updatedbook.getTitle());
        toUpdatebook.setAuthor(updatedbook.getAuthor());
        toUpdatebook.setYear(updatedbook.getYear());
        toUpdatebook.setOwner(toUpdatebook.getOwner());

        bookRepository.save(toUpdatebook);
    }
    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public Person getBookOwner(long bookId) {
        return personRepository.findOwner(bookId);
    }
    @Override
    public void release(long bookId) {
        bookRepository.release(bookId);
    }
    @Override
    public void assign(long bookId, long PersonId, Date takeAt) {
        bookRepository.assign(bookId, PersonId, takeAt);
    }
    public List<Book> searchBookByTitle(String title) {
        return bookRepository.findByTitleStartingWith(title);
    }
}
