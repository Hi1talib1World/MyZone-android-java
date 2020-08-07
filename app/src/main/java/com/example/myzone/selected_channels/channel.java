package com.example.myzone.selected_channels;

public class channel {
    private Long id;
    private String name;
    private String emailAddress;
    private int imageId;
    private String imagePath;

    public channel() {
    }
    public channel(Long id, String name,String emailAddress ,int imageId ,String imagePath ) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.imageId=imageId;
        this.imagePath=imagePath;
        this.id=id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}