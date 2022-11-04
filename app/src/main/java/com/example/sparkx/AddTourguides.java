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

public class AddTourguides extends AppCompatActivity {


    private TextInputEditText guideName,id,phoneNo,email;
    private Button addguideBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private String guideId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tourguides);

        guideName = findViewById(R.id.guidename);
        id = findViewById(R.id.id);
        phoneNo = findViewById(R.id.phoneNo);
        email = findViewById(R.id.email);

        addguideBtn = findViewById(R.id.btn);
        loadingPB = findViewById(R.id.loading);
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("Guides");

        addguideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guidename = guideName.getText().toString();
                String cid = id.getText().toString();
                String no = phoneNo.getText().toString();
                String gm = email.getText().toString();

                guideId = cid;

                TourguideRvModel tourguideRvModel = new TourguideRvModel(guidename,cid,no,gm,guideId);


                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dbref.child(guideId).setValue(tourguideRvModel);
                        Toast.makeText(AddTourguides.this,"Guide added...",Toast.LENGTH_SHORT);
                        startActivity(new Intent(AddTourguides.this, TourGuides.class));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddTourguides.this,"Error is"+error.toString(),Toast.LENGTH_SHORT);


                    }
                });



            }
        });






    }
}