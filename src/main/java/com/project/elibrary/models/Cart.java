package com.project.elibrary.models;


import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String bookName;

    @Column(name = "author")
    private String authors;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_ID")
    private User user;
    
    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
        

    public Cart() {
    }

    public Cart(Long id, String bookName, String authors, String thumbnailUrl, User user, double price, int quantity, double totalPrice, Book book) {
        this.id = id;
        this.bookName = bookName;
        this.authors = authors;
        this.thumbnailUrl = thumbnailUrl;
        this.user = user;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.book = book;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthors() {
        return this.authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        double bookPrice = book.getPrice(); // Assuming there is a `price` field in the `Book` entity
        return quantity * bookPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Cart id(Long id) {
        setId(id);
        return this;
    }

    public Cart bookName(String bookName) {
        setBookName(bookName);
        return this;
    }

    public Cart authors(String authors) {
        setAuthors(authors);
        return this;
    }

    public Cart thumbnailUrl(String thumbnailUrl) {
        setThumbnailUrl(thumbnailUrl);
        return this;
    }

    public Cart user(User user) {
        setUser(user);
        return this;
    }

    public Cart price(double price) {
        setPrice(price);
        return this;
    }

    public Cart quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public Cart totalPrice(double totalPrice) {
        setTotalPrice(totalPrice);
        return this;
    }

    public Cart book(Book book) {
        setBook(book);
        return this;
    }

   
    }
  
 
   
    


    
