package com.example.evgeniy.android_less_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evgeniy.android_less_7.model.Data;
import com.example.evgeniy.android_less_7.service.GetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoTovarActivity extends AppCompatActivity {
    private LinearLayout llt;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView c11;
    private TextView logview;
    String s_id;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tovar);
        String data_id = getIntent().getStringExtra("data_id");

        if (data_id.equals("0")){
            s_id = getIntent().getStringExtra("s_id");
        }else{
            getDataById(data_id);
        }

    }

    public void getDataById(String data_id){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataService service = retrofit.create(GetDataService.class);
        Call<List<Data>> repos = service.getDataById(String.valueOf(data_id));


        repos.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                final List<Data> data = response.body();
               int size = data.size();
                EditText t1 = (EditText) findViewById(R.id.editText);
                EditText t2 = (EditText)  findViewById(R.id.editText3);
                EditText t3 = (EditText)  findViewById(R.id.editText4);
                EditText c11 = (EditText)  findViewById(R.id.editText5);

                for (int i = 0; i<data.size();i++){
                     id = data.get(i).getId();
                    String T1 = data.get(i).getT1();
                    String T2 = data.get(i).getT2();
                    String T3 = data.get(i).getT3();
                    String C11 = data.get(i).getC11();

                    t1.setText(T1);
                    t2.setText(T2);
                    t3.setText(T3);
                    c11.setText(C11);

                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });
    }

    public void saveGood(View view){
        final EditText t11 = (EditText) findViewById(R.id.editText);
        EditText t22 = (EditText)  findViewById(R.id.editText3);
        EditText t33 = (EditText)  findViewById(R.id.editText4);
        EditText c111 = (EditText)  findViewById(R.id.editText5);
        String tx1 =  t11.getText().toString();
        String tx2 = t22.getText().toString();
        String tx3 = t33.getText().toString();
        String cn1 = c111.getText().toString();
        logview =(TextView) findViewById(R.id.textView3);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataService service = retrofit.create(GetDataService.class);
       // t11.setText("Go23of");

        if (id != 0){
        Call<List<Data>> repos = service.saveGood(id,tx1,tx2,tx3,cn1);
        repos.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                logview.setText("Данные успешно сохранены");
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                logview.setText("Ошибка Сохранения");
            }
        });
        }else{
            s_id = getIntent().getStringExtra("s_id");
            logview.setText(s_id);
            Call<List<Data>> repos = service.saveNewGood(id,tx1,tx2,tx3,cn1,Integer.parseInt(s_id));
            repos.enqueue(new Callback<List<Data>>() {
                @Override
                public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                    logview.setText("Данные успешно сохранены");
                }
                @Override
                public void onFailure(Call<List<Data>> call, Throwable t) {
                  //  logview.setText("Ошибка Сохранения");
                }
            });

        }
    }

}
