/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.controller;

import com.project.commission.entities.User;
import com.project.commission.service.UserService;
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
public class UserController {
    @Autowired
    UserService userservice;
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        try {
            User user1 = userservice.userLogin(user.getUsername(), user.getPassword());
            if(user1 != null){
                return new ResponseEntity<>(user1,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user)  {
        try {
            if(userservice.saveUser(user)){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @PostMapping("/edituser")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user)  {
        try {
            userservice.editUser(user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @PostMapping("/editimage")
    public ResponseEntity<HttpStatus> editImage(@RequestBody User user)  {
        userservice.addImage(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);        
    }
    
    @PostMapping("/getuserdetail")
    public ResponseEntity<User> getUserDetail(@RequestBody String id) throws InterruptedException, ExecutionException{
        try {
            User user = userservice.getUserDetails(id);
            if(user != null){
                return new ResponseEntity<>(user,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/updateinfo")
    public ResponseEntity<HttpStatus> updateInfo(@RequestBody User user)  {
        userservice.editInfo(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);       
    }
    
    @PostMapping("/getimage")
    public ResponseEntity<String> getImageLink(@RequestBody String id) throws InterruptedException, ExecutionException{
        try {
            String url = userservice.getImageLink(id);
            if(url != null){
                return new ResponseEntity<>(url,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
}
