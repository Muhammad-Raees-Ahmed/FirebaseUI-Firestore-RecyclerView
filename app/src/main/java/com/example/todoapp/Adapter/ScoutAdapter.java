package com.example.todoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Model.Scout;
import com.example.todoapp.R;

import java.util.ArrayList;

public class ScoutAdapter extends RecyclerView.Adapter<ScoutAdapter.ScoutHolder>{
    private Context context;
    private ArrayList<Scout> scouts;

    public ScoutAdapter(Context context, ArrayList<Scout> scouts) {
        this.context = context;
        this.scouts = scouts;
    }
    @NonNull
    @Override
    public ScoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ScoutHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ScoutHolder holder, int position) {
        Scout scout = scouts.get(position);
        holder.setDetails(scout);
    }

    @Override
    public int getItemCount() {
        return scouts.size();
    }

    public class ScoutHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtRank, txtKill;
        public ScoutHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name);
//            txtRank = itemView.findViewById(R.id.txtRank);
//            txtKill = itemView.findViewById(R.id.txtKill);
        }

        public void setDetails(Scout scout) {
            txtName.setText(scout.getName());
//            txtRank.setText("Rank: "+scout.getRank());
//            txtKill.setText("Rank: "+scout.getKillCount());
        }
    }
}
