package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.evgeniy.android_less_7.model.Category;
import com.example.evgeniy.android_less_7.service.GetCategoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategorySettingsActivity extends AppCompatActivity {
    private LinearLayout llt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_settings);
        GetListCategory();
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
                Button btn = new Button(CategorySettingsActivity.this);

                for (int i=0;i<response.body().size();i++){
                    final String c_name = category.get(i).getName();
                    final int c_id = category.get(i).getId();

                    btn = new Button(CategorySettingsActivity.this);
                    btn.setText(c_name);
                    btn.setId(c_id);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(CategorySettingsActivity.this, InfoCategoryActivity.class);
                            intent.putExtra("c_id",Integer.toString(c_id));
                            intent.putExtra("c_name",c_name);
                            startActivity(intent);
                        }
                    });
                    llt.addView(btn);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    public void addCategory(View view){
        Intent intent = new Intent(CategorySettingsActivity.this, InfoCategoryActivity.class);
        intent.putExtra("c_id","0");
        startActivity(intent);
    }
}
