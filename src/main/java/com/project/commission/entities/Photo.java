/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.entities;

/**
 *
 * @author PC
 */
public class Photo {
    private String photo_id;
    private String photo_name;
    private String photo_url;
    private String photo_owner;

    
    public Photo(){
     
    }
    public Photo(String photo_id, String photo_name, String photo_url) {
        this.photo_id = photo_id;
        this.photo_name = photo_name;
        this.photo_url = photo_url;
    }

    public String getPhoto_owner() {
        return photo_owner;
    }

    public void setPhoto_owner(String photo_owner) {
        this.photo_owner = photo_owner;
    }
    
    
  
    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public String getPhoto_url() {
        return photo_url;
    }
}
