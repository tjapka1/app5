package ru.tjapka.springCourse.Models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;
    @NotEmpty(message = "username should be not empty")
    @Size(min = 1, max = 200, message = "Firstname should be between 2 and 200 characters")
    @Column(name = "username", unique = true)
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;
    @NotEmpty(message = "Firstname should be not empty")
    @Size(min = 1, max = 200, message = "Firstname should be between 2 and 200 characters")
    @Column(name = "firstname")
    private String firstName;
    @NotEmpty(message = "Lastname should be not empty")
    @Size(min = 1, max = 200, message = "Lastname should be between 2 and 200 characters")
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email")
    @Valid()
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email should be not empty")
    private String email;
    @Enumerated(EnumType.STRING)
    private Mood mood;
//    @NotEmpty
//    @Min(value = 1900, message = "min 1900")
//    @Max(value = 2024, message = "max 2024")
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateOfBirth;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Book> books;

}
