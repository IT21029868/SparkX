package com.example.sparkx;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditTourGuide extends AppCompatActivity {

    private TextInputEditText guideName,id,phoneNo,email;
    private Button edit,delete;
    private ProgressBar loadingPB;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private String guideId;
    private TourguideRvModel tourguideRvModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tour_guide);

        guideName = findViewById(R.id.guidename);
        id = findViewById(R.id.id);
        phoneNo = findViewById(R.id.phoneNo);
        email = findViewById(R.id.email);
        edit = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        loadingPB = findViewById(R.id.loading);
        db = FirebaseDatabase.getInstance();

        tourguideRvModel = getIntent().getParcelableExtra("guide");
        if(tourguideRvModel!=null){

            guideName.setText(tourguideRvModel.getName());
            id.setText(tourguideRvModel.getCid());
            phoneNo.setText(tourguideRvModel.getPhoneNo());
            email.setText(tourguideRvModel.getGmail());
            guideId = tourguideRvModel.getGuideid();

        }
        dbref = db.getReference().child("Guides").child(guideId);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility((View.VISIBLE));

                String guidename = guideName.getText().toString();
                String cid = id.getText().toString();
                String no = phoneNo.getText().toString();
                String gm = email.getText().toString();


                Map<String,Object> map = new HashMap<>();
                map.put("name",guidename);
                map.put("cid",cid);
                map.put("phoneNo", no);
                map.put("gmail",gm);
                map.put("guideid",guideId);


                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        dbref.updateChildren(map);
                        Toast.makeText(EditTourGuide.this,"Guide Updated...",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditTourGuide.this, TourGuides.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditTourGuide.this,"Fail to update guide...",Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });

        delete.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteGuide();

            }
        }));



    }
    private void deleteGuide(){
        dbref.removeValue();
        Toast.makeText(this,"Deleted...",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditTourGuide.this,TourGuides.class));

    }




}