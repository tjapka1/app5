package ru.tjapka.springCourse.Reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tjapka.springCourse.Models.Book;
import ru.tjapka.springCourse.Models.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(nativeQuery = true, value = "select person.* from book join person on book.person_id = person.person_id where book.book_id =?1")
    Person findOwner(long bookId);




}
