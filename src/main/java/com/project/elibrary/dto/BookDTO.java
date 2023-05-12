package com.project.elibrary.dto;

import java.util.List;

public class BookDTO {

    private String title;

    private String description;

    private List<String> authors;

    private String thumbnailUrl;

    private Boolean availability;

    // Constructors, getters, and setters

    public BookDTO() {
        // Empty constructor
    }

    public BookDTO(String title, String description, List<String> authors, String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.thumbnailUrl = thumbnailUrl;
    }

    // Getters and setters

    public Boolean getAvailability() {
        return availability;
    }
    public void setAvailability(Boolean availability) {
        this.availability = availability;
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
}

