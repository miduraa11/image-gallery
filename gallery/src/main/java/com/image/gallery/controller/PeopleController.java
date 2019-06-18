package com.image.gallery.controller;

import com.image.gallery.dto.CreatePersonDTO;
import com.image.gallery.model.People;
import com.image.gallery.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    PeopleService peopleService;


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(value = "/add-new-person")
    public ResponseEntity<?> createPerson(@RequestBody CreatePersonDTO personDTO){
        return peopleService.createPerson(personDTO);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/get-all-people/{username}")
    List<People> getAllPeople(@PathVariable(name = "username") String username){
        return peopleService.getAllPeople(username);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping(value = "/delete-person/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(name = "id")Long id){
        return peopleService.deletePerson(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/get-all-people-by-image/{image}")
    List<People> getAllPeopleByImage(@PathVariable(name = "image") String image){
        return peopleService.getAllPeopleByImage(image);
    }

}
