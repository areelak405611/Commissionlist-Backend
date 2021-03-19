/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.controller;

import com.project.commission.entities.Post;
import com.project.commission.service.PostService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author arl
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PostController {
    
    @Autowired
    PostService post_service;
    
    @PostMapping("/getposts")
    public ResponseEntity<List<Map<String, Object>>> getPostsByUserId(@RequestBody String id){
        try {
            List<Map<String, Object>> posts = post_service.getAllPostByUserId(id);
            if(posts != null){
                return new ResponseEntity<>(posts,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addpost")
    public ResponseEntity<HttpStatus> addPost(@RequestBody Post post){
        try {
            post_service.addPost(post);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @PostMapping("/editpost")
    public ResponseEntity<HttpStatus> editPost(@RequestBody Post post){
        try {
            post_service.editPost(post);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @PostMapping("/getonepost")
    public ResponseEntity<Post> getPost(@RequestBody Post post){
        try {
            Post post_ = post_service.getPost(post);
            if(post_ != null){
                return new ResponseEntity<>(post_,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getallpost")
    public ResponseEntity<List<Map<String, Object>>> getAllPost(){
        try {
            List<Map<String, Object>> posts = post_service.getAllPost();
            if(posts != null){
                return new ResponseEntity<>(posts,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getposttt/{uid}/{pid}")
    public Object getPostss(@PathVariable String uid, @PathVariable String pid){
        try {
            Object post = post_service.getPostss(uid, pid);
            if(post != null){
                return new ResponseEntity<>(post,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
