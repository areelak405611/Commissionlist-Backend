/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.entities;

/**
 *
 * @author arl
 */
public class Image {
    private String image_id;
    private String image_path;
    private String type;
    private byte[] picbyte;

    public Image() {
    }

    public Image(String image_id, String image_path, String type, byte[] picbyte) {
        this.image_id = image_id;
        this.image_path = image_path;
        this.type = type;
        this.picbyte = picbyte;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicbyte() {
        return picbyte;
    }

    public void setPicbyte(byte[] picbyte) {
        this.picbyte = picbyte;
    }
    
}
