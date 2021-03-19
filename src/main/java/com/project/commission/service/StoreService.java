/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.project.commission.entities.Store;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

/**
 *
 * @author arl
 */
@Service
public class StoreService {
    
    public Store getStoreDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").document(id).collection("stores").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        if(!documents.isEmpty()){
            Store store = documents.get(0).toObject(Store.class);
            return store;
        }
        return null;    
    }
    
    public void addStore(Store store) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference user_ref = dbFirestore.collection("users");
        user_ref.document(store.getUser_id()).collection("stores").add(store);
    }
    
    public void editInfoStore(Store store) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users").document(store.getUser_id())
                .collection("stores").document(store.getStore_id());
        ApiFuture<WriteResult> future = docRef.update("info", store.getInfo());
        WriteResult result = future.get();
    }
}
