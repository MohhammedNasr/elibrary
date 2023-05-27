package com.project.elibrary.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String profilePic;
    private String role;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    @Fetch(FetchMode.SELECT)
    @OrderColumn(name = "LIST_INDEX")
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Borrow> borrow;

   
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Cart> cart; 

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public void addToCart(Cart cart)
    {
        this.cart.add(cart);
    }
    public User() {
        this.role = "User";
    }

    public User(Long id, String username, String email, String password, String profilePic) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.role = "User";
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return this.profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority(this.role)));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;

    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public void addToFavorites(Favorite favorite){
        this.favorites.add(favorite);
    }

    public void addBook(Book books){
        this.books.add(books);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Set<Borrow> getBorrow() {
        return borrow;
    }
    public void setBorrow(Set<Borrow> borrow) {
        this.borrow = borrow;
    }

}