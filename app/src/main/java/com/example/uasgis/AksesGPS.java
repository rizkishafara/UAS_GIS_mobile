package com.example.uasgis;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class AksesGPS implements LocationListener {
    Context context;
    Location loc;

    public AksesGPS(Context ctx){

        this.context=ctx;

    }

    public Location ambilLokasi() {
        LocationManager lm =(LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){

        }else{
            Log.i("GPS Error","Non Aktif ACCESS_COARSE_LOCATION");
        }

        if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50,this);
            loc =lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        return loc;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
