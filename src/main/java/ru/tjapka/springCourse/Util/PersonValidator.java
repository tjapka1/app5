package ru.tjapka.springCourse.Util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.tjapka.springCourse.Models.Person;
import ru.tjapka.springCourse.Service.impl.PersonServiceImpl;

@Component
public class PersonValidator implements Validator {
    @Autowired
    private PersonServiceImpl personService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personService.getPersonByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", null, "This email address is already in use");
        }
    }
}
