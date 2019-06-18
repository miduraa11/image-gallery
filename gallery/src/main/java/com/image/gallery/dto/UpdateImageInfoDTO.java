package com.image.gallery.dto;

import com.image.gallery.model.People;
import com.image.gallery.model.Tag;

import java.util.List;

public class UpdateImageInfoDTO {

    private String image;
    private List<People> people;
    private List<Tag> tags;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
