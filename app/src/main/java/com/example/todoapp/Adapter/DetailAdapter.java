package com.example.todoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Model.Detail;
import com.example.todoapp.Model.FirebaseModel;
import com.example.todoapp.R;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Detail> details;
    FirebaseModel firebaseModel;
    Detail detail;
    DetailAdapter detailAdapter;

    public DetailAdapter(Context context, ArrayList<Detail> details) {
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        firebaseModel = FirebaseModel.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         detail = details.get(position);
        holder.setDetails(detail);


        // handle when click on delete icon
        holder.delete_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details.clear();
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                firebaseModel.deleteTask(context.getApplicationContext(), detail.getId());
//                Toast.makeText(context, detail.getId(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return details.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtTask;
        CardView delete_cv,edit_cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name);
            txtTask=itemView.findViewById(R.id.task);
            delete_cv=itemView.findViewById(R.id.delete);

        }

        public void setDetails(Detail detail) {
            txtName.setText(detail.getName());
            txtTask.setText(detail.getTask());
        }
    }

}
