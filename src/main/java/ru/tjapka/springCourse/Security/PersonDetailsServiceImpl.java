package ru.tjapka.springCourse.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tjapka.springCourse.Models.Person;
import ru.tjapka.springCourse.Reposotory.PersonRepository;
import ru.tjapka.springCourse.Security.MyPersonDatails;

import java.util.Optional;
@Service
public class PersonDetailsServiceImpl implements UserDetailsService {

    @Autowired
     private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> personByUserName = personRepository.findPersonByUserName(username);

        return personByUserName.map(MyPersonDatails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }
}
