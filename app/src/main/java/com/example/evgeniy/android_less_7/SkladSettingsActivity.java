package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.evgeniy.android_less_7.model.Data;
import com.example.evgeniy.android_less_7.model.Sklad;
import com.example.evgeniy.android_less_7.service.GetDataService;
import com.example.evgeniy.android_less_7.service.GetSkladService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkladSettingsActivity extends AppCompatActivity {
    public LinearLayout llt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sklad_settings);
        getSkladDetails();
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
                final List<Sklad> sklad = response.body();
                setButtons(sklad);
            }
            @Override
            public void onFailure(Call<List<Sklad>> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage().toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void setButtons(List<Sklad> sklad) {
        llt = (LinearLayout) findViewById(R.id.activity_sklad_settings);
        for (int i = 0; i < sklad.size(); i++) {
            final String name = sklad.get(i).getName();
            final int sklad_id = sklad.get(i).getId();
            Button btn = new Button(SkladSettingsActivity.this);
            btn.setText(name);
            btn.setId(sklad_id);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SkladSettingsActivity.this, AboutSkladActivity.class);
                    intent.putExtra("sklad_id", Integer.toString(sklad_id));
                    intent.putExtra("sklad_name", name);
                    startActivity(intent);
                }
            });
            llt.addView(btn);
        }
    }

}
