package com.image.gallery.service;

import com.image.gallery.dto.CreatePersonDTO;
import com.image.gallery.message.response.ResponseMessage;
import com.image.gallery.model.Image;
import com.image.gallery.model.People;
import com.image.gallery.model.User;
import com.image.gallery.repository.ImageRepository;
import com.image.gallery.repository.PeopleRepository;
import com.image.gallery.repository.UserRepository;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;

    public ResponseEntity<?> createPerson(CreatePersonDTO person) {
        User user = userRepository.findByUsername(person.getUsername());
        List<People> people = user.getPeople();
        boolean personExists = people.stream()
                .anyMatch(x -> x.getName().equals(person.getPerson()));

        if (personExists)
            return new ResponseEntity<>(new ResponseMessage("Błąd - osoba już istnieje!"), HttpStatus.BAD_REQUEST);

        People newPerson = new People();
        newPerson.setName(person.getPerson());
        newPerson.setUser(user);
        peopleRepository.save(newPerson);
        return new ResponseEntity<>(new ResponseMessage("Osoba została dodana!"), HttpStatus.OK);
    }

    public List<People> getAllPeople(String username) {
        User user = userRepository.findByUsername(username);
        List<People> people = user.getPeople();
        return people;
    }

    @OnDelete(action = OnDeleteAction.CASCADE)
    public ResponseEntity<?> deletePerson(Long id) {
        try{
            peopleRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseMessage("Pomyślnie usunięto"), HttpStatus.OK);
        } catch(Exception x) {
            return new ResponseEntity<>(new ResponseMessage("Błąd - osoba osoba nie została usunięta!"), HttpStatus.BAD_REQUEST);
        }

    }

    public List<People> getAllPeopleByImage(String image) {
        Image img = imageRepository.findByName(image);
        return img.getPeoples();
    }
}
