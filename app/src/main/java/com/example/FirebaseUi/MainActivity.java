package com.example.FirebaseUi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.firestore.FirebaseFirestore;
public class MainActivity extends AppCompatActivity{
    //Declare Recyclerview , Adapter and ArrayList


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore.getInstance();

    }
}