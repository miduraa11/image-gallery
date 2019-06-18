package com.image.gallery.dto;

public class CreatePersonDTO {

    private String username;
    private String person;


    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
