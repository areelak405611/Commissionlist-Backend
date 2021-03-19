/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.controller;

import com.project.commission.entities.Order;
import com.project.commission.service.OrderService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author arl
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {
    
    @Autowired
    OrderService order_service;
    
    @PostMapping("/getorders")
    public ResponseEntity<List<Map<String, Object>>> getOrdersByUserId(@RequestBody String id){
        try {
            List<Map<String, Object>> orders = order_service.getAllOrderByUserId(id);
            if(orders != null){
                return new ResponseEntity<>(orders,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/getmyorders")
    public ResponseEntity<List<Map<String, Object>>> getMyOrdersByUserId(@RequestBody String id){
        try {
            List<Map<String, Object>> orders = order_service.getAllMyOrderByUserId(id);
            if(orders != null){
                return new ResponseEntity<>(orders,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/changeworkstatus")
    public ResponseEntity<Order> changeWorkStatus(@RequestBody Order order){
        try {
            order_service.changeWorkStatusOrder(order);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addorder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        try {
            order_service.addOrder(order);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
