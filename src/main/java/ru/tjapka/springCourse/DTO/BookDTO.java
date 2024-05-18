package ru.tjapka.springCourse.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tjapka.springCourse.Models.Person;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookDTO {
    private String title;
    private String author;
    private int year;
    private Person person;
}
