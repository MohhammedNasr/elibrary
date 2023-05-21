package com.project.elibrary.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "borrowedBooks")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "book_name")
    private String bookName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "book_authors")
    private List<String> authors;

    @Column(name = "book_image")
    private String image;

    @Column(name = "user_ID")
    private Long userID;

    @Column(name = "description")
    private String description;

    @Column(name = "startDate")
    private  LocalDate startDate;

    @Column(name = "endDate")
    private String endDate;

    public Borrow() {
    }

    public Borrow(Long id, Long bookId, String bookName, List<String> authors, String image, Long userID, String description, LocalDate startDate, String endDate) {
        this.id = id;
        this.bookId = bookId;
        this.bookName = bookName;
        this.authors = authors;
        this.image = image;
        this.userID = userID;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return this.bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Borrow id(Long id) {
        setId(id);
        return this;
    }

    public Borrow bookId(Long bookId) {
        setBookId(bookId);
        return this;
    }

    public Borrow bookName(String bookName) {
        setBookName(bookName);
        return this;
    }

    public Borrow authors(List<String> authors) {
        setAuthors(authors);
        return this;
    }

    public Borrow image(String image) {
        setImage(image);
        return this;
    }

    public Borrow userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public Borrow description(String description) {
        setDescription(description);
        return this;
    }

    public Borrow startDate(LocalDate startDate) {
        setStartDate(startDate);
        return this;
    }

    public Borrow endDate(String endDate) {
        setEndDate(endDate);
        return this;
    }

    
    }



