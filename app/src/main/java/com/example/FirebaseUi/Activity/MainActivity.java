package com.example.FirebaseUi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.FirebaseUi.Model.FirebaseModel;
import com.example.FirebaseUi.Model.User;
import com.example.FirebaseUi.Adapter.UserAdapter;
import com.example.FirebaseUi.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity{
    //Declare Recyclerview , Adapter and ArrayList

    Button btn, btnDone;
    Dialog dialog;
    TextInputLayout nameEt, fNameEt,ageET;
    FirebaseModel firebaseModel;
    User obj=new User();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userDetailRef = db.collection("user detail");

    private UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseModel = FirebaseModel.getInstance();
        setUpRecyclerView();
        btn=findViewById(R.id.add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

    }

    void showCustomDialog() {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_dialog);

        nameEt = dialog.findViewById(R.id.name);
        fNameEt = dialog.findViewById(R.id.fName);
        ageET= dialog.findViewById(R.id.age);
        btnDone = dialog.findViewById(R.id.btn_done);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
        dialog.show();
    }

    private void validateData() {
        String name = nameEt.getEditText().getText().toString();
        String fName = fNameEt.getEditText().getText().toString();
        String age = ageET.getEditText().getText().toString();


        if (name.length() >= 1 && fName.length() >= 1 && age.length()>=1) {
            nameEt.setEnabled(false);
            fNameEt.setEnabled(false);
            ageET.setErrorEnabled(false);
            btnDone.setEnabled(false);
            btnDone.setText("Plz wait...");
//            Toast.makeText(this, "uri "+ uri.toString(), Toast.LENGTH_SHORT).show();
            firebaseModel.addUser(this,new User(name,fName,age));
        }
        if (name.equals("")) {
            nameEt.setErrorEnabled(true);
            nameEt.setError("Required");

        } else {
            nameEt.setErrorEnabled(false);
        }
        if (fName.equals("")) {
            fNameEt.setErrorEnabled(true);
            fNameEt.setError("Required");
        } else {
            fNameEt.setErrorEnabled(false);
        }
        if (age.equals("")) {
            ageET.setErrorEnabled(true);
            ageET.setError("Required");
        } else {
            ageET.setErrorEnabled(false);
        }
    }

    private void setUpRecyclerView() {
        Query query = userDetailRef.orderBy("age", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter = new UserAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    public void updateUI(boolean isSuccess) {
        if (isSuccess) {
            dialog.dismiss();
            Toast.makeText(this, "User successfully Added", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show();
        }

    }
}

