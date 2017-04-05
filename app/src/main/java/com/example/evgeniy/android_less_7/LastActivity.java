package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Evgeniy on 05.04.2017.
 */



public class LastActivity extends AppCompatActivity {

    private EditText txtLogin;
    private EditText txtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lastlayout);
        Intent intent = new Intent(this, MainActivity.class);


        txtLogin = (EditText) findViewById(R.id.txtlogin);
        txtPassword = (EditText) findViewById(R.id.txtpassword);

        txtLogin.setText(getIntent().getStringExtra("login"));
        txtPassword.setText(getIntent().getStringExtra("pass"));

    }
    public void back (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
