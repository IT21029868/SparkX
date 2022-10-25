package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BotiqueVilla extends AppCompatActivity {
    private Button button4;
    private Button button6;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botique_villa);

        getSupportActionBar().setTitle("Botique Villa");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindAStay();
            }
        });

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVillaA();
            }
        });


    }
    public void openFindAStay(){
        Intent intent = new Intent(this, FindAStay.class);
        startActivity(intent);
    }

    public void openVillaA(){
        Intent intent = new Intent(this, VillaA.class);
        startActivity(intent);
    }
}