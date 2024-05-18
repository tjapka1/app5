package ru.tjapka.springCourse.Service.stand;

import ru.tjapka.springCourse.Models.Person;

import java.util.List;

public interface PersonService {
    public List<Person> getAllPersons();
    public Person getPersonById(long id);
    public Person addPerson(Person person);
    public Person updatePerson(long id, Person updatedPerson);
    public void deletePerson(long id);

}
