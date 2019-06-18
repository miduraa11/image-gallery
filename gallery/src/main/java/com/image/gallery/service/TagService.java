package com.image.gallery.service;

import com.image.gallery.message.response.ResponseMessage;
import com.image.gallery.model.Image;
import com.image.gallery.model.Tag;
import com.image.gallery.repository.ImageRepository;
import com.image.gallery.repository.TagRepository;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;
    @Autowired
    ImageRepository imageRepository;

    public ResponseEntity<?> createNewTag(String tag) {
        List<Tag> tags = tagRepository.findAll();
        boolean tagExists = tags.stream()
                .anyMatch(x -> x.getName().equals(tag));

        if(tagExists)
            return new ResponseEntity<>(new ResponseMessage("Błąd - tag już istnieje!"), HttpStatus.BAD_REQUEST);

        Tag newTag = new Tag();
        newTag.setName(tag);
        tagRepository.save(newTag);
        return new ResponseEntity<>(new ResponseMessage("Dodano nowy tag"), HttpStatus.OK);
    }


    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @OnDelete(action = OnDeleteAction.CASCADE)
    public ResponseEntity<String> deleteTag(Long id) {
        try{
            tagRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Pomyślnie usunięto");
        } catch(Exception x) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Błąd - tag nie został usunięty!");
        }
    }

    public List<Tag> getAllImageTags(String image) {
        Image img = imageRepository.findByName(image);
        return img.getTags();
    }
}
