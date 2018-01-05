package mydigitalschool.esteban.dogtracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.webkit.GeolocationPermissions;
import android.widget.TextView;


import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;

import mydigitalschool.esteban.dogtracker.Classes.Dog;
import mydigitalschool.esteban.dogtracker.Classes.Position;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ArrayList<Dog> dogArrayList;
    private Date time = new Date();
    private double myLatitude;
    private double myLongitude;
    private Handler handler= new Handler();
    private int timeInMillis = 5000;
    private TextView textView3;
    private GoogleMap mMap;
    private GeolocationPermissions geolocationPermissions;
    private GoogleApi googleApi;
    private GoogleApiClient googleApiClient;
    private GoogleMapOptions googleMapOptions;
    private TextView tvNameSelectedDog;
    private String location = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyDgdsCuM9te-U_7GM1qDuQEvHvwXNOaKuo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        String nomChien = intent.getStringExtra(AccountActivity.NOMCHIEN);
        tvNameSelectedDog = findViewById(R.id.tvNameSelectedDog);
        tvNameSelectedDog.setText(nomChien);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);
        String provider = locationManager.getBestProvider(criteria,true);
        mMap.setMaxZoomPreference(30);
        mMap.setMinZoomPreference(10);
        Location myLocation = locationManager.getLastKnownLocation(provider);
        myLatitude = myLocation.getLatitude();
        myLongitude = myLocation.getLongitude();

        dogArrayList = new ArrayList<Dog>();


        LatLng latLng= new LatLng(myLatitude,myLongitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.addMarker(new MarkerOptions().position(latLng).title("Emplacement du chien"));
        System.out.println(myLatitude);

        System.out.println(myLongitude);
    }

    public void ajoutMarqueursChiens(){

    }


}
