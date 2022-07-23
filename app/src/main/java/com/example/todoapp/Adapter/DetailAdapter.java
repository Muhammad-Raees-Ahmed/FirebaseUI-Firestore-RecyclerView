package com.example.todoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Model.Detail;
import com.example.todoapp.R;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Detail> details;

    public DetailAdapter(Context context, ArrayList<Detail> details) {
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Detail detail = details.get(position);
        holder.setDetails(detail);
    }

    @Override
    public int getItemCount() {
        return details.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtTask, txtKill;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name);
            txtTask=itemView.findViewById(R.id.task);

        }

        public void setDetails(Detail detail) {
            txtName.setText(detail.getName());
            txtTask.setText(detail.getTask());
        }
    }

}
