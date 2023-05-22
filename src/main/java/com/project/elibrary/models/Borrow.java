package com.project.elibrary.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.project.elibrary.util.StringListConverter;

@Entity
@Table(name = "borrowedBooks")
public class Borrow {

    @OneToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_name")
    private String bookName;

    @Convert(converter = StringListConverter.class)
    @Column(name = "book_authors")
    private List<String> authors;

    @Column(name = "book_image")
    private String image;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public Borrow() {
    }

    public Borrow(Long id, Long bookId, String bookName, List<String> authors, String image, Long userID,
            String description, LocalDate startDate, LocalDate endDate) {
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

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
