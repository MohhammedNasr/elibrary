package com.project.elibrary.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "borrowedBooks")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    public Borrow() {
    }

    public Borrow(Long id, Long userID, LocalDate startDate, LocalDate endDate, Book book) {
        this.id = id;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.book = book;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrow id(Long id) {
        setId(id);
        return this;
    }

    public Borrow userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public Borrow startDate(LocalDate startDate) {
        setStartDate(startDate);
        return this;
    }

    public Borrow endDate(LocalDate endDate) {
        setEndDate(endDate);
        return this;
    }

    public Borrow book(Book book) {
        setBook(book);
        return this;
    }

}
