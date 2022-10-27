package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button button37;
    private Button button41;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Thambapanni");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        button37 = findViewById(R.id.button37);
        button37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSelectDestination();
            }
        });

        button41 = findViewById(R.id.button41);
        button41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBookAGuide();
            }
        });
    }

    public void openSelectDestination(){
        Intent intent = new Intent(this, SelectDestination.class);
        startActivity(intent);
    }
    public void openBookAGuide(){
        Intent intent = new Intent(this, BookAGuide.class);
        startActivity(intent);
    }
}