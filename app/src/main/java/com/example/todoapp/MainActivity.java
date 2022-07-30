package com.example.todoapp;

import static android.content.ContentValues.TAG;
import static com.example.todoapp.Model.immutable.COLLECTION_USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.todoapp.Adapter.DetailAdapter;
import com.example.todoapp.Model.Detail;
import com.example.todoapp.Model.FirebaseModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Declare Recyclerview , Adapter and ArrayList

    private RecyclerView recyclerView;
    ArrayList<Detail> detailList;
    Button btnAdd, btnDone;
    TextInputLayout nameEt, taskEt;

    FirebaseModel firebaseModel;
    DetailAdapter detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeData();
        btnAdd = findViewById(R.id.add);
        btnAdd.setOnClickListener(this);
        firebaseModel = FirebaseModel.getInstance();
//        firebaseModel.tester(this, detailList,detailAdapter);
        firebaseModel.getUserTask(this,detailList,detailAdapter);

//        getTaskData(MainActivity.this,detailList);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                showCustomDialog();
                break;
        }
    }

    //Function to display the custom dialog.
    void showCustomDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_todo_dialog);
        nameEt = dialog.findViewById(R.id.name);
        taskEt = dialog.findViewById(R.id.task);

        btnDone = dialog.findViewById(R.id.btn_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initializeData() {
        // Initialize RecyclerView and set


        detailList = new ArrayList<>();
        recyclerView = findViewById(R.id.todo_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        detailAdapter = new DetailAdapter(this, detailList);
        recyclerView.setAdapter(detailAdapter);
//        Toast.makeText(this, detailList.toString(), Toast.LENGTH_SHORT).show();
    }


    public void validateData() {
        String name = nameEt.getEditText().getText().toString();
        String task = taskEt.getEditText().getText().toString();

        if (name.length() >= 1 && task.length() >= 1) {

            firebaseModel.addTask(this, name, task);
//            detailArrayList.add(new Detail(name, task));
            Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter Valid Inputs", Toast.LENGTH_SHORT).show();
        }
    }

}