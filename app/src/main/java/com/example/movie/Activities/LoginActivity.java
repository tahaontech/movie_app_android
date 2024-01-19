package com.example.movie.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movie.R;

public class LoginActivity extends AppCompatActivity {
    private EditText usrEdt, passEdt;
    private Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView()
    {
        usrEdt = findViewById(R.id.editTextTextPersonName);
        passEdt = findViewById(R.id.editTextTextPassword);
        loginbtn = findViewById(R.id.loginBtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usrEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Please Fill user and password", Toast.LENGTH_SHORT).show();
                } else if (usrEdt.getText().toString().equals("test") && passEdt.getText().toString().equals("test"))
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                else
                {
                    Toast.makeText(LoginActivity.this, "user and password are not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}