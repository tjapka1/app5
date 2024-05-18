package ru.tjapka.springCourse.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tjapka.springCourse.Models.Person;
import ru.tjapka.springCourse.Reposotory.PersonRepository;
import ru.tjapka.springCourse.Service.stand.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonServiceImpl() {
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(long id) {
        List<Person> all = getAllPersons();
        return all.stream().filter(person1 -> person1.getId() == id).findAny().orElse(null);
    }

    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
        }

    @Override
    public Person updatePerson(long id, Person updatedPerson) {
        Person toUpdatePerson = getPersonById(id);
        toUpdatePerson.setFirstName(updatedPerson.getFirstName());
        toUpdatePerson.setLastName(updatedPerson.getLastName());
        toUpdatePerson.setYear(updatedPerson.getYear());
        personRepository.save(toUpdatePerson);
        return toUpdatePerson;
    }


    @Override
    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }
}
