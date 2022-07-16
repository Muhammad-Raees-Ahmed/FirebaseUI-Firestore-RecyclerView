package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.todoapp.Adapter.DetailAdapter;
import com.example.todoapp.Model.Detail;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Declare Recyclerview , Adapter and ArrayList
    private RecyclerView recyclerView;
    ArrayList<Detail> detailArrayList;
    Button btnAdd, btnDone;
    TextInputLayout nameEt, taskEt;
    Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView1();

        btnAdd = findViewById(R.id.add);
        btnAdd.setOnClickListener(this);

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

    private void initView1() {
        // Initialize RecyclerView and set Adapter
        recyclerView = findViewById(R.id.todo_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailArrayList = new ArrayList<>();
        DetailAdapter scoutAdapter = new DetailAdapter(this, detailArrayList);
        recyclerView.setAdapter(scoutAdapter);
        createList1();
    }

    private void createList1() {
        //data to be shown in list

        detailArrayList.add(new Detail("Eren Jaeger", "ioi"));
        detailArrayList.add(new Detail("Eren Jaeger", "ioi"));
        detailArrayList.add(new Detail("Eren Jaeger", "ioi"));
        detailArrayList.add(new Detail("Eren Jaeger", "ioi"));

    }

    public void validateData() {
        String name = nameEt.getEditText().getText().toString();
        String task = taskEt.getEditText().getText().toString();

        if (name.length() >= 1 && task.length() >= 1) {
            detailArrayList.add(new Detail(name, task));
            Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter Valid Inputs", Toast.LENGTH_SHORT).show();
        }

    }
}