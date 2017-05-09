package com.example.evgeniy.android_less_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

public class InfoTovarActivity extends AppCompatActivity {
    private LinearLayout llt;
    private RadioGroup rbg;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView c11;
    private TextView logview;
    private RadioGroup radio;
    String s_id;
    protected String sup_id;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tovar);
        String data_id = getIntent().getStringExtra("data_id");

        if (data_id.equals("0")) {
            s_id = getIntent().getStringExtra("s_id");
            ImageButton sup_btn = (ImageButton) findViewById(R.id.imageButton3);
            sup_btn.setVisibility(View.INVISIBLE);
            GetSupplierlist();
        } else {
            getDataById(data_id);
        }

    }

    public void getDataById(String data_id) {

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
                EditText t2 = (EditText) findViewById(R.id.editText3);
                EditText t3 = (EditText) findViewById(R.id.editText4);
                EditText c11 = (EditText) findViewById(R.id.editText5);

                for (int i = 0; i < data.size(); i++) {
                    id = data.get(i).getId();
                    String T1 = data.get(i).getT1();
                    String T2 = data.get(i).getT2();
                    String T3 = data.get(i).getT3();
                    String C11 = data.get(i).getC11();

                    t1.setText(T1);
                    t2.setText(T2);
                    t3.setText(T3);
                    c11.setText(C11);

                    sup_id = data.get(i).getSup_id();
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });
    }

    public void saveGood(View view) {
        final EditText t11 = (EditText) findViewById(R.id.editText);
        EditText t22 = (EditText) findViewById(R.id.editText3);
        EditText t33 = (EditText) findViewById(R.id.editText4);
        EditText c111 = (EditText) findViewById(R.id.editText5);
        RadioGroup radio = (RadioGroup) findViewById(R.id.RRG);
        int selected_sup = radio.getCheckedRadioButtonId();
        String tx1 = t11.getText().toString();
        String tx2 = t22.getText().toString();
        String tx3 = t33.getText().toString();
        String cn1 = c111.getText().toString();
        s_id = getIntent().getStringExtra("s_id");
       // logview = (TextView) findViewById(R.id.textView3);
        Log.d("SAVE TOVAR","SELECTED SUP="+selected_sup);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataService service = retrofit.create(GetDataService.class);
        // t11.setText("Go23of");
        logview.setText(String.valueOf(selected_sup));

            Call<List<Data>> repos = service.saveGood(id, tx1, tx2, tx3, cn1, Integer.parseInt(s_id),selected_sup);
            repos.enqueue(new Callback<List<Data>>() {
                @Override
                public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {

                }

                @Override
                public void onFailure(Call<List<Data>> call, Throwable t) {
                }
            });


    }


    public void SupplierList(View view) {

        Intent intent = new Intent(InfoTovarActivity.this, SupplierActivity.class);
        intent.putExtra("sup_id", sup_id);
        startActivity(intent);
    }
    //Список поставщиков

    private void GetSupplierlist() {
        llt = (LinearLayout) findViewById(R.id.activity_info_tovar);
        rbg = (RadioGroup) findViewById(R.id.RRG);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://php7.demo20277.atservers.net/web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetSupplierService service = retrofit.create(GetSupplierService.class);
        Call<List<SupplierData>> repos = service.getSupplierDetails();
        repos.enqueue(new Callback<List<SupplierData>>() {
            @Override
            public void onResponse(Call<List<SupplierData>> call, Response<List<SupplierData>> response) {
                List<SupplierData> data = response.body();
                RadioGroup r_g = new RadioGroup(InfoTovarActivity.this);
                r_g.setId(id);
                llt.addView(r_g);

                for (int i = 0; i < data.size(); i++) {
                    id = data.get(i).getId();
                    String Name = data.get(i).getName();
                    String Data = data.get(i).getData();
                    RadioButton btn = new RadioButton(InfoTovarActivity.this);
                    btn.setText(Name);
                    btn.setId(data.get(i).getId());
                    rbg.addView(btn);
                }
            }

            @Override
            public void onFailure(Call<List<SupplierData>> call, Throwable t) {

            }
        });

    }

}
