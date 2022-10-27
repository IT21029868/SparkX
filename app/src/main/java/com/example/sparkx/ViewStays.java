package com.example.sparkx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;

public class ViewStays extends AppCompatActivity {


    ListView myListview1;
    List<HotelBookings> hotelBookingsList;

    DatabaseReference hoteldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stays);


        myListview1= findViewById(R.id.myListView1);
        hotelBookingsList = new ArrayList<>();

        hoteldb = FirebaseDatabase.getInstance().getReference("HotelBookings");

        hoteldb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hotelBookingsList.clear();

                for(DataSnapshot hotelDatasnap: dataSnapshot.getChildren()){

                    HotelBookings hotels = hotelDatasnap.getValue(HotelBookings.class);
                    hotelBookingsList.add(hotels);
                }

                ListAdapter adapter = new ListAdapter(ViewStays.this,hotelBookingsList);
                myListview1.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        getSupportActionBar().setTitle("View Stays");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));


    }

}