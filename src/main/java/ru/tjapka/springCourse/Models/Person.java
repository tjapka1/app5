package ru.tjapka.springCourse.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;
    @NotEmpty(message = "Firstname should be not empty")
    @Size(min = 1, max = 200, message = "Firstname should be between 2 and 200 characters")
    @Column(name = "firstname")
    @NotNull
    private String firstName;
    @NotEmpty(message = "Lastname should be not empty")
    @Size(min = 1, max = 200, message = "Lastname should be between 2 and 200 characters")
    @Column(name = "lastname")
    @NotNull
    private String lastName;
    @NotNull
    @Column(name = "year")
    @Min(value = 1900, message = "min 1900")
    @Max(value = 2024, message = "max 2024")
    private int year;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Book> books;

}
