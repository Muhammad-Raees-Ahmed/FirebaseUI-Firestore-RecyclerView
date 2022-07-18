package com.example.todoapp.Model;

import static com.example.todoapp.Model.immutable.COLLECTION_USER;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.todoapp.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FirebaseModel {
    private static FirebaseModel single_instance = null;
    private final FirebaseFirestore db;

    public FirebaseModel() {
        db = FirebaseFirestore.getInstance();
    }

    public static FirebaseModel getInstance() {
        if (single_instance == null)
            single_instance = new FirebaseModel();
        return single_instance;
    }

    // add task method
    public void addTask(MainActivity mainActivity, String name, String task) {
        DocumentReference doc = FirebaseFirestore.getInstance().collection(COLLECTION_USER).document();
        doc.set(new Detail(doc.getId(), name, task, new Date().getTime()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Inserted", "pass");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Inserted", "pass");
                    }
                });
    }

    // retrieve task //
    public void getTask(MainActivity mainActivity) {
        Task<QuerySnapshot> doc = FirebaseFirestore.getInstance().collection(COLLECTION_USER)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(document.getId());
                            }
                            Log.d("result", list.toString());
                            System.out.println(list.toString());
                            Toast.makeText(mainActivity, list.toString(), Toast.LENGTH_LONG).show();
                        } else{
                            Log.d("error","no result");
                        }
                    }
                });
    }
}

