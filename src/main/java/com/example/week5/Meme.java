package com.example.week5;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    private String shortDesc;
    private int likeCount;
    private String todayDate;

    public String getShortDesc() { return shortDesc; }

    public void setShortDesc(String shortDesc) {        this.shortDesc = shortDesc; }

    public String getDescription() {        return description; }

    public void setDescription(String description) {        this.description = description; }

    public String getImage() {        return image; }

    public void setImage(String image) {        this.image = image; }

    public long getId() {        return id; }

    public void setId(long id) {        this.id = id; }

    public String getFirstCaption() {        return firstCaption; }

    public void setFirstCaption(String fistCaption) {        this.firstCaption = fistCaption; }

    public String getSecondCaption() {        return secondCaption; }

    public void setSecondCaption(String secondCaption) {        this.secondCaption = secondCaption; }

    public int getLikeCount() {        return likeCount; }

    public void setLikeCount(int likeCount) {        this.likeCount = likeCount; }

    public String getTodayDate() {        return todayDate; }


    public void setTodayDate(LocalDate todayDate) {
        DateTimeFormatter todayInfo =  DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.todayDate = todayDate.format(todayInfo);
    }

    public String lengthOfString(String text) {
        String result = "";
        text = description;

        if (text.length()>20) {
            for (int i=0; i < 20 ;i++) {
                result+=text.charAt(i);
            }
            return result +"...";
        }
        else {
            return description;
        }
    }
}
