package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindAStay extends AppCompatActivity {
    private Button button5;
    private Button button16;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_astay);

        getSupportActionBar().setTitle("Find A Stay");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSelectDestination();
            }
        });

        button16 = findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStarClassHotel();
            }
        });
    }
    public void openSelectDestination(){
        Intent intent = new Intent(this, SelectDestination.class);
        startActivity(intent);
    }

    public void openStarClassHotel(){
        Intent intent = new Intent(this, BotiqueVilla.class);
        startActivity(intent);
    }
}