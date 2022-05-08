package com.example.bankconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFr=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFr.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map=googleMap;
        LatLng agency1=new LatLng(34.0016, -6.84711);
        map.addMarker(
                new MarkerOptions().position(agency1)
                        .title("Agdal agency, Rabat")
                        .snippet("0544404139").draggable(true)
        );
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(agency1,10.0f));


        LatLng agency2=new LatLng(34.25937, -6.58961);
        map.addMarker(new MarkerOptions().position(agency2).title("Mimousas agency, Kenitra").snippet("0588888888").draggable(true));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(agency2,10.0f));

        LatLng agency3=new LatLng(34.06470507, -6.788795);
        map.addMarker(new MarkerOptions().position(agency3).title("Lmrisa agency, Sale").snippet("0533778899").draggable(true));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(agency3,10.0f));

        map.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

        call(marker.getSnippet());
        Toast.makeText(getApplicationContext(),marker.getTitle(),Toast.LENGTH_SHORT).show();

    }


    public void call(String phone){

        String s="tel:"+phone;
        Intent intent= new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(s));

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        }

    }



}