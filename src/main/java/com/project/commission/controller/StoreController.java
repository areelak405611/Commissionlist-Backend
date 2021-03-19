/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.controller;

import com.project.commission.entities.Store;
import com.project.commission.service.StoreService;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author arl
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StoreController {
    
    @Autowired
    StoreService store_service;
    
    @PostMapping("/getstoredetail")
    public ResponseEntity<Store> getStoreByUserId(@RequestBody String id){
        try {
            Store store = store_service.getStoreDetails(id);
            if(store != null){
                return new ResponseEntity<>(store,HttpStatus.OK);
            }
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addstore")
    public ResponseEntity<HttpStatus> addStore(@RequestBody Store store)  {
        try {
            store_service.addStore(store);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @PostMapping("/editinfostore")
    public ResponseEntity<HttpStatus> editInfo(@RequestBody Store store){
        try {
            store_service.editInfoStore(store);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}
