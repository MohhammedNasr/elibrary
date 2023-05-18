package com.project.elibrary.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.type.Date;

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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authors;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
   

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "review")
    private Boolean reviewed;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "published_date")
    private String publishedDate;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "user_ID")
    private Long userID;
    @Column(name = "borrowed")
    private boolean borrowed;
 @ManyToOne
  @JoinColumn(name = "borrower_id")
  private User borrower;
  @Column(name = "BorrowedDate")
  private Date BorrowedDate;

  
    public Book() {
    }
  
    public Book(  String title,String description, List<String> authors,String thumbnailUrl,Long userID ) 
    {
    
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.thumbnailUrl = thumbnailUrl;
       
        this.userID = userID;
      
    }

    public Book(Long id, String title, String description, List<String> authors, String thumbnailUrl, Boolean availability, Boolean reviewed, Integer pageCount, String publishedDate, Double averageRating, Long userID, boolean borrowed, User borrower, Date BorrowedDate) {
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
        this.userID = userID;
        this.borrowed = borrowed;
        this.borrower = borrower;
        this.BorrowedDate = BorrowedDate;
    }

    public boolean isBorrowed() {
        return this.borrowed;
    }

    public boolean getBorrowed() {
        return this.borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public Date getBorrowedDate() {
        return this.BorrowedDate;
    }

    public void setBorrowedDate(Date BorrowedDate) {
        this.BorrowedDate = BorrowedDate;
    }

    public Book borrowed(boolean borrowed) {
        setBorrowed(borrowed);
        return this;
    }

    public Book BorrowedDate(Date BorrowedDate) {
        setBorrowedDate(BorrowedDate);
        return this;
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

    public Double getAverageRating() {
        return this.averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public User getBorrower() {
        return this.borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
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

    public Book averageRating(Double averageRating) {
        setAverageRating(averageRating);
        return this;
    }

    public Book userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public Book borrower(User borrower) {
        setBorrower(borrower);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(description, book.description) && Objects.equals(authors, book.authors) && Objects.equals(thumbnailUrl, book.thumbnailUrl) && Objects.equals(availability, book.availability) && Objects.equals(reviewed, book.reviewed) && Objects.equals(pageCount, book.pageCount) && Objects.equals(publishedDate, book.publishedDate) && Objects.equals(averageRating, book.averageRating) && Objects.equals(userID, book.userID) && Objects.equals(borrower, book.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, authors, thumbnailUrl, availability, reviewed, pageCount, publishedDate, averageRating, userID, borrower);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", authors='" + getAuthors() + "'" +
            ", thumbnailUrl='" + getThumbnailUrl() + "'" +
            ", availability='" + isAvailability() + "'" +
            ", reviewed='" + isReviewed() + "'" +
            ", pageCount='" + getPageCount() + "'" +
            ", publishedDate='" + getPublishedDate() + "'" +
            ", averageRating='" + getAverageRating() + "'" +
            ", userID='" + getUserID() + "'" +
            ", borrower='" + getBorrower() + "'" +
            "}";
    }

}
