package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuideKrishan extends AppCompatActivity {
    private Button button32;
    private Button button35;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_krishan);

        getSupportActionBar().setTitle("Book A Guide");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        button32 = findViewById(R.id.button32);
        button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEnglishGuide();
            }
        });

        button35 = findViewById(R.id.button35);
        button35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddToCart2();
            }
        });



    }

    public void openEnglishGuide(){
        Intent intent = new Intent(this, EnglishGuide.class);
        startActivity(intent);
    }

    public void openAddToCart2(){
        Intent intent = new Intent(this, AddToCart2.class);
        startActivity(intent);
    }

}