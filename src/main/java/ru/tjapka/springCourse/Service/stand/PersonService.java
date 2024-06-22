package ru.tjapka.springCourse.Service.stand;

import ru.tjapka.springCourse.Models.Book;
import ru.tjapka.springCourse.Models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getAllPersons();
    Person getPersonById(long id);
    Person addPerson(Person person);
    Person updatePerson(long id, Person updatedPerson);
    void deletePerson(long id);
    Optional<Person> getPersonByFirstNameOrLastName(String lastName, String firstName);
    Optional<Person> getPersonByFirstName(String firstName);
    Optional<Person> getPersonByLastName(String lastName);
    Optional<Person> getPersonByEmail(String email);
    List<Book> getBooksByPersonId(long personId);
    public Optional<Person> getPersonByUserName(String username);

}
