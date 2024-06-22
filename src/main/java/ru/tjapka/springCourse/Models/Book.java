package ru.tjapka.springCourse.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @Max(value = 2024, message = "max 2024")
    @Column(name = "year")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;
    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;
    @Transient
    private boolean expired;

}
