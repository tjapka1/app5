package ru.tjapka.springCourse.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;
    @NotEmpty(message = "Title should be not empty")
    @Size(min = 1, max = 200, message = "Title should be between 2 and 200 characters")
    @Column(name = "title")
    @NotNull
    private String title;
    @NotEmpty(message = "author should be not empty")
    @Size(min = 1, max = 200, message = "Author should be between 2 and 200 characters")
    @Column(name = "author")
    @NotNull
    private String author;
    @NotNull
    @Column(name = "year")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
