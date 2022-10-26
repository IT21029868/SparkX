package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddToCart extends AppCompatActivity {



    private Button button34;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);




        getSupportActionBar().setTitle("Add To Cart");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        button34 = findViewById(R.id.button34);
        button34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindAStay();
            }
        });
    }
    public void openFindAStay(){
        Intent intent = new Intent(this, FindAStay.class);
        startActivity(intent);
    }

}