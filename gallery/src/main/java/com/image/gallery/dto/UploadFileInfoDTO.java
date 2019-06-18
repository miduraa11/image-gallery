package com.image.gallery.dto;

import com.image.gallery.model.People;
import com.image.gallery.model.Tag;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UploadFileInfoDTO {

    private Long imageId;
    private String username;
    private List<People> people;
    private List<Tag> tags;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
