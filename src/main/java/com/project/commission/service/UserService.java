/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.project.commission.entities.User;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

/**
 *
 * @author arl
 */
@Service
public class UserService {
    
    public User userLogin(String username, String password) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference userRef = dbFirestore.collection("users");
        Query query = userRef.whereEqualTo("username", username).whereEqualTo("password", password);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        DocumentSnapshot document = querySnapshot.get().getDocuments().get(0);
        User user = null;
        if(document.exists()) {
            user = document.toObject(User.class);
            return user;
        }else {
            return null;
        }
    }
    
    public User getUserDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        User user = null;
        if(document.exists()) {
            user = document.toObject(User.class);
            return user;
        }else {
            return null;
        }
    }
    
    public boolean saveUser(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference userRef = dbFirestore.collection("users");
        Query query = userRef.whereEqualTo("username", user.getUsername());
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        if(querySnapshot.get().isEmpty()) {
            ApiFuture<DocumentReference> collectionsApiFuture = dbFirestore.collection("users").add(user);
            String id = collectionsApiFuture.get().getId();
            Map<String, Object> updates = new HashMap<>();
            updates.put("id", id);
            DocumentReference docRef = dbFirestore.collection("users").document(id);
            ApiFuture<WriteResult> writeResult = docRef.update(updates);
            return true;
        }       
        return false;
    }
    
    public void editUser(User user) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users").document(user.getId());
        Map<String, Object> updates = new HashMap<>();
        updates.put("email", user.getEmail());
        //updates.put("password", user.getPassword());
        ApiFuture<WriteResult> writeResult = docRef.update(updates);   
    }
    
    public void addImage(User user) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users").document(user.getId());
        Map<String, Object> updates = new HashMap<>();
        updates.put("user_image", user.getUser_image());
        //updates.put("password", user.getPassword());
        ApiFuture<WriteResult> writeResult = docRef.update(updates);
    }
    
    public void editInfo(User user){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users").document(user.getId());
        Map<String, Object> updates = new HashMap<>();
        updates.put("info", user.getInfo());
        //updates.put("password", user.getPassword());
        ApiFuture<WriteResult> writeResult = docRef.update(updates);
    }
    
    public String getImageLink(String id) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(User.class).getUser_image();
        } else {
            return null;
        }
    }
    
}
