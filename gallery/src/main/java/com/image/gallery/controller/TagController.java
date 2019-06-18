package com.image.gallery.controller;


import com.image.gallery.model.Tag;
import com.image.gallery.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-new-tag")
    public ResponseEntity<?> createTag(@RequestBody String tag){
        return tagService.createNewTag(tag);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/get-all-tags")
    List<Tag> getAllTags(){
        return tagService.getAllTags();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete-tag/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id")Long id){
        return tagService.deleteTag(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/get-image-tags/{image}")
    List<Tag> getAllImageTags(@PathVariable(name = "image") String image){
        return tagService.getAllImageTags(image);
    }
}
