package com.image.gallery.model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long id;

    @Column(name = "image_name")
    private String name;

    @Column(name = "add_date")
    private Date date;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "people_image", joinColumns = @JoinColumn(name = "image_id"), inverseJoinColumns = @JoinColumn(name = "people_id"))
    private List<People> peoples;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "image_tags", joinColumns = @JoinColumn(name = "image_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    public Image(long id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Image() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser()    {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
