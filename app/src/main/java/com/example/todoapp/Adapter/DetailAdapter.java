package com.example.todoapp.Adapter;

import static android.content.ContentValues.TAG;
import static com.example.todoapp.Model.immutable.COLLECTION_USER;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity;
import com.example.todoapp.Model.Detail;
import com.example.todoapp.Model.FirebaseModel;
import com.example.todoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Detail> arrayOfTask;
    FirebaseModel firebaseModel;

    Detail detail;
    DetailAdapter detailAdapter;
    MainActivity m;

    public DetailAdapter(Context context, List<Detail> arrayOfTask) {
        this.context = context;
        this.arrayOfTask = arrayOfTask;
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
        detail = arrayOfTask.get(position);
        holder.setDetails(detail);


        // handle when click on delete icon
        holder.delete_cv.setOnClickListener(this);


        // handle when click on edit icon
        holder.edit_cv.setOnClickListener(this);


    }

    @Override
    public int getItemCount() {
        return arrayOfTask.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete:
                arrayOfTask.clear();
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                firebaseModel.deleteTask(context.getApplicationContext(), detail.getId());

                break;
            case R.id.edit:

                Toast.makeText(context, "coming soon", Toast.LENGTH_SHORT).show();
//                FirebaseFirestore.getInstance().collection(COLLECTION_USER).document(detail.getId())
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    DocumentSnapshot document = task.getResult();
//                                    if (document.exists()) {
//                                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                                        Toast.makeText(context, document.getData().toString(), Toast.LENGTH_SHORT).show();
//                                        Detail obj = document.toObject(Detail.class);
//                                        ViewHolder.setDetails(obj);
//                                        final Dialog dialog = new Dialog(view.getContext());
//                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                                        dialog.setCancelable(true);
//                                        dialog.setContentView(R.layout.add_todo_dialog);
//                                        dialog.show();
//
//
//
//                                        String e_name=document.getData().get("name").toString();
//                                        String e_task=document.getData().get("task").toString();
//                                        Toast.makeText(context, e_name+e_task, Toast.LENGTH_SHORT).show();
//                                        ViewHolder.txtName.setText(detail.getName());
//                                        ViewHolder.txtTask.setText(detail.getTask());
//
//
//                                    } else {
//                                        Log.d(TAG, "No such document");
//                                    }
//                                } else {
//                                    Log.d(TAG, "get failed with ", task.getException());
//                                }
//                            }
//                        });
        }


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private static TextView txtName;
        private static TextView txtTask;
        CardView delete_cv,edit_cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name);
            txtTask=itemView.findViewById(R.id.task);
            delete_cv=itemView.findViewById(R.id.delete);
            edit_cv=itemView.findViewById(R.id.edit);

//            edit_cv.setVisibility(View.INVISIBLE);

        }

        public static void setDetails(Detail detail) {
            txtName.setText(detail.getName());
            txtTask.setText(detail.getTask());




        }

    }

}
