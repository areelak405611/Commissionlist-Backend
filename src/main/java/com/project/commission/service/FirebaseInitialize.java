/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.commission.service;

import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author arl
 */
@Service
public class FirebaseInitialize {
    
    @PostConstruct
    public void initialize() {
        try {  
           //FileInputStream serviceAccount = new FileInputStream("D:\\senior project\\PJCommislist\\commission-backend\\src\\main\\resources\\projectcommislist-firebase-adminsdk-fzndu-8b91fbdde5.json");
           InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("./projectcommislist-firebase-adminsdk-fzndu-8b91fbdde5.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://projectcommislist-default-rtdb.firebaseio.com")
            .build();

            FirebaseApp.initializeApp(options);
            FirebaseDatabase.getInstance(FirebaseApp.getInstance()).setPersistenceEnabled(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
