package ru.tjapka.springCourse.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tjapka.springCourse.DTO.PersonDTO;
import ru.tjapka.springCourse.Models.Book;
import ru.tjapka.springCourse.Models.Person;
import ru.tjapka.springCourse.Reposotory.BookRepository;
import ru.tjapka.springCourse.Reposotory.PersonRepository;
import ru.tjapka.springCourse.Service.stand.BookService;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Book> getAllBooks() {
       return bookRepository.bookAll();
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(long id, Book updatedbook) {
        Book toUpdatebook = bookRepository.findById(id).orElse( new Book());
        toUpdatebook.setTitle(updatedbook.getTitle());
        toUpdatebook.setAuthor(updatedbook.getAuthor());
        toUpdatebook.setYear(updatedbook.getYear());
        bookRepository.save(toUpdatebook);
        return toUpdatebook;
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);

    }

    public List<Book> getAllBooksByPersonId(long id) {
        return bookRepository.findAllBooksByPersonId(id);
    }

    public Person getBookOwner(long bookId) {
        return personRepository.findOwner(bookId);
    }

    public void release(long id) {
        bookRepository.release(id);
    }

    public void assign(long bookId, long PersonId) {
        bookRepository.assign(bookId, PersonId);
    }
}
