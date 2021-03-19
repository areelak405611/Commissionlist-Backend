/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.controller;

import com.project.commission.entities.Post;
import com.project.commission.entities.Review;
import com.project.commission.service.ReviewService;
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
public class ReviewController {
    @Autowired
    ReviewService review_service;
    
    @PostMapping("/getallreview")
    public ResponseEntity<List<Map<String, Object>>> getReviewByUserId(@RequestBody String id){
        try {
            List<Map<String, Object>> reviews = review_service.getAllReviewByUserId(id);
            if(reviews != null){
                return new ResponseEntity<>(reviews,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/getreviews")
    public ResponseEntity<List<Map<String, Object>>> getReviewByPost(@RequestBody Post post){
        try {
            List<Map<String, Object>> reviews = review_service.getReviewByPost(post);
            if(reviews != null){
                return new ResponseEntity<>(reviews,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addreview")
    public ResponseEntity<HttpStatus> addReview(@RequestBody Review review)  {
        try {
            review_service.addReview(review);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
}
