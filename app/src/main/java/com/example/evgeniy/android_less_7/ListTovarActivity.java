package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evgeniy.android_less_7.model.Category;
import com.example.evgeniy.android_less_7.model.Data;
import com.example.evgeniy.android_less_7.service.GetCategoryService;
import com.example.evgeniy.android_less_7.service.GetDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListTovarActivity extends AppCompatActivity {
    private LinearLayout llt;
    public TextView logview;
    public List<String> categorys = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tovar);
        String s_id = getIntent().getStringExtra("sklad_id");

        getDataDetails(s_id);
        GetListCategory();
        SetSpinner();
    }

    public void getDataDetails(String s_id){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataService service = retrofit.create(GetDataService.class);
        Call<List<Data>> repos = service.getDataDetails(String.valueOf(s_id));

        repos.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                final List<Data> data = response.body();
                llt = (LinearLayout) findViewById(R.id.activity_list_tovar);
                String details = "";
                for (int i = 0; i<data.size();i++){
                   String T1 = data.get(i).getT1();
                   final int data_id = data.get(i).getId();

                    Button btn = new Button(ListTovarActivity.this);

                    btn.setText(T1);
                    btn.setId(data_id);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ListTovarActivity.this, InfoTovarActivity.class);
                            intent.putExtra("data_id",Integer.toString(data_id));
                            startActivity(intent);
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

    private void SetSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorys);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);//Заполняем снипер
        spinner.setPrompt("Фильтр...");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),"124",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void newGood(View view){
        String s_id = getIntent().getStringExtra("sklad_id");

        Intent intent = new Intent(ListTovarActivity.this, InfoTovarActivity.class);
        intent.putExtra("data_id","0");
        intent.putExtra("s_id",s_id);
        startActivity(intent);
    }

    protected void GetListCategory(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetCategoryService service = retrofit.create(GetCategoryService.class);
        final Call<List<Category>> repos = service.getCategoryDetails();
        repos.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List <Category> category = response.body();
                llt = (LinearLayout) findViewById(R.id.activity_category_settings);

                for (int i=0;i<response.body().size();i++){
                    final String c_name = category.get(i).getName();
                    final int c_id = category.get(i).getId();
                    categorys.add(c_name);

                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }



}
