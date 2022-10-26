package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnglishGuide extends AppCompatActivity {
    private Button button23;
    private Button button38;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_guide);

        getSupportActionBar().setTitle("English Guide");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        button23 = findViewById(R.id.button23);
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBookAGuide();
            }
        });

        button38 = findViewById(R.id.button38);
        button38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGuideKrishan();
            }
        });




    }

    public void openBookAGuide(){
        Intent intent = new Intent(this, BookAGuide.class);
        startActivity(intent);
    }


    public void openGuideKrishan(){
        Intent intent = new Intent(this, GuideKrishan.class);
        startActivity(intent);
    }
}