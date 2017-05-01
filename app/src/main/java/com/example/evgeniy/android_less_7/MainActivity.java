package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.evgeniy.android_less_7.model.Users;
import com.example.evgeniy.android_less_7.service.GetUserservice;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Response;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Evgeniy on 05.04.2017.
 */




public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText password;
    private TextView logview;

    private Retrofit retrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        logview = (TextView) findViewById(R.id.textView5);
    }


//    public void getUserDetails(){
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://php7.demo20277.atservers.net/web/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        GetUserservice service = retrofit.create(GetUserservice.class);
//        Call<List<Users>> repos = service.getUserDetails();
//
//        repos.enqueue(new Callback<List<Users>>() {
//            @Override
//            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
//                List<Users> users = response.body();
//                String details = "";
//                for (int i = 0; i<users.size();i++){
//                    String name = users.get(i).getUsername();
//                    details +="name  :" + name;
//                    logview.setText(details);
//                }
//            }
//            @Override
//            public void onFailure(Call<List<Users>> call, Throwable t) {
//
//            }
//        });
//    }

        public void checkUserInfo(View view){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://php7.demo20277.atservers.net/web/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            GetUserservice service = retrofit.create(GetUserservice.class);
            Users users = new Users();
            users.setUsername(login.getText().toString());
            users.setPassword(password.getText().toString());
           // logview.setText(users.getUsername()+users.getPassword());

           final Call<Users> repos = service.checkUserInfo(users.getUsername(),users.getPassword());

            repos.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    List<String> json = response.headers().values("Message");
                    String rev = json.toString();

                    if (rev.equals("[true]")){
                        GooDAuth();
                    }else{
                        GooDAuth();//TODO delete this
                        logview.setText("Неправильный логин или пароль");
                    }
                }
                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    logview.setText(t.getMessage()+"\n");
                }
            });

        }

    public void GooDAuth(){
        Intent intent = new Intent(this, LastActivity.class);
        intent.putExtra("login",login.getText().toString());
        intent.putExtra("pass",password.getText().toString());
        startActivity(intent);
    }

}
