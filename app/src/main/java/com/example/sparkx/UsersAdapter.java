package com.example.sparkx.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparkx.Activities.EditUserActivity;
import com.example.sparkx.Activities.ViewUsersAcitivity;
import com.example.sparkx.Models.UserModel;
import com.example.sparkx.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.viewHolder> {
    ArrayList<UserModel> list;
    Context context;
    UserModel model;

    public UsersAdapter(ArrayList<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.users_item, parent, false);
        return new viewHolder(view);
    }

    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        model = list.get(position);
        if (model.getFullName().equals("Admin User")) {
            holder.btn_delete.setVisibility(View.INVISIBLE);
            holder.btn_edit.setVisibility(View.INVISIBLE);
        } else {

        }

        holder.fullName.setText(model.getFullName());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getContact());
        holder.userType.setText(model.getUserType());

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String PhoneNumber = holder.phone.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(PhoneNumber);
                reference.removeValue();
                Intent intent = new Intent(context.getApplicationContext(), ViewUsersAcitivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);

            }
        });

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneNumber = holder.phone.getText().toString();
                String Email = holder.email.getText().toString();
                String FullName = holder.fullName.getText().toString();

                Intent intent = new Intent(context.getApplicationContext(), EditUserActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("phone",PhoneNumber);
                intent.putExtra("email",Email);
                intent.putExtra("fullName",FullName);
                context.getApplicationContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {


        TextView fullName, email, phone, userType;
        Button btn_delete, btn_edit;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.txt_fullname);
            email = itemView.findViewById(R.id.txt_email);
            phone = itemView.findViewById(R.id.txt_phone);
            userType = itemView.findViewById(R.id.txt_usertype);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }


}
