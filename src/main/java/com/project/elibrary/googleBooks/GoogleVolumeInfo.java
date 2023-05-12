package com.project.elibrary.googleBooks;

import java.util.Arrays;
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
        if(title == null || title == ""){
            return "No title";
        }
        else{
            return title;
        }
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<String> getAuthors() {
        if(authors == null || authors.isEmpty()){
            return Arrays.asList("No author info");
        }
        else{
            return authors;
        }
    }
    
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    
    public String getDescription() {
        if(description == null || description == ""){
            return "There is no description for this book :(";
        }
        else{
            return description;
        }
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
        if(pageCount == null || pageCount == 0){
            return 0;
        }
        else{
            return pageCount;
        }
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublishedDate() {
        if(publishedDate == null || publishedDate == ""){
            return "unknown publish date";
        }
        else{
            return publishedDate;
        }
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Double getAverageRating() {
        if(averageRating == null || averageRating == 0){
            return 0.0;
        }
        else{
            return averageRating;
        }
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
