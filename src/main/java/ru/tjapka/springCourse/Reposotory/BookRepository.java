package ru.tjapka.springCourse.Reposotory;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tjapka.springCourse.Models.Book;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book>findByTitleStartingWith(String title);

    @Query(nativeQuery = true, value = "select * from book b where b.person_id=?1")
    List<Book> findAllBooksByPersonId(Long id);

    @Query(value = "select * from book b where b.book_id=?1", nativeQuery = true)
    Book findBookById(long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update book set person_id=null, taken_at=null where book_id=?1")
    void release(long bookId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update book set person_id=?2, taken_at=?3 where book_id=?1")
    void assign(long bookId, long personId, Date takeAt);
}