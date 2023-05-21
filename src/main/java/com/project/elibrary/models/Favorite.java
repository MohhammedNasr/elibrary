package com.project.elibrary.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "authors")
    private String authors;

    @Column(name = "image")
    private String image;

    @Column(name = "user_ID")
    private Long userID;

    // getters and setters
    public String getAuthors() {
        return authors;
    }

    public String getBookName() {
        return bookName;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
