/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.controller;

import com.project.commission.entities.Photo;
import com.project.commission.entities.Post;
import com.project.commission.service.GalleryService;
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
 * @author PC
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GalleryController {
    
     @Autowired
      GalleryService photo_service;
     
     @PostMapping("/getphoto")
    public ResponseEntity<List<Map<String, Object>>> getPhotoByUserId(@RequestBody String id){
        try {
            List<Map<String, Object>> photo = photo_service.getAllPhotoByUserId(id);
            if(photo != null){
                return new ResponseEntity<>(photo,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @PostMapping("/addphoto")
    public ResponseEntity<HttpStatus> addPhoto(@RequestBody Photo photo){
        try {
            photo_service.addPhoto(photo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @GetMapping("/getphotopost/{uid}/{pid}")
    public ResponseEntity<List<Map<String, Object>>> getPhotoPost(@PathVariable String uid,@PathVariable String pid){
        try {
            List<Map<String, Object>> objs = photo_service.getAllPhotoPostByID(uid, pid);
            return new ResponseEntity<>(objs,HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
     
}
