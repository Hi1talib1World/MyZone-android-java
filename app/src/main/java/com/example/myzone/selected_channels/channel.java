package com.example.myzone.selected_channels;

public class channel {
    private Long id;
    private String name;
    private String emailAddress;
    private int imageId;
    private String imagePath;
}
    public channel() {
    }
    public channel(Long id, String name,String emailAddress ,int imageId ,String imagePath ) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.imageId=imageId;
        this.imagePath=imagePath;
        this.id=id;

    }