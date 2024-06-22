package ru.tjapka.springCourse.Reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tjapka.springCourse.Models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(nativeQuery = true, value = "select person.* from book join person on book.person_id = person.person_id where book.book_id =?1")
    Person findOwner(long bookId);

    Optional<Person> findDistinctByLastNameAndFirstName(String lastName, String firstName);
    Optional<Person> findByFirstNameStartingWith(String firstName);
    Optional<Person> findByLastNameStartingWith(String lastName);
    Optional<Person> findPersonByEmail(String email);
    Optional<Person> findPersonByUserName(String username);
}
