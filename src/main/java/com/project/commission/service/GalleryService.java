/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.project.commission.entities.Photo;
import com.project.commission.entities.Post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class GalleryService {
    
    Post post = new Post();
   
    public List<Map<String, Object>> getAllPhotoByUserId(String user_id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").document(user_id).collection("photo").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        //List<Post> posts = new ArrayList();
        List<Map<String, Object>> objects = new ArrayList();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Post.class));
            //posts.add(document.toObject(Post.class));
            Photo photo = document.toObject(Photo.class);
            Map<String, Object> obj = new HashMap<>();
            obj.put("photo_id", photo.getPhoto_id());
            obj.put("photo_name", photo.getPhoto_name());
            obj.put("photo_url", photo.getPhoto_url());
            obj.put("photo_owner", photo.getPhoto_owner());
            objects.add(obj);
        }
        return objects;
    }
    
    public void addPhoto(Photo photo) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference user_ref = dbFirestore.collection("users");
        ApiFuture<DocumentReference> collectionsApiFuture = user_ref.document(photo.getPhoto_owner()).collection("photo").add(photo);
        String id = collectionsApiFuture.get().getId();
            Map<String, Object> updates = new HashMap<>();
            updates.put("photo_id", id);
            //updates.put("timestamp", Timestamp.now());
            DocumentReference docRef = dbFirestore.collection("users").document(photo.getPhoto_owner()).collection("photo").document(id);
            ApiFuture<WriteResult> writeResult = docRef.update(updates);
    }

    public List<Map<String, Object>> getAllPhotoPostByID(String user_id, String post_id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").document(user_id).collection("posts").document(post_id).collection("postphotos").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        //List<Post> posts = new ArrayList();
        List<Map<String, Object>> objects = new ArrayList();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Post.class));
            //posts.add(document.toObject(Post.class));
            Photo photo = document.toObject(Photo.class);
            Map<String, Object> obj = new HashMap<>();
            obj.put("photo_id", photo.getPhoto_id());
            obj.put("photo_name", photo.getPhoto_name());
            obj.put("photo_url", photo.getPhoto_url());
            obj.put("photo_owner", photo.getPhoto_owner());
            objects.add(obj);
        }
        return objects;
    }
     
       /*
    public void addPostPhoto(Post post) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference user_ref = dbFirestore.collection("users");
        ApiFuture<DocumentReference> collectionsApiFuture = user_ref.document(post.getPost_owner()).collection("posts").add(post);
        String id = collectionsApiFuture.get().getId();
            Map<String, Object> updates = new HashMap<>();
            updates.put("post_id", id);
            updates.put("timestamp", Timestamp.now());
            DocumentReference docRef = dbFirestore.collection("users").document(post.getPost_owner()).collection("posts").document(id);
            ApiFuture<WriteResult> writeResult = docRef.update(updates);
    }*/
      
      
}
