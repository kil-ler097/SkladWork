package com.example.evgeniy.android_less_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import com.example.evgeniy.android_less_7.model.Data;
import com.example.evgeniy.android_less_7.model.Sklad;
import com.example.evgeniy.android_less_7.model.SkladInfo;
import com.example.evgeniy.android_less_7.service.GetDataService;
import com.example.evgeniy.android_less_7.service.GetSkladService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AboutSkladActivity extends AppCompatActivity {
    private EditText name;
    private EditText info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_sklad);
        String s_id = getIntent().getStringExtra("sklad_id");

        name = (EditText) findViewById(R.id.editText2);
        if (!s_id.equals("0")){
            String s_name = getIntent().getStringExtra("sklad_name");
            GetSkladInfo(s_id,s_name);
        }
    }


    protected void GetSkladInfo(String s_id,String s_name){
        name = (EditText) findViewById(R.id.editText2);
        info = (EditText) findViewById(R.id.editText6);
        name.setText(s_name);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetSkladService service = retrofit.create(GetSkladService.class);
        Call<List<SkladInfo>> repos = service.getSkladById(s_id);

        repos.enqueue(new Callback<List<SkladInfo>>() {
            @Override
            public void onResponse(Call<List<SkladInfo>> call, Response<List<SkladInfo>> response) {
                List<SkladInfo> data = response.body();
              //  Integer s_id = data.get(0).getS_id();
                String adress = data.get(0).getAdress();
                info.setText(adress);
            }

            @Override
            public void onFailure(Call<List<SkladInfo>> call, Throwable t) {
                info.setText(t.getMessage());
            }
        });
    }

    public void SaveSklad(View view){
        name = (EditText) findViewById(R.id.editText2);
        info = (EditText) findViewById(R.id.editText6);
        String s_id = getIntent().getStringExtra("sklad_id");

        String s_name = name.getText().toString();
        String s_adress = info.getText().toString();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetSkladService service = retrofit.create(GetSkladService.class);
        Call<List<SkladInfo>> repos = service.setSkladInfo(s_id,s_name,s_adress);
        repos.enqueue(new Callback<List<SkladInfo>>() {
            @Override
            public void onResponse(Call<List<SkladInfo>> call, Response<List<SkladInfo>> response) {

            }

            @Override
            public void onFailure(Call<List<SkladInfo>> call, Throwable t) {

            }
        });

    }

    public void deletSklad(View view){
        String s_id = getIntent().getStringExtra("sklad_id");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetSkladService service = retrofit.create(GetSkladService.class);
        Call<List<SkladInfo>> repos = service.deleteSklad(s_id);
        repos.enqueue(new Callback<List<SkladInfo>>() {
            @Override
            public void onResponse(Call<List<SkladInfo>> call, Response<List<SkladInfo>> response) {

            }

            @Override
            public void onFailure(Call<List<SkladInfo>> call, Throwable t) {

            }
        });

    }

}
