package com.project.elibrary.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String bookName;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    
    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;  
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_ID")
    private User user;

    public Cart() {
    }

    public Cart(Long id, String bookName,  String thumbnailUrl, 
    User user, double price, int quantity, double totalPrice) {
        this.id = id;
        this.bookName = bookName;
        this.thumbnailUrl = thumbnailUrl;
        this.user = user;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
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

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = this.quantity*this.price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Cart id(Long id) {
        setId(id);
        return this;
    }

    public Cart bookName(String bookName) {
        setBookName(bookName);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}