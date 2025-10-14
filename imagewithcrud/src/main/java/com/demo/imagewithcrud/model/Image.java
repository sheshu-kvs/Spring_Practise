package com.demo.imagewithcrud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Image{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imagename;
    private String  imagetype;
    @Lob                       //this will be the Large Binary Object it will store the image file like the 
    private byte[] imagedata;  //we are storing the data in the form  of the byte format...
    
    
    public Image() {
    }


    public Image(long id, String imagename, String imagetype, byte[] imagedata) {
        this.id = id;
        this.imagename = imagename;
        this.imagetype = imagetype;
        this.imagedata = imagedata;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getImagename() {
        return imagename;
    }


    public void setImagename(String imagename) {
        this.imagename = imagename;
    }


    public String getImagetype() {
        return imagetype;
    }


    public void setImagetype(String imagetype) {
        this.imagetype = imagetype;
    }


    public byte[] getImagedata() {
        return imagedata;
    }


    public void setImagedata(byte[] imagedata) {
        this.imagedata = imagedata;
    }
}