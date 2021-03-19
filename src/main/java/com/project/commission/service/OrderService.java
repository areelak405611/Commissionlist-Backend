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
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.project.commission.entities.Order;
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
public class OrderService {
    
    public List<Map<String, Object>> getAllOrderByUserId(String user_id) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").document(user_id).collection("orders").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Order> orders = new ArrayList();
        List<Map<String, Object>> objects = new ArrayList();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Order.class));
            orders.add(document.toObject(Order.class));
            Order order = document.toObject(Order.class);
            Map<String, Object> obj = new HashMap<>();
            obj.put("order_id", order.getOrder_id());
            obj.put("order_owner", order.getOrder_owner());
            obj.put("customer_id", order.getCustomer_id());
            obj.put("customer_name", order.getCustomer_name());
            obj.put("post_id", order.getPost_id());
            obj.put("price", order.getPrice());
            obj.put("scale", order.getScale());
            obj.put("type", order.getType());
            obj.put("work_status", order.getWork_status());
            obj.put("timestamp", order.getTimestamp().toSqlTimestamp().toString().substring(0, 10));
            objects.add(obj);
        }
        //Object object = documents.getClass();
        return objects;
    }
    
    public List<Map<String, Object>> getAllMyOrderByUserId(String user_id) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();         
        Query query = dbFirestore.collectionGroup("orders").whereEqualTo("customer_id", user_id);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        //List<Order> orders = new ArrayList();
        List<Map<String, Object>> objects = new ArrayList();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Order.class));
            //orders.add(document.toObject(Order.class));
            Order order = document.toObject(Order.class);
            Map<String, Object> obj = new HashMap<>();
            obj.put("order_id", order.getOrder_id());
            obj.put("order_owner", order.getOrder_owner());
            obj.put("customer_id", order.getCustomer_id());
            obj.put("customer_name", order.getCustomer_name());
            obj.put("post_id", order.getPost_id());
            obj.put("price", order.getPrice());
            obj.put("scale", order.getScale());
            obj.put("type", order.getType());
            obj.put("work_status", order.getWork_status());
            obj.put("timestamp", order.getTimestamp().toSqlTimestamp().toString().substring(0, 10));
            objects.add(obj);
        }
        //Object object = documents.getClass();
        return objects;
    }
    
    public void changeWorkStatusOrder(Order order) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users")
                .document(order.getOrder_owner()).collection("posts").document(order.getPost_id());
        ApiFuture<WriteResult> future = docRef.update("work_status", order.getWork_status());
        WriteResult result = future.get();
    }
    
    public void addOrder(Order order)throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference user_ref = dbFirestore.collection("users");
        ApiFuture<DocumentReference> collectionsApiFuture = user_ref.document(order.getOrder_owner()).collection("orders").add(order);    
        String id = collectionsApiFuture.get().getId();
            Map<String, Object> updates = new HashMap<>();
            updates.put("order_id", id);
            updates.put("timestamp", Timestamp.now());
            DocumentReference docRef = dbFirestore.collection("users").document(order.getOrder_owner()).collection("orders").document(id);
            ApiFuture<WriteResult> writeResult = docRef.update(updates);
    }
    
    public void editOrder(Order order) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users")
                .document(order.getOrder_owner()).collection("orders").document(order.getOrder_id());
        Map<String, Object> obj = new HashMap<>();
        obj.put("customer_name", order.getCustomer_name());
        obj.put("price", order.getPrice());
        obj.put("scale", order.getScale());
        obj.put("type", order.getType());
        obj.put("work_status", order.getWork_status());
        obj.put("timestamp", order.getTimestamp());
        ApiFuture<WriteResult> future = docRef.update(obj);
    }
}
