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
public class Order {
    private String order_id;
    private String customer_name;
    private String customer_id;
    private String scale;
    private String type;
    private int price;
    private int work_status;
    private Timestamp timestamp;
    private String post_id;
    private String order_owner;
    private String contact;

    public Order() {
    }

    public Order(String order_id, String customer_name, String customer_id, String scale, String type, int price, int work_status, Timestamp timestamp, String post_id, String order_owner, String contact) {
        this.order_id = order_id;
        this.customer_name = customer_name;
        this.customer_id = customer_id;
        this.scale = scale;
        this.type = type;
        this.price = price;
        this.work_status = work_status;
        this.timestamp = timestamp;
        this.post_id = post_id;
        this.order_owner = order_owner;
        this.contact = contact;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWork_status() {
        return work_status;
    }

    public void setWork_status(int work_status) {
        this.work_status = work_status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        Timestamp t = Timestamp.now();
        if(timestamp!=null){
            this.timestamp = timestamp;
        }else{
            this.timestamp = t;
        } 
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getOrder_owner() {
        return order_owner;
    }

    public void setOrder_owner(String order_owner) {
        this.order_owner = order_owner;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
}
