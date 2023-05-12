package com.project.elibrary.googleBooks;

import java.util.List;

public class GoogleBooksResponse {
    
    private List<GoogleBook> items;

    public List<GoogleBook> getItems() {
        return items;
    }

    public void setItems(List<GoogleBook> items) {
        this.items = items;
    }
}