package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class checkVillaDetails extends AppCompatActivity {
    private Button button20;
    private Button button21;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_villa_details);

        getSupportActionBar().setTitle("VillaA Details");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        button20 = findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVillaA();
            }
        });

        button21 = findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddToCart();
            }
        });

    }
    public void openVillaA(){
        Intent intent = new Intent(this , VillaA.class);
        startActivity(intent);
    }

    public void openAddToCart(){
        Intent intent = new Intent(this , AddToCart.class);
        startActivity(intent);
    }


}