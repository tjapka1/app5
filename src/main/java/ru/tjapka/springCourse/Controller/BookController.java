package ru.tjapka.springCourse.Controller;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tjapka.springCourse.Models.Book;
import ru.tjapka.springCourse.Models.Person;
import ru.tjapka.springCourse.Service.impl.BookServiceImpl;
import ru.tjapka.springCourse.Service.impl.PersonServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookServiceImpl bookService;
    private final PersonServiceImpl personService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookController(BookServiceImpl bookServiceImpl, PersonServiceImpl personService, ModelMapper modelMapper) {
        this.bookService = bookServiceImpl;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String showAllBooks(Model model){
        model.addAttribute("book", bookService.getAllBooks());
        return "book/showAllBooks";
}
    @GetMapping("/{id}")
    public String showOneBook(Model model, @PathVariable("id") long bookId, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookService.getBookById(bookId));

        Optional<Person> bookOwner = Optional.ofNullable(bookService.getBookOwner(bookId));

        if(bookOwner.isPresent()){
            model.addAttribute("owner", bookOwner.get());
        }else {
            model.addAttribute("people", personService.getAllPersons());
        }
        return "book/showOneBook";
    }
    @GetMapping("/new")
    public String abbBook(@ModelAttribute("book")Book book){
        return "book/addBook";
    }

    @PostMapping
    public String createNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/abbBook";
        }
        bookService.addBook(book);
        return "redirect:/book";

    }
    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") long id){
        model.addAttribute("book", bookService.getBookById(id));
        return "book/editBook";
    }
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable long id){
        if (bindingResult.hasErrors()){
            return "book/editBook";
        }
        bookService.updateBook(id, book);
        return "redirect:/book/"+id;
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        return "redirect:/book";

    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable long id){
        bookService.release(id);
        return "redirect:/book/" + id;
    }
    @PatchMapping("/{bookId}/assign")
    public String assign(@PathVariable long bookId, @ModelAttribute("person") Person selectedPerson){
        bookService.assign(bookId, selectedPerson.getId());
        return "redirect:/book/"+ bookId;
    }

}
