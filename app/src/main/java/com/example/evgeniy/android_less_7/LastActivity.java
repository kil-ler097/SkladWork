package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evgeniy.android_less_7.model.Sklad;
import com.example.evgeniy.android_less_7.service.GetSkladService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Evgeniy on 05.04.2017.
 */



public class LastActivity extends AppCompatActivity {

    private EditText txtLogin;
    private EditText txtPassword;
    private TextView logview;
//android.support.constraint.ConstraintLayout
    LinearLayout llt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lastlayout);

        logview = (TextView) findViewById(R.id.textView3);

        llt = (LinearLayout) findViewById(R.id.container);


      //  lastA = (LinearLayout) findViewById(R.id.container);

       // getSkladDetails();

//        Intent intent = new Intent(this, MainActivity.class);
//
//
//        txtLogin = (EditText) findViewById(R.id.txtlogin);
//        txtPassword = (EditText) findViewById(R.id.txtpassword);
//
//        txtLogin.setText(getIntent().getStringExtra("login"));
//        txtPassword.setText(getIntent().getStringExtra("pass"));

    }

    public void getSkladDetails(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetSkladService service = retrofit.create(GetSkladService.class);
        Call<List<Sklad>> repos = service.getSkladDetails();

        repos.enqueue(new Callback<List<Sklad>>() {
            @Override
            public void onResponse(Call<List<Sklad>> call, Response<List<Sklad>> response) {
                    List<Sklad> sklad = response.body();
                    String details = "";
                LinearLayout llt = (LinearLayout) findViewById(R.id.container);
                Button btn = new Button(LastActivity.this);

                for (int i = 0; i<sklad.size();i++){
                    String name = sklad.get(i).getName();

                    btn = new Button(LastActivity.this);
//                    btn.setLayoutParams(layoutParams);
                    btn.setId(i);
                    btn.setText(name);
                    llt.addView(btn);

                    details +="name  :" + name;
                    logview.setText(details);
                }
            }
            @Override
            public void onFailure(Call<List<Sklad>> call, Throwable t) {

            }
        });

    }

    public void back (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
