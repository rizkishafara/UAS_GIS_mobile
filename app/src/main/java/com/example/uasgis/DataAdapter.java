package com.example.uasgis;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends BaseAdapter {
    List<Data> daftar;
    Context context;

    public DataAdapter(Context ctx){
        this.context=ctx;
        daftar = new ArrayList<>();
    }

    public void loadData(JSONObject jo){
        try{
            JSONArray data = jo.getJSONArray("features");
            daftar.clear();

            for(int i=0; i<data.length();i++){
                JSONObject obj = data.getJSONObject(i);
                Data d = new Data(obj.getJSONObject("properties").getString("npsn"),
                        obj.getJSONObject("properties").getString("nama"),
                        obj.getJSONObject("properties").getString("kategori"),
                        obj.getJSONObject("properties").getString("alamat"),
                        obj.getJSONObject("properties").getString("kecamatan")
                );
                daftar.add(d);
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.e("Error on loadData : ",e.toString());
        }
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return daftar.size();
    }

    @Override
    public Object getItem(int i) {
        return daftar.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view =inflater.inflate(R.layout.list_sekolah,viewGroup,false);
        TextView tvId = view.findViewById(R.id.tvId);
        TextView tvNama = view.findViewById(R.id.tvNama);
        TextView tvKategori = view.findViewById(R.id.tvKategori);
        TextView tvAlamat = view.findViewById(R.id.tvAlamat);
        TextView tvKec = view.findViewById(R.id.tvKec);
        Data d = daftar.get(i);
        tvId.setText(d.gid);
        tvNama.setText(d.nama);
        tvKategori.setText(d.kategori);
        tvAlamat.setText(d.alamat);
        tvKec.setText(d.kecamatan);

        return view;
    }

}
