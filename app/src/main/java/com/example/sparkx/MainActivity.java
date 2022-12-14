package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sparkx.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Button button37;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Thambapanni");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));


        button37 = findViewById(R.id.button37);
        button37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSelectDestination();
            }
        });
    }
    public void openSelectDestination(){
        Intent intent = new Intent(this, SelectDestination.class);
        startActivity(intent);
    }

}