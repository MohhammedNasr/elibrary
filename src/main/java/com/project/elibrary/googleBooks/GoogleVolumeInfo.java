package com.project.elibrary.googleBooks;

import java.util.List;

public class GoogleVolumeInfo {
    
    private String title;
    private List<String> authors;
    private String description;
    private GoogleImageLinks imageLinks;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<String> getAuthors() {
        return authors;
    }
    
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public GoogleImageLinks getImageLinks() {
        return imageLinks;
    }
    
    public void setImageLinks(GoogleImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}
