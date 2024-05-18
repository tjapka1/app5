package ru.tjapka.springCourse.DTO;

import lombok.*;
import ru.tjapka.springCourse.Models.Book;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {
        private long id;
        private String firstName;
        private String lastName;
        private int year;
        private List<Book> books;

    }
