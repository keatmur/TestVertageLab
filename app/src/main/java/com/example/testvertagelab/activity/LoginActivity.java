package com.example.testvertagelab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.testvertagelab.R;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.testvertagelab.Constants.PUT_EMAIL;

public class LoginActivity extends AppCompatActivity {
    AppCompatButton go;
    TextInputLayout email;
    TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        go.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
            String stEmail, stPassword;
            stEmail = email.getEditText().getText().toString().trim();
            stPassword = password.getEditText().getText().toString().trim();
            if (chekingEror(stEmail, stPassword)) {
                intent.putExtra(PUT_EMAIL, stEmail);
                startActivity(intent);
            }

        });
    }

    private void initView() {
        go = findViewById(R.id.btn_go);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

    }

    private boolean chekingEror(String stEmail, String stPassword) {

        if ((stEmail.length() == 0 & stPassword.length() == 0)) {
            email.setError("Enter Email");
            password.setError("Enter Password");
            return false;

        } else if (stPassword.length() == 00) {
            password.setError("Enter Password");
            email.setError(null);
            return false;
        } else if (stEmail.length() == 0) {
            email.setError("Enter Email");
            password.setError(null);
            return false;
        } else {
            email.setError(null);
            password.setError(null);
            return true;
        }
    }
}