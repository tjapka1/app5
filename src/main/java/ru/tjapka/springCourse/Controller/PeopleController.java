package ru.tjapka.springCourse.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tjapka.springCourse.Models.Person;
import ru.tjapka.springCourse.Service.impl.BookServiceImpl;
import ru.tjapka.springCourse.Service.impl.PersonServiceImpl;
import ru.tjapka.springCourse.Service.stand.PersonService;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonServiceImpl personService;
    private final BookServiceImpl bookService;
    @Autowired
    public PeopleController(PersonServiceImpl personServiceImp, BookServiceImpl bookService) {
        this.personService = personServiceImp;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personService.getAllPersons());
        return "people/showAllPersons";
    }
    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") long id){
        model.addAttribute("person", personService.getPersonById(id));
        model.addAttribute("books", bookService.getAllBooksByPersonId(id));
        return "people/showOnePerson";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){

        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        personService.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id){
        model.addAttribute("person", personService.getPersonById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") long id){
        if (bindingResult.hasErrors()){
            return "people/edit";
        }
        personService.updatePerson(id, person);
        return "redirect:/people/"+id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        personService.deletePerson(id);
        return "redirect:/people";
    }

}
