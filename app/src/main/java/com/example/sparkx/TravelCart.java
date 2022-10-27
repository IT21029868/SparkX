package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TravelCart extends AppCompatActivity {

    private Button button51;
    private Button button52;
    private Button button55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_cart);



        getSupportActionBar().setTitle("Travel Cart");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));



        button51 = findViewById(R.id.button51);
        button51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewStays();
            }
        });


        button52 = findViewById(R.id.button52);
        button52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewGuides();
            }
        });
        button55 = findViewById(R.id.button55);
        button55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
    }

    public void openViewStays(){
        Intent intent = new Intent(this, ViewStays.class);
        startActivity(intent);
    }

    public void openViewGuides(){
        Intent intent = new Intent(this, ViewGuides.class);
        startActivity(intent);
    }

    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}