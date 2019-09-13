package vn.fpoly.fpolybookcarclient.View.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.logicbeanzs.uberpolylineanimation.MapAnimator;

import java.util.List;
import java.util.concurrent.ExecutionException;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.Library.Dialog;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.Service.DownloadPolyLine;
import vn.fpoly.fpolybookcarclient.Service.ParserPolyline;

public class GoogleMapActivity extends AppCompatActivity implements
        OnMapReadyCallback, View.OnClickListener {

    private GoogleMap googleMap;
    private MapFragment mapFragment;
    private FusedLocationProviderClient fusedLocationClient;
    private LatLng locationCurrent, locationGo, locationCome;
    private String placeNameGo, placeNameCome, placeNameCurrent,LINK;
    private final int REQUESCODE = 1;
    public ParserPolyline parserPolyline;
    private DownloadPolyLine downloadPolyLine;
    private LocationManager locationManager;
    private RadioButton radioButton1,radioButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        initView();
        int checkPermissionCoarseLocaltion_COARSE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int checkPermissionCoarseLocaltion_FINE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (checkPermissionCoarseLocaltion_COARSE != PackageManager.PERMISSION_GRANTED && checkPermissionCoarseLocaltion_FINE != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GoogleMapActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUESCODE);
        } else {
            getLocationClient();
        }

        findViewById(R.id.edt).setOnClickListener(this);
        
        checkedRadioButton();


    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initView() {
        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        locationManager = this.getSystemService(LocationManager.class);
        radioButton1    = findViewById(R.id.radio1);
        radioButton2    = findViewById(R.id.radio2);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (locationCurrent != null) {
            placeNameCurrent = "Vị trí của bạn"; //chuyển sai file string dùm t
            addMarker(locationCurrent, placeNameCurrent, R.drawable.icon_laban);

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(locationCurrent, 18f);
            googleMap.animateCamera(cameraUpdate);

        }
        if (locationGo != null && locationCome != null) {

            addMarker(locationGo, placeNameGo, R.drawable.icon_motorbike);
            addMarker(locationCome, placeNameCome, R.drawable.icon_user);
            drawPolyline();
            //move camera theo lat log
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(locationGo).include(locationCome);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), 100);
            googleMap.animateCamera(cameraUpdate);
        }
    }


    private void drawPolyline() {
        LINK = Constans.LINK_GOOGLE_API_DRAW_POLYLINE + locationGo.latitude  + Constans.Comma + locationGo.longitude + Constans.Destination + locationCome.latitude +Constans.Comma +locationCome.longitude + Constans.Language + getString(R.string.google_map_api2);
        parserPolyline = new ParserPolyline();
        downloadPolyLine = new DownloadPolyLine();

        //open dowload
        downloadPolyLine.execute(LINK);

        try {
            String dataJSON = downloadPolyLine.get();
            List<LatLng> latLngList = parserPolyline.getListLocation(dataJSON);
            int disttance = parserPolyline.getDistance(dataJSON);
            int time = parserPolyline.getTime(dataJSON);
            PolylineOptions polylineOptions = new PolylineOptions();
            for(LatLng latLng : latLngList){
                polylineOptions.add(latLng);
            }
            Polyline polyline = googleMap.addPolyline(polylineOptions);
            polyline.setColor(Color.rgb(0,191,255));

            //set màu sao nhìn đc
//            MapAnimator.getInstance().setSecondaryLineColor();
            MapAnimator.getInstance().animateRoute(googleMap,latLngList);

            //show thông tin -> tách hàm ra như t
            showDetailDistance(disttance,time);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void showDetailDistance(int distance, int time) {
        new AlertDialog.Builder(this)
                .setTitle("Thông báp Leader")
                .setMessage("Bạn phải đi " + distance / 1000 + " km" + " Tổng tiền là " + (distance / 1000) * 2000 + " và mất khoảng "+
                        time/60 +" phút "+ " nha giám đốc").show();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUESCODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocationClient();
                } else {
                    Toast.makeText(this, "Bạn chưa cấp quyền", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocationClient() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L
                , 500.0F, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        locationCurrent = new LatLng(location.getLatitude(), location.getLongitude());
                        mapFragment.getMapAsync(GoogleMapActivity.this);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });


//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(GoogleMapActivity.this);
//                fusedLocationClient.getLastLocation()
//                        .addOnSuccessListener(GoogleMapActivity.this, new OnSuccessListener<Location>() {
//                            @Override
//                            public void onSuccess(Location location) {
//                                if (location != null) {
//                                    Log.d("kiemtra", location.getLatitude() + " - " + location.getLongitude());
//                                    locationCurrent = new LatLng(location.getLatitude(),location.getLongitude());
//                                    mapFragment.getMapAsync(GoogleMapActivity.this);
//                                } else {
//                                    Log.d("dd", "dd");
//                                }
//                            }
//                        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUESCODE:
                if (resultCode == RESULT_OK && data != null) {
                    Bundle bundle = data.getBundleExtra(Constans.KEY_BUNDEL_BOOK);
                    locationGo = new LatLng(bundle.getDouble(Constans.KEY_BUNDEL_LATITUDE_GO), bundle.getDouble(Constans.KEY_BUNDEL_LONGITUDE_GO));
                    locationCome = new LatLng(bundle.getDouble(Constans.KEY_BUNDEL_LATITUDE_COME), bundle.getDouble(Constans.KEY_BUNDEL_LONGITUDE_COME));
                    placeNameGo = bundle.getString(Constans.KEY_BUNDEL_PLACENAME_GO);
                    placeNameCome = bundle.getString(Constans.KEY_BUNDEL_PLACENAME_COME);

                    mapFragment.getMapAsync(this);
                }

                break;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt:
                startActivityForResult(new Intent(GoogleMapActivity.this, ChooseLocation_Go_ComeActivity.class), REQUESCODE);
                findViewById(R.id.edt).setVisibility(View.GONE);
                findViewById(R.id.edt1).setVisibility(View.GONE);
                findViewById(R.id.chooseservice).setVisibility(View.VISIBLE);
                break;

        }
    }

    private void addMarker(LatLng location, String place, int icon) {
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title(place)
                .icon(BitmapDescriptorFactory.fromResource(icon)));

    }
    private void checkedRadioButton(){
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton1.setChecked(true);
                radioButton2.setChecked(false);
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton1.setChecked(false);
                radioButton2.setChecked(true);
            }
        });
    }

}
