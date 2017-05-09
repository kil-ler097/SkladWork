package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.EditText;
import com.example.evgeniy.android_less_7.model.Category;
import com.example.evgeniy.android_less_7.service.GetCategoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoCategoryActivity extends AppCompatActivity {
    protected String c_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_category);
        c_id = getIntent().getStringExtra("c_id");
        if (c_id.equals("0")){}else{
            GetCategoryByid(c_id);
        }

    }

    protected void GetCategoryByid(String c_id){
        final EditText edit = (EditText) findViewById(R.id.editText9);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetCategoryService service = retrofit.create(GetCategoryService.class);
        final Call<List<Category>> repos = service.getCategoryById(c_id);
        repos.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List <Category> category = response.body();

                for (int i=0;i<response.body().size();i++){
                    final String c_name = category.get(i).getName();
                    edit.setText(c_name);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    public void SaveCategory(View view){
        final EditText edit = (EditText) findViewById(R.id.editText9);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetCategoryService service = retrofit.create(GetCategoryService.class);
        final Call<List<Category>> repos = service.setCategory(c_id,edit.getText().toString());
        repos.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

    }
}
