package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.evgeniy.android_less_7.model.Sklad;
import com.example.evgeniy.android_less_7.model.SupplierData;
import com.example.evgeniy.android_less_7.service.GetSkladService;
import com.example.evgeniy.android_less_7.service.GetSupplierService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupplierSettingsActivity extends AppCompatActivity {
    private LinearLayout llt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_settings);
        GetSupplierlist();
    }

    //Список поставщиков
    private void GetSupplierlist() {
        llt = (LinearLayout) findViewById(R.id.activity_supplier_settings);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetSupplierService service = retrofit.create(GetSupplierService.class);
        Call<List<SupplierData>> repos = service.getSupplierDetails();
        repos.enqueue(new Callback<List<SupplierData>>() {
            @Override
            public void onResponse(Call<List<SupplierData>> call, Response<List<SupplierData>> response) {
                List<SupplierData> sup_list = response.body();
                setButtons(sup_list);
            }

            @Override
            public void onFailure(Call<List<SupplierData>> call, Throwable t) {

            }
        });

    }


    public void setButtons(List<SupplierData> sup_list) {
        llt = (LinearLayout) findViewById(R.id.activity_supplier_settings);
        for (int i = 0; i < sup_list.size(); i++) {
            final String name = sup_list.get(i).getName();
            final int sup_id = sup_list.get(i).getId();
            Button btn = new Button(SupplierSettingsActivity.this);
            btn.setText(name);
            btn.setId(sup_id);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SupplierSettingsActivity.this, SupplierActivity.class);
                    intent.putExtra("sup_id", String.valueOf(sup_id));
                    startActivity(intent);
                }
            });
            llt.addView(btn);
        }
    }


}
