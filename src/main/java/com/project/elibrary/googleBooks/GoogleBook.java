package com.project.elibrary.googleBooks;

public class GoogleBook {
    private String id;
    private GoogleVolumeInfo volumeInfo;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public GoogleVolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
    
    public void setVolumeInfo(GoogleVolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
