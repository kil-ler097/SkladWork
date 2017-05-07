package com.example.evgeniy.android_less_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;


public class InfoSkladActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_sklad);

    }

    public void AboutSklad(View view){
        String s_id = getIntent().getStringExtra("sklad_id");
        String s_name = getIntent().getStringExtra("sklad_name");;
        Intent intent = new Intent(InfoSkladActivity.this, AboutSkladActivity.class);
        intent.putExtra("sklad_id",s_id);
        intent.putExtra("sklad_name",s_name);
        startActivity(intent);
    }

    public void ListTovar(View view){
        String s_id = getIntent().getStringExtra("sklad_id");
        Intent intent = new Intent(InfoSkladActivity.this, ListTovarActivity.class);
        intent.putExtra("sklad_id",s_id);
        startActivity(intent);
    }

    public void SupplierList(View view){
        String s_id = getIntent().getStringExtra("sklad_id");
        Intent intent = new Intent(InfoSkladActivity.this, SupplierActivity.class);
        intent.putExtra("sklad_id",s_id);
        startActivity(intent);
    }


}
