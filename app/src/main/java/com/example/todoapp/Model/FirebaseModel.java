package com.example.todoapp.Model;

import static android.content.ContentValues.TAG;
import static com.example.todoapp.Model.immutable.COLLECTION_USER;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todoapp.Adapter.DetailAdapter;
import com.example.todoapp.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FirebaseModel {
    private static FirebaseModel single_instance = null;
    private final FirebaseFirestore db;


// Array list and list both useful in this situation but good is arraylist for efficiency

    // check  instance
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
                        Log.d("Inserted", "fail");
                    }
                });
    }

    // get document id from collection "user" without snapshot listeners
    public void getDocumentId_SL(MainActivity mainActivity) {

        // QuerySnapshot used for read multiple document from collection
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
                            System.out.println(list);
                            //
                            Toast.makeText(mainActivity, list.toString(), Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("error", "no result");
                        }
                    }
                });
    }

    // get document data  from collection "user"
    public void getSpecificDocumentData(MainActivity mainActivity) {
        DocumentReference docRef = db.collection("users").document("DyNondqBf8avce0lM70P");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                // DocumentSnapshot used for read single document
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        Toast.makeText(mainActivity, document.getData().toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(TAG, "No such document");
                        Toast.makeText(mainActivity, "no ducument", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                    Toast.makeText(mainActivity, "get failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void getTaskData(MainActivity mainActivity, ArrayList<Detail> detailList, DetailAdapter detailAdapter) {
        // always use query snap shot for getting multiple document
        // if we want to fetch data another class and show in other class,activity,fragment so we must use query snapshot
        // Document Refrence can do this all but can't pass data or show

        // Task<QuerySnapshot> querySnapshotQuery= we can also write this when we are not using snapshot listeners (is ke jaga )  db.collection(COLLECTION_USER)
       db.collection(COLLECTION_USER)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
//                                Toast.makeText(mainActivity, document.getData().toString(), Toast.LENGTH_SHORT).show();
                                Detail obj = document.toObject(Detail.class);
                                detailList.add(obj);
                            }
                            // this is important when fetch from server and show in recyclerview
                            detailAdapter.notifyDataSetChanged();
                        } else {


                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void tester(MainActivity mainActivity, ArrayList<Detail> detailList, DetailAdapter detailAdapter) {
// just for testing
        //  Task<QuerySnapshot> querySnapshotQuery= we can't use this when we use snapshot listners
        db.collection(COLLECTION_USER).orderBy("createdDate")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.d("error ", "error ocured");
                        }

                        for (QueryDocumentSnapshot document : value) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
//                                Toast.makeText(mainActivity, document.getData().toString(), Toast.LENGTH_SHORT).show();
                            Detail obj = document.toObject(Detail.class);
                            detailList.add(obj);
                        }
                        // this is important when fetch from server and show in recyclerview
                        detailAdapter.notifyDataSetChanged();
                        Log.d(TAG, "Error getting documents: ");
                    }
                });

    }


}

