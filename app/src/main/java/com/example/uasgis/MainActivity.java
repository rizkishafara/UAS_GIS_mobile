package com.example.uasgis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void peta(View view) {
        Intent intent=new Intent(MainActivity.this,Peta.class);
        startActivity(intent);
    }

    public void data(View view) {
        Intent intent=new Intent(MainActivity.this,TampilData.class);
        startActivity(intent);
    }

    public void tambah(View view) {
        Intent intent=new Intent(MainActivity.this,gps.class);
        startActivity(intent);
    }

    public void info(View view) {
        Intent intent = new Intent(MainActivity.this, Informasi.class);
        startActivity(intent);
    }
}