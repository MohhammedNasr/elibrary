package com.project.elibrary.googleBooks;

import java.util.List;

public class GoogleVolumeInfo {
    
    private String title;
    private List<String> authors;
    private String description;
    private GoogleImageLinks imageLinks;
    private Integer pageCount;
    private String publishedDate;
    private Double averageRating;
    
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
}
