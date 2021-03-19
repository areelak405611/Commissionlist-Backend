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
public class Review {
    private String review_id;
    private int point;
    private String text_review;
    private Timestamp time_stamp;
    private String reviewer;
    private String post_id;
    private String uid;

    public Review() {
    }

    public Review(String review_id, int point, String text_review, Timestamp time_stamp, String reviewer, String post_id, String uid) {
        this.review_id = review_id;
        this.point = point;
        this.text_review = text_review;
        this.time_stamp = time_stamp;
        this.reviewer = reviewer;
        this.post_id = post_id;
        this.uid = uid;
    }

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getText_review() {
        return text_review;
    }

    public void setText_review(String text_review) {
        this.text_review = text_review;
    }

    public Timestamp getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Timestamp timestamp) {
        Timestamp t = Timestamp.now();
        if(timestamp!=null){
            this.time_stamp = timestamp;
        }else{
            this.time_stamp = t;
        } 
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
 
}
