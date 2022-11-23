package com.example.uasgis;

import android.Manifest;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class gps extends AppCompatActivity {
    AksesGPS gps;
    Location loc;
    LocationManager lokasi;

    EditText etLat, etLong, etNpsn, etNama, etAlamat, etKecamatan, etKategori;
    Button btSimpan;


    OkHttpClient.Builder builder;

    public gps() {
        builder = new OkHttpClient.Builder();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        String[] permision=new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        this.requestPermissions(permision,0);

        etLat = (EditText) findViewById(R.id.etLat);
        etLong = findViewById(R.id.etLong);
        etNpsn = findViewById(R.id.etNpsn);
        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etKecamatan = findViewById(R.id.etKecamatan);
        etKategori = findViewById(R.id.etKategori);

        try {
            gps = new AksesGPS(getApplicationContext());
            loc = gps.ambilLokasi();
            etLat.setText(""+loc.getLatitude());
            etLong.setText(""+loc.getLongitude());
        }catch (Exception e){
            Toast.makeText(gps.this,e.toString(),Toast.LENGTH_LONG).show();
        }

        builder.connectTimeout(20,TimeUnit.SECONDS);
        builder.writeTimeout(20,TimeUnit.SECONDS);
        builder.readTimeout(10,TimeUnit.SECONDS);

        btSimpan = findViewById(R.id.btSimpan);
        btSimpan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                simpan();
                kosong();

            }

        });
    }
    void kosong(){
        etNpsn.setText("");
        etNpsn.requestFocus();

    }

    void simpan(){
        OkHttpClient client = builder.build();

        RequestBody reqbody = new FormBody.Builder()
                .add("npsn",etNpsn.getText().toString())
                .add("nama",etNama.getText().toString())
                .add("alamat",etAlamat.getText().toString())
                .add("kecamatan",etKecamatan.getText().toString())
                .add("kategori",etKategori.getText().toString())
                .add("lat",etLat.getText().toString())
                .add("lng",etLong.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.43.210/mobileGIS/titik.php")
                .post(reqbody)
                .addHeader("connection","Keep-Alive")
                .addHeader("connection-type","Application/x-www-urlencoded")
                .build();
        Log.i("Info Kirim ","Proses Simpan Dijalankan");
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Err Kirim",e.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(gps.this,"Pengiriman Gagal",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i("Info Kirim",response.message());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(gps.this,"Pengiriman Berhasil",
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}