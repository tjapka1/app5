package ru.tjapka.springCourse.Controller;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tjapka.springCourse.Models.Book;
import ru.tjapka.springCourse.Models.Person;
import ru.tjapka.springCourse.Service.impl.BookServiceImpl;
import ru.tjapka.springCourse.Service.impl.PersonServiceImpl;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookServiceImpl bookService;
    private final PersonServiceImpl personService;


    @Autowired
    public BookController(BookServiceImpl bookServiceImpl, PersonServiceImpl personService) {
        this.bookService = bookServiceImpl;
        this.personService = personService;
    }

    @GetMapping()
    public String showAllBooks(Model model,
                               @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "book_per_page", required = false) Integer booksPerPage,
                               @RequestParam(value = "sort_by_year", required = false) boolean sortByYear) {
        if (page==null || booksPerPage==null || sortByYear) {
            model.addAttribute("book", bookService.getAllBooks(sortByYear));
        }else {
            model.addAttribute("book", bookService.getAllBooksWithPagination(page, booksPerPage, sortByYear));
        }
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
    @PreAuthorize("PreAuthorizehasAnyAuthority('ROLE_ADMIN')")
    public String abbBook(@ModelAttribute("book")Book book){
        return "book/addBook";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String createNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/abbBook";
        }
        bookService.addBook(book);
        return "redirect:/book";

    }
    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String editBook(Model model, @PathVariable("id") long id){
        model.addAttribute("book", bookService.getBookById(id));
        return "book/editBook";
    }
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable long id){
        if (bindingResult.hasErrors()){
            return "book/editBook";
        }
        bookService.updateBook(id, book);
        return "redirect:/book/"+id;
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        return "redirect:/book";

    }
    @PatchMapping("/{bookId}/release")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String release(@PathVariable long bookId){
        bookService.release(bookId);
        return "redirect:/book/" + bookId;
    }
    @PatchMapping("/{bookId}/assign")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String assign(@PathVariable long bookId, @ModelAttribute("person") Person selectedPerson){
        bookService.assign(bookId, selectedPerson.getId(), new Date());
        return "redirect:/book/"+ bookId;

    }
    @GetMapping("/search")
    public String search(){
        return "book/search";
    }
    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("title") String title){
        model.addAttribute("books", bookService.searchBookByTitle(title));
        return "book/search";

    }


}
