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

import java.util.ArrayList;
import java.util.List;

public class ViewGuides extends AppCompatActivity {

    ListView myListview2;
    List<GuideBookings> guideBookingsList;

    DatabaseReference guidebookingdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guides);


        myListview2 = findViewById(R.id.myListView2);
        guideBookingsList = new ArrayList<>();

        guidebookingdb = FirebaseDatabase.getInstance().getReference("GuideBookings");

        guidebookingdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                guideBookingsList.clear();

                for(DataSnapshot guideDatasnap : snapshot.getChildren()){

                    GuideBookings guides = guideDatasnap.getValue(GuideBookings.class);

                    guideBookingsList.add(guides);
                }

                ListAdapter2 adapter = new ListAdapter2(ViewGuides.this,guideBookingsList);
                myListview2.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}