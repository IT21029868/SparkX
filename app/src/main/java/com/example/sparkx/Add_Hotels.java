package com.example.sparkx

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

public class Add_hotels extends AppCompatActivity {



    private TextInputEditText hotelname,description,price,image;
    private Button addhotelBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private String hotelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotels);
        hotelname = findViewById(R.id.hotelname);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        image = findViewById(R.id.image);
        addhotelBtn = findViewById(R.id.btn);
        loadingPB = findViewById(R.id.loading);
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("Hotels");

        addhotelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hotelName = hotelname.getText().toString();
                String des = description.getText().toString();
                String pri = price.getText().toString();
                String img = image.getText().toString();
                hotelId = hotelName;

                HotelRvModel hotelRvModel = new HotelRvModel(hotelName,des,pri,img,hotelId);


                dbref.addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot){
                        dbref.child(hotelId).setValue(hotelRvModel);
                        Toast.makeText(Add_hotels.this,"Hotel added...",Toast.LENGTH_SHORT);
                        startActivity(new Intent(Add_hotels.this, HotelList.class));



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Add_hotels.this,"Error is"+error.toString(),Toast.LENGTH_SHORT);




                    }
                });



            }
        });
    }
}