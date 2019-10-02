package com.example.naskayone.Modules;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.naskayone.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LastLocationModule {

    private MainActivity mCtx;
    String TAG = "LAST LOCATION MODULE";
    private FusedLocationProviderClient fusedLocationClient ;
    Activity context;
    public LastLocationModule(MainActivity ctx ) {
        this.mCtx = ctx;
    }

    public void getLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mCtx);

        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    // Logic to handle location object
                    Log.d(TAG, "onSuccess: "+
                            location.getLatitude()+"\n" +
                            location.getProvider()+"\n" + location.getBearing()+"\n"+location.getElapsedRealtimeNanos());
                }
                else
                    Log.d(TAG, "onFailure: ");
            }
        });
    }
}