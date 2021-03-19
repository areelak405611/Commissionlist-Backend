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
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.project.commission.entities.Post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author arl
 */
@Service
public class PostService {
    
    public List<Map<String, Object>> getAllPostByUserId(String user_id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").document(user_id).collection("posts").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        //List<Post> posts = new ArrayList();
        List<Map<String, Object>> objects = new ArrayList();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Post.class));
            //posts.add(document.toObject(Post.class));
            Post post = document.toObject(Post.class);
            Map<String, Object> obj = new HashMap<>();
            obj.put("post_id", post.getPost_id());
            obj.put("post_owner", post.getPost_owner());
            obj.put("text", post.getText());
            obj.put("title", post.getTitle());
            obj.put("status", post.isStatus());
            obj.put("timestamp", post.getTimestamp().toSqlTimestamp().toString().substring(0, 16));
            objects.add(obj);
        }
        return objects;
    }
    
    public void addPost(Post post) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference user_ref = dbFirestore.collection("users");
        ApiFuture<DocumentReference> collectionsApiFuture = user_ref.document(post.getPost_owner()).collection("posts").add(post);
        String id = collectionsApiFuture.get().getId();
            Map<String, Object> updates = new HashMap<>();
            updates.put("post_id", id);
            updates.put("timestamp", Timestamp.now());
            DocumentReference docRef = dbFirestore.collection("users").document(post.getPost_owner()).collection("posts").document(id);
            ApiFuture<WriteResult> writeResult = docRef.update(updates);
    }
    
    public void editPost(Post post) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users").document(post.getPost_owner()).collection("posts").document(post.getPost_id());
        Map<String, Object> updates = new HashMap<>();
        updates.put("title", post.getTitle());
        updates.put("text", post.getText());
        updates.put("status", post.isStatus());
        updates.put("timestamp", post.getTimestamp());
        ApiFuture<WriteResult> writeResult = docRef.update(updates);
    }
    
    public Post getPost(Post post) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users").document(post.getPost_owner()).collection("posts").document(post.getPost_id());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        Post post_;
        if (document.exists()) {
           // System.out.println("Document data: " + document.getData());
            post_ = document.toObject(Post.class);
            Map<String, Object> obj = new HashMap<>();
            obj.put("post_id", post_.getPost_id());
            obj.put("post_owner", post_.getPost_owner());
            obj.put("text", post_.getText());
            obj.put("title", post_.getTitle());
            obj.put("status", post_.isStatus());
            obj.put("timestamp", post_.getTimestamp().toSqlTimestamp().toString().substring(0, 16));
            return post_;
        }
        return null;
    }
          
    public List<Map<String, Object>> getAllPost() throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();         
        Query query = dbFirestore.collectionGroup("posts");
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        List<Map<String, Object>> objects = new ArrayList();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Post.class));
            //posts.add(document.toObject(Post.class));
            Post post = document.toObject(Post.class);
            Map<String, Object> obj = new HashMap<>();
            obj.put("post_id", post.getPost_id());
            obj.put("post_owner", post.getPost_owner());
            obj.put("text", post.getText());
            obj.put("title", post.getTitle());
            obj.put("status", post.isStatus());
            obj.put("timestamp", post.getTimestamp().toSqlTimestamp().toString().substring(0, 16));
            objects.add(obj);
        }
        return objects;
    }
    
 
    public Object getPostss(String uid, String pid) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users").document(uid).collection("posts").document(pid);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        Post post_;
        if (document.exists()) {
           // System.out.println("Document data: " + document.getData());
            post_ = document.toObject(Post.class);
            Map<String, Object> obj = new HashMap<>();
            obj.put("post_id", post_.getPost_id());
            obj.put("post_owner", post_.getPost_owner());
            obj.put("text", post_.getText());
            obj.put("title", post_.getTitle());
            obj.put("status", post_.isStatus());
            obj.put("timestamp", post_.getTimestamp().toSqlTimestamp().toString().substring(0, 16));
            return post_;
        }
        return null;
    }
    
}
