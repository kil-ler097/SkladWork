package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Evgeniy on 05.04.2017.
 */

public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
    }

    public void SendData (View view){
        Intent intent = new Intent(this, LastActivity.class);
        intent.putExtra("login",login.getText().toString());
        intent.putExtra("pass",password.getText().toString());
        startActivity(intent);

    }
}
