package com.example.naskayone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.naskayone.Modules.LastLocationModule;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    String TAG = "TAG";
    ImageView camera;
    Bitmap bmp;

    private  FusedLocationProviderClient fusedLocationClient ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LastLocationModule lastLocationModule = new LastLocationModule(this);
        lastLocationModule.getLocation();

//        mpunchIn = findViewById(R.id.punch_in);
//        camera = findViewById(R.id.camera);


//        textClock.setFormat12Hour("hh:mm:ss a");
        //textClock.setFormat24Hour("dd/MM/yyyy hh:mm:ss a");
//        textClock.setFormat24Hour(null);

//        camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,0);
//            }
//        });
//
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==0&&resultCode==RESULT_OK&&data!=null) {
            Bundle b = data.getExtras();
            bmp = (Bitmap) b.get("data");
            camera.setImageBitmap(bmp);
        }
        else
            Toast.makeText(this, "click picture", Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void punchIn(View view) {

        Toast.makeText(this, "Time : ", Toast.LENGTH_SHORT).show();
    }

    public void getLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    // Logic to handle location object
                    Toast.makeText(MainActivity.this, ""+location.getLatitude(), Toast.LENGTH_SHORT).show();
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
