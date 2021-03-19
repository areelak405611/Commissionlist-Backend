/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.entities;

import com.google.cloud.Timestamp;

/**
 *
 * @author arl
 */
public class Post {
    private String post_id;
    private String title;
    private String text;
    private boolean status;
    private String post_owner;
    private Timestamp timestamp;

    public Post() {
    }

    public Post(String post_id, String title, String text, boolean status, String post_owner, Timestamp timestamp) {
        this.post_id = post_id;
        this.title = title;
        this.text = text;
        this.status = status;
        this.post_owner = post_owner;
        this.timestamp = timestamp;
    }

    
    
    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPost_owner() {
        return post_owner;
    }

    public void setPost_owner(String post_owner) {
        this.post_owner = post_owner;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        if(timestamp != null){
            this.timestamp = timestamp;
        }else{
            Timestamp t = Timestamp.now();
            this.timestamp = t;
        }
        
    }
    
    
    
}
