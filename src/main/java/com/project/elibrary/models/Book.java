package com.project.elibrary.models;

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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.project.elibrary.util.StringListConverter;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private double averageRating;

    @OneToOne(mappedBy = "book",cascade = CascadeType.ALL)
    private Borrow borrowedBooks;


    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_ID")
    private User user;


    @Column (name="Price")
private Double price ;  

    public Book() {
    }

    public Book(Long id, String title, String description, List<String> authors, String thumbnailUrl, Boolean availability, Boolean reviewed, Integer pageCount, String publishedDate, double averageRating, Borrow borrowedBooks, User user, Double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.thumbnailUrl = thumbnailUrl;
        this.availability = availability;
        this.reviewed = reviewed;
        this.pageCount = pageCount;
        this.publishedDate = publishedDate;
        this.averageRating = averageRating;
        this.borrowedBooks = borrowedBooks;
        this.user = user;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Boolean isAvailability() {
        return this.availability;
    }

    public Boolean getAvailability() {
        return this.availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Boolean isReviewed() {
        return this.reviewed;
    }

    public Boolean getReviewed() {
        return this.reviewed;
    }

    public void setReviewed(Boolean reviewed) {
        this.reviewed = reviewed;
    }

    public Integer getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublishedDate() {
        return this.publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public double getAverageRating() {
        return this.averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Borrow getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public void setBorrowedBooks(Borrow borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Book id(Long id) {
        setId(id);
        return this;
    }

    public Book title(String title) {
        setTitle(title);
        return this;
    }

    public Book description(String description) {
        setDescription(description);
        return this;
    }

    public Book authors(List<String> authors) {
        setAuthors(authors);
        return this;
    }

    public Book thumbnailUrl(String thumbnailUrl) {
        setThumbnailUrl(thumbnailUrl);
        return this;
    }

    public Book availability(Boolean availability) {
        setAvailability(availability);
        return this;
    }

    public Book reviewed(Boolean reviewed) {
        setReviewed(reviewed);
        return this;
    }

    public Book pageCount(Integer pageCount) {
        setPageCount(pageCount);
        return this;
    }

    public Book publishedDate(String publishedDate) {
        setPublishedDate(publishedDate);
        return this;
    }

    public Book averageRating(double averageRating) {
        setAverageRating(averageRating);
        return this;
    }

    public Book borrowedBooks(Borrow borrowedBooks) {
        setBorrowedBooks(borrowedBooks);
        return this;
    }

    public Book user(User user) {
        setUser(user);
        return this;
    }

    public Book price(Double price) {
        setPrice(price);
        return this;
    }


    }


