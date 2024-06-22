package ru.tjapka.springCourse.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tjapka.springCourse.Models.Book;
import ru.tjapka.springCourse.Models.Mood;
import ru.tjapka.springCourse.Models.Person;
import ru.tjapka.springCourse.Reposotory.PersonRepository;
import ru.tjapka.springCourse.Service.stand.PersonService;
import ru.tjapka.springCourse.Util.PersonValidator;

import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    @Override
    public Person getPersonById(long id) {
    Optional<Person> person = personRepository.findById(id);
    return person.orElse(null);
    }
    @Override
    public Person addPerson(Person person) {
        Person savedPerson = Person.builder()
                .userName(person.getUserName())
                .password(passwordEncoder.encode(person.getPassword()))
                .roles("USER")
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .mood(Mood.CALM)
                .dateOfBirth(person.getDateOfBirth())
                .createdAt(new Date())
                .books(null)
                .build();
        return personRepository.save(savedPerson);
        }
    @Override
    public Person updatePerson(long id, Person updatedPerson) {
        Person toUpdatePerson = getPersonById(id);
        toUpdatePerson.setFirstName(updatedPerson.getFirstName());
        toUpdatePerson.setLastName(updatedPerson.getLastName());
        toUpdatePerson.setEmail(updatedPerson.getEmail());
        toUpdatePerson.setMood(updatedPerson.getMood());
        toUpdatePerson.setDateOfBirth(updatedPerson.getDateOfBirth());
        personRepository.save(toUpdatePerson);
        return toUpdatePerson;
    }
    @Override
    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }
    @Override
    public Optional<Person> getPersonByFirstNameOrLastName(String lastName, String firstName){
        return personRepository.findDistinctByLastNameAndFirstName(lastName, firstName);
    }
    @Override
    public Optional<Person> getPersonByFirstName(String firstName){
        return personRepository.findByFirstNameStartingWith(firstName);
    }
    @Override
    public Optional<Person> getPersonByLastName(String lastName){
        return personRepository.findByLastNameStartingWith(lastName);
    }
    @Override
    public Optional<Person> getPersonByEmail(String email){
        return personRepository.findPersonByEmail(email);
    }
    @Override
    public Optional<Person> getPersonByUserName(String username){return personRepository.findPersonByUserName(username);}
    @Override
    public List<Book> getBooksByPersonId(long personId){
        Optional<Person> person = personRepository.findById(personId);
        if(person.isPresent()){
            List<Book> books = person.get().getBooks();

            books.forEach(book -> {
                long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                if(diffInMillies > 864000000){
                    book.setExpired(true);
                }
            });
            return books;
        }
        else {
            return Collections.emptyList();
        }
    }

}
