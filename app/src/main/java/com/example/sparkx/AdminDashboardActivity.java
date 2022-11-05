package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sparkx.R;

public class AdminDashBoardActivity extends AppCompatActivity {
    Button btn_manage_users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);
        btn_manage_users=findViewById(R.id.btn_manage_users);
        btn_manage_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ViewUsersAcitivity.class);
                startActivity(intent);
            }
        });
    }
}