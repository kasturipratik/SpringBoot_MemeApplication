package com.example.week5;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=4)
    private String firstCaption;

    @NotNull
    @Size(min=4)
    private String secondCaption;

    @NotNull
    @Size(min=10)
    private String description;

    private String image;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstCaption() {
        return firstCaption;
    }

    public void setFirstCaption(String fistCaption) {
        this.firstCaption = fistCaption;
    }

    public String getSecondCaption() {
        return secondCaption;
    }

    public void setSecondCaption(String secondCaption) {
        this.secondCaption = secondCaption;
    }
}
