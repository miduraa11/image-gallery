package com.image.gallery.dto;

import com.image.gallery.model.People;
import com.image.gallery.model.Tag;

import java.util.List;

public class ImageFilterDTO {

    private String username;
    private People person;
    private List<Tag> tags;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
