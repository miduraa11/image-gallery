package com.image.gallery.controller;


import com.image.gallery.dto.UpdateImageInfoDTO;
import com.image.gallery.dto.UploadFileInfoDTO;
import com.image.gallery.dto.ViewImagesDTO;
import com.image.gallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(value = "/add-image")
    public Long addImage(@RequestParam("image") MultipartFile file, @RequestParam("username") String username) {
        return imageService.addNewImage(file, username);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(value = "/set-image-info")
    public ResponseEntity<String> setImageInformation(@RequestBody UploadFileInfoDTO uploadFileInfoDTO){
        return imageService.setImageInformation(uploadFileInfoDTO);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/get-all-images/{username}")
    List<ViewImagesDTO> getAllPeople(@PathVariable(name = "username") String username){
        return imageService.getAllImages(username);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(value = "/update-image-info")
    public Long updateImageInfo(@RequestBody UpdateImageInfoDTO updateImageInfoDTO){
        return imageService.updateImageInfo(updateImageInfoDTO);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/get-images-by-element/{username}/{person}/{tag}")
    List<ViewImagesDTO> getImagesByElement(@PathVariable(name = "username") String username, @PathVariable(name = "person") String person, @PathVariable(name = "tag") String tag){
        return imageService.getImagesByElement(username, person, tag);
    }
}
