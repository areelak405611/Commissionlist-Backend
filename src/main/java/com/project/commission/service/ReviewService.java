/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.project.commission.entities.Post;
import com.project.commission.entities.Review;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

/**
 *
 * @author arl
 */
@Service
public class ReviewService {
    
    public List<Map<String, Object>> getAllReviewByUserId(String user_id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").document(user_id).collection("reviews").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        //List<Review> reviews = new ArrayList();
        List<Map<String, Object>> objects = new ArrayList();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Review.class));
            //reviews.add(document.toObject(Review.class));
            Review rev = document.toObject(Review.class);
            Map<String, Object> obj = new HashMap<>();
            obj.put("post_id", rev.getPost_id());
            obj.put("review_id", rev.getReview_id());
            obj.put("reviewer", rev.getReviewer());
            obj.put("text_review", rev.getText_review());
            obj.put("point", rev.getPoint());
            obj.put("uid", rev.getUid());
            obj.put("time_stamp", rev.getTime_stamp().toSqlTimestamp().toString().substring(0, 16));
            objects.add(obj);
        }
        return objects;
    }
    
    public String addReview(Review review) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference user_ref = dbFirestore.collection("users");
        user_ref.document(review.getUid()).collection("reviews").add(review);
        return null;
    }
    
    public List<Map<String, Object>> getReviewByPost(Post post) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference reviewRef = dbFirestore.collection("users").document(post.getPost_owner()).collection("reviews");
        Query query = reviewRef.whereEqualTo("post_id", post.getPost_id());
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        List<Review> reviews = new ArrayList();
        List<Map<String, Object>> objects = new ArrayList();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Review.class));
            //reviews.add(document.toObject(Review.class));
            Review rev = document.toObject(Review.class);
            Map<String, Object> obj = new HashMap<>();
            obj.put("post_id", rev.getPost_id());
            obj.put("review_id", rev.getReview_id());
            obj.put("reviewer", rev.getReviewer());
            obj.put("text_review", rev.getText_review());
            obj.put("point", rev.getPoint());
            obj.put("uid", rev.getUid());
            obj.put("time_stamp", rev.getTime_stamp().toSqlTimestamp().toString().substring(0, 16));
            objects.add(obj);
        }
        return objects;
    }
    
    
}
