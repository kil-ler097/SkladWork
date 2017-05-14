package com.example.evgeniy.android_less_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evgeniy.android_less_7.model.Data;
import com.example.evgeniy.android_less_7.model.SupplierData;
import com.example.evgeniy.android_less_7.service.GetDataService;
import com.example.evgeniy.android_less_7.service.GetSupplierService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupplierActivity extends AppCompatActivity {
    protected String sup_id;
    protected EditText name;
    protected EditText data_sup;
    protected TextView log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);

        sup_id = getIntent().getStringExtra("sup_id");

        if (sup_id.equals("0")) {

        } else {
            getSupbyid();
        }
    }

    protected void getSupbyid() {
        name = (EditText) findViewById(R.id.editText8) ;
        data_sup = (EditText) findViewById(R.id.editText7) ;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetSupplierService service = retrofit.create(GetSupplierService.class);
        Call<List<SupplierData>> repos = service.getSupplierById(String.valueOf(sup_id));

        repos.enqueue(new Callback<List<SupplierData>>() {
            @Override
            public void onResponse(Call<List<SupplierData>> call, Response<List<SupplierData>> response) {
                List<SupplierData> data = response.body();
                name.setText(data.get(0).getName());
                data_sup.setText(data.get(0).getData());
            }
            @Override
            public void onFailure(Call<List<SupplierData>> call, Throwable t) {

            }
        });
    }

    public void setSupplierddata(View view){
        EditText  name_a = (EditText) findViewById(R.id.editText8) ;
        EditText  data_sup_a = (EditText) findViewById(R.id.editText7) ;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetSupplierService service = retrofit.create(GetSupplierService.class);
        Call<List<SupplierData>> repos = service.setSupplierById(sup_id,name_a.getText().toString(),data_sup_a.getText().toString());

        repos.enqueue(new Callback<List<SupplierData>>() {
            @Override
            public void onResponse(Call<List<SupplierData>> call, Response<List<SupplierData>> response) {

            }

            @Override
            public void onFailure(Call<List<SupplierData>> call, Throwable t) {

            }
        });
    }


    public void deleteSup(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetSupplierService service = retrofit.create(GetSupplierService.class);
        Call<List<SupplierData>> repos = service.deletesupplier(sup_id);
        repos.enqueue(new Callback<List<SupplierData>>() {
            @Override
            public void onResponse(Call<List<SupplierData>> call, Response<List<SupplierData>> response) {
                Toast.makeText(getBaseContext(),"Успешно удален",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<SupplierData>> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Успешно удален",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
