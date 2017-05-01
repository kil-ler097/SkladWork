package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ListTovarActivity extends AppCompatActivity {
    private LinearLayout llt;
   // private TextView logview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tovar);
       // logview = (TextView) findViewById(R.id.textView13);
        String s_id = getIntent().getStringExtra("sklad_id");
       // logview.setText(s_id);
        getDataDetails(s_id);
    }

    public void getDataDetails(String s_id){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataService service = retrofit.create(GetDataService.class);
        Call<List<Data>> repos = service.getDataDetails(String.valueOf(s_id));
     //   logview.setText(String.valueOf(s_id)+"new");

        repos.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                final List<Data> data = response.body();
                llt = (LinearLayout) findViewById(R.id.activity_list_tovar);
                String details = "";
                for (int i = 0; i<data.size();i++){
                   String T1 = data.get(i).getT1();
                   final int data_id = data.get(i).getId();
                 //   details +="T1  :" + T1;
                   // logview.setText(details);
                    Button btn = new Button(ListTovarActivity.this);
//                    btn.setLayoutParams(layoutParams);

                    btn.setText(T1);
                    btn.setId(data_id);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ListTovarActivity.this, InfoTovarActivity.class);
                            intent.putExtra("data_id",Integer.toString(data_id));
                            startActivity(intent);
                            //   getDataDetails(sklad_id);
                        }
                    });
                    llt.addView(btn);
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });

    }


}
