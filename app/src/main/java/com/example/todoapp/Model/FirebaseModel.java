package com.example.todoapp.Model;

import static com.example.todoapp.Model.immutable.COLLECTION_USER;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class FirebaseModel {
    private static FirebaseModel single_instance = null;
    private final FirebaseFirestore db;
    private DocumentReference doc;


    private FirebaseModel() {
        db = FirebaseFirestore.getInstance();
    }



    public void addTask(String name, String task) {
        doc=FirebaseFirestore.getInstance().collection(COLLECTION_USER).document();
        doc.set(new Detail(doc.getId(),name,task,new Date().getTime()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Inserted","pass");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Inserted","pass");
                    }
                });
    }
    public void addTest(String a,String b){
        doc=FirebaseFirestore.getInstance().collection("test").document();
               doc .set(new test(a,b))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        System.out.println("dadasdasda");
                    }
                });

    }
}

