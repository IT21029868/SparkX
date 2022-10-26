package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VillaA extends AppCompatActivity {
    private Button button7;
    private Button button9;
    private Button button18;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villa);

        getSupportActionBar().setTitle("VillaA");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBotiqueVilla();
            }
        });

        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewVilla();
            }
        });

        button18 = findViewById(R.id.button18);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencheckVillaDetails();
            }
        });
    }
    public void openBotiqueVilla(){
        Intent intent = new Intent(this, BotiqueVilla.class);
        startActivity(intent);
    }

    public void openViewVilla(){
        Intent intent = new Intent(this, ViewVilla.class);
        startActivity(intent);
    }

    public void opencheckVillaDetails(){
        Intent intent = new Intent(this , checkVillaDetails.class);
        startActivity(intent);
    }
}