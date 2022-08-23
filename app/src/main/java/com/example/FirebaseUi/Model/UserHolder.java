package com.example.FirebaseUi.Model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.FirebaseUi.R;

public class UserHolder extends RecyclerView.ViewHolder {
    TextView textViewName;
    TextView textViewfName;
    TextView textViewAge;
    public UserHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.text_view_name);
        textViewfName = itemView.findViewById(R.id.text_view_fName);
        textViewAge = itemView.findViewById(R.id.text_view_age);
    }
}
