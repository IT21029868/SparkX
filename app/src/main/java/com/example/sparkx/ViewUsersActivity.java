package com.example.sparkx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sparkx.Adapters.UsersAdapter;
import com.example.sparkx.Models.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class ViewUsersAcitivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<UserModel> usersList = new ArrayList<>();
    ArrayList<UserModel> usersList2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.sparkx.R.layout.activity_view_users_acitivity);
        rv=findViewById(com.example.sparkx.R.id.rv);
        getAllUsers();



    }

    private void getAllUsers()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                collectUsers((Map<String,Object>) snapshot.getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void displayUsers()
    {
        usersList2=new ArrayList<>();
        for(int i=0;i<usersList.size();i++)
        {
            usersList2.add(new UserModel(usersList.get(i).getFullName(), usersList.get(i).getContact(), "", usersList.get(i).getEmail(), usersList.get(i).getUserType()));
            System.out.println("User Fulll Name"+ usersList.get(i).getContact());
            UsersAdapter adapter = new UsersAdapter(usersList2, getApplicationContext());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true);
            rv.setLayoutManager(layoutManager);
            //storyRV.setNestedScrollingEnabled(false);
            rv.setAdapter(adapter);
        }



    }
    private void collectUsers(Map<String,Object> users) {
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            String contact=singleUser.get("contact").toString();
            String fullName=singleUser.get("fullName").toString();
            String  email=singleUser.get("email").toString();
            String UserType=singleUser.get("userType").toString();
            UserModel tempUser=new UserModel(fullName,contact,"",email,UserType);
            usersList.add((tempUser) );
        }

        displayUsers();

    }
}