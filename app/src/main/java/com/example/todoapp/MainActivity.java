package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.todoapp.Adapter.DetailAdapter;
import com.example.todoapp.Adapter.ScoutAdapter;
import com.example.todoapp.Model.Detail;
import com.example.todoapp.Model.Scout;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declare Recyclerview , Adapter and ArrayList
    private RecyclerView recyclerView;
    private ScoutAdapter adapter;
    private ArrayList<Scout> scoutArrayList;

    ArrayList<Detail> detailArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView1();

        Button btn=findViewById(R.id.add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
showCustomDialog();
            }
        });
    }

    //Function to display the custom dialog.
    void showCustomDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.add_todo_dialog);

        //Initializing the views of the dialog.
        final TextInputLayout nameEt = dialog.findViewById(R.id.name);
        final  TextInputLayout taskEt = dialog.findViewById(R.id.task);
//        final CheckBox termsCb = dialog.findViewById(R.id.terms_cb);
        Button submitButton = dialog.findViewById(R.id.btn_done);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEt.getEditText().getText().toString();
                String age = taskEt.getEditText().getText().toString();

//                populateInfoTv(name,age,hasAccepted);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void initView1() {
        // Initialize RecyclerView and set Adapter
        recyclerView = findViewById(R.id.todo_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailArrayList= new ArrayList<>();
        DetailAdapter scoutAdapter=new DetailAdapter(this,detailArrayList);
        recyclerView.setAdapter(scoutAdapter);
        createList1();
    }


    private void createList1() {
        //data to be shown in list


        detailArrayList.add(new Detail( "Eren Jaeger" ));
        detailArrayList.add(new Detail( "Eren Jaeger" ));
        detailArrayList.add(new Detail( "Eren Jaeger" ));
        detailArrayList.add(new Detail( "Eren Jaeger" ));


    }
}