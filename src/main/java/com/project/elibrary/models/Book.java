package com.project.elibrary.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.project.elibrary.util.StringListConverter;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_ID")
    private Long userID;

    @Column(nullable = false)
    private String title;

    @Column(length = 10000)
    private String description;

    @Convert(converter = StringListConverter.class)
    @Column(name = "book_authors")
    private List<String> authors;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "admin_review")
    private Boolean reviewed;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "published_date")
    private String publishedDate;

    @Column(name = "average_rating")
    private Double averageRating;

    @OneToOne(mappedBy = "book",cascade = CascadeType.ALL)
    private Borrow borrowedBooks;
    
    // Constructors, getters, and setters
    public Book() {

    }

    public Book(String title, String description, List<String> authors, String thumbnailUrl, Long userID) {
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.thumbnailUrl = thumbnailUrl;
        this.userID = userID;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Boolean getReviewed() {
        return reviewed;
    }

    public void setReviewed(Boolean reviewed) {
        this.reviewed = reviewed;
    }

    public Borrow getBorrowedBooks() {
        return borrowedBooks;
    }
    
    public void setBorrowedBooks(Borrow borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
