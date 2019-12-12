package vn.fpoly.fpolybookcarclient.view.maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.EventListener;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.fpoly.fpolybookcarclient.BuildConfig;
import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.library.Dialog;
import vn.fpoly.fpolybookcarclient.model.objectClass.BillFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.presenter.maps.PresenterGoogleMap;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.activity.HomeActivity;


public class GoogleMapActivity extends AppCompatActivity implements
        OnMapReadyCallback, View.OnClickListener, ViewGoogleMap, GoogleMap.OnCameraMoveStartedListener
        , GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraIdleListener {

    private GoogleMap googleMap;
    private MapFragment mapFragment;
    private LatLng locationCurrent, locationGo, locationCome, locationDriverCar;
    private String placeNameGo, placeNameCome, placeNameCurrent, LINK;
    private final int REQUESCODE = 1;
    private TextView txtMotoMoney, txtCarMoney, txtDistanceTime, txtNameDriver, txtLicensePlateDriver;
    private CircleImageView imgDriver;
    private ImageView imgSMSDriver, imgPhoneDriver, imgInfoDriver, imgCancelDriver;
    private RatingBar rateDriver;
    private LocationManager locationManager;
    private LinearLayout layoutChooseLocation;
    private Button btnBook;
    private EditText edtLocationCurrent;
    private ProgressDialog progressDialog;
    private RelativeLayout relaLayoutChooseBike, relaLayoutChooseCar;
    private int CODE_CAR_OR_MOTO;
    private ImageButton imgButtonMyLocation;
    private ImageView imgMarker;

    private ArrayList<BillFood> billFoodArrayList = new ArrayList<>();
    private Restaurant restaurant;
    private PresenterGoogleMap presenterGoogleMap;
    private LatLng locationRestaurant;
    SharedPreferences sharedPreferences;
    String locationLatitude = "";
    String locationLongitude = "";
    String addressCurrent = "";
    double priceOrderFood = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        sharedPreferences = getSharedPreferences("toado", 0);

        locationLatitude = sharedPreferences.getString("locationlatitude", "");
        locationLongitude = sharedPreferences.getString("locationlongitude", "");

        initView();
        int checkPermissionCoarseLocaltion_COARSE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int checkPermissionCoarseLocaltion_FINE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (checkPermissionCoarseLocaltion_COARSE != PackageManager.PERMISSION_GRANTED && checkPermissionCoarseLocaltion_FINE != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GoogleMapActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUESCODE);
        } else {
            getLocationClient();
        }
        LocalBroadcastManager.getInstance(GoogleMapActivity.this).registerReceiver(mMessageReceiver, new IntentFilter("myFunction"));


    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initView() {
        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        txtMotoMoney = findViewById(R.id.txtMotoMoney);
        txtCarMoney = findViewById(R.id.txtCarMoney);
        txtDistanceTime = findViewById(R.id.txtDistanceTime);
        layoutChooseLocation = findViewById(R.id.layoutChooseLocation);
        relaLayoutChooseBike = findViewById(R.id.relaLayoutChooseBike);
        relaLayoutChooseCar = findViewById(R.id.relaLayoutChooseCar);
        btnBook = findViewById(R.id.btnBook);
        txtNameDriver = findViewById(R.id.txtNameDriver);
        txtLicensePlateDriver = findViewById(R.id.txtLicensePlateDriver);
        rateDriver = findViewById(R.id.ratingbarShow);
        imgSMSDriver = findViewById(R.id.imgSMSDriver);
        imgPhoneDriver = findViewById(R.id.imgPhoneDriver);
        imgInfoDriver = findViewById(R.id.imgInfoDriver);
        imgCancelDriver = findViewById(R.id.imgCancelDriver);
        progressDialog = new ProgressDialog(GoogleMapActivity.this);
        progressDialog.setMessage("Loading");
        presenterGoogleMap = new PresenterGoogleMap(this);
        imgMarker = findViewById(R.id.imgMarker);
        layoutChooseLocation.setOnClickListener(this);
        btnBook.setOnClickListener(this);
        imgButtonMyLocation = findViewById(R.id.imgButtonMyLocation);
        locationManager = this.getSystemService(LocationManager.class);
        edtLocationCurrent = findViewById(R.id.edtLocationCurrent);
        initEventBookFood();

    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("LONgKUTE", "onReceive: " + intent.getStringExtra("text"));

        }
    };

    private void initEventBookFood() {
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            progressDialog.show();
            billFoodArrayList = intent.getParcelableArrayListExtra(Constans.KEY_ORDERFOOD_BILLLIST);
            restaurant = intent.getParcelableExtra(Constans.KEY_ORDERFOOD_RESTAURANT);
            addressCurrent = intent.getStringExtra(Constans.KEY_ORDERFOOD_ADDRES_CURRENT);
            locationRestaurant = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
            sharedPreferences = getSharedPreferences("toado", 0);

            locationLatitude = sharedPreferences.getString("locationlatitude", "");
            locationLongitude = sharedPreferences.getString("locationlongitude", "");

            locationCurrent = new LatLng(Double.parseDouble(locationLatitude), Double.parseDouble(locationLongitude));
            priceOrderFood = (double) intent.getIntExtra(Constans.KEY_ORDERFOOD_PRICE, 0);
            mapFragment.getMapAsync(this);
//            presenterGoogleMap.getDriverList(GoogleMapActivity.this, locationRestaurant,false);
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setMinZoomPreference(6.0f);
        googleMap.setMaxZoomPreference(14.0f);

        if (locationCurrent != null) {
            placeNameCurrent = "You are here!";
            getLocationCurrent();
            imgButtonMyLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getLocationCurrent();
                }
            });

        }

        if (locationGo != null && locationCome != null) {

            addMarker(locationGo, placeNameGo, R.drawable.iconlocationblue);
            addMarker(locationCome, placeNameCome, R.drawable.iconlocationred);
            drawPolyline(locationGo, locationCome, true);
            //move camera theo lat log
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(locationGo).include(locationCome);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), 100);
            googleMap.animateCamera(cameraUpdate);
        }
        if (locationRestaurant != null && locationCurrent != null) {
//            addMarker(locationCurrent, "", R.drawable.ic_driver_bike);
            addMarker(locationRestaurant, placeNameGo, R.drawable.iconlocationblue);//nha hang
            addMarker(locationCurrent, placeNameCome, R.drawable.iconlocationred);
            drawPolyline(locationRestaurant, locationCurrent, false);
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(locationRestaurant).include(locationCurrent);

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), 0);
            googleMap.animateCamera(cameraUpdate);
        }

        googleMap.setOnCameraIdleListener(this);
        googleMap.setOnCameraMoveListener(this);

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
        String provider = BuildConfig.DEBUG ? LocationManager.GPS_PROVIDER : LocationManager.NETWORK_PROVIDER;

        locationManager.requestLocationUpdates(provider, 5000L
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
            case R.id.layoutChooseLocation:
                startActivityForResult(new Intent(GoogleMapActivity.this, ChooseLocation_Go_ComeActivity.class), REQUESCODE);
                break;
            case R.id.btnBook:
                searchDriverNearLocationGo();
                break;
        }
    }

    private void checkedChooseVerhical() {
        relaLayoutChooseBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relaLayoutChooseCar.setBackground(null);
                relaLayoutChooseBike.setBackground(ContextCompat.getDrawable(GoogleMapActivity.this, R.drawable.custom_clicked));
                CODE_CAR_OR_MOTO = 1;
            }
        });
        relaLayoutChooseCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relaLayoutChooseBike.setBackground(null);
                relaLayoutChooseCar.setBackground(ContextCompat.getDrawable(GoogleMapActivity.this, R.drawable.custom_clicked));
                CODE_CAR_OR_MOTO = 2;
            }
        });
    }

    private void addMarker(LatLng location, String place, int icon) {
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title(place)
                .icon(BitmapDescriptorFactory.fromResource(icon)));


    }

    private void searchDriverNearLocationGo() {
        if (CODE_CAR_OR_MOTO == 1) {
            loadDriverMotoList();
        } else if (CODE_CAR_OR_MOTO == 2) {
            loadDriverCarList();
        } else {
            Toast.makeText(this, "Bạn chưa chọn loại xe", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void drawPolyline(LatLng locationGo, LatLng locationCome, boolean isBookCar) {
        presenterGoogleMap.getPolyline(GoogleMapActivity.this, googleMap, locationGo, locationCome, isBookCar);

    }

    @Override
    public void showDetailDistance(int distance, int time, double price) {
        txtDistanceTime.setText("You will have to go " + distance + " km and take about " + time + " minute");
        txtMotoMoney.setText(price + "K");
        txtCarMoney.setText(15 * price + "K");
        checkedChooseVerhical();
        public void showDetailDistance ( int distance, int time, double price, boolean isBookCar){
            progressDialog.dismiss();
            findViewById(R.id.chooseservice).setVisibility(View.VISIBLE);
            layoutChooseLocation.setVisibility(View.GONE);
            if (!isBookCar) {
                relaLayoutChooseCar.setVisibility(View.GONE);
                txtDistanceTime.setText("Nhà hàng cách bạn " + distance + " km và mất khoảng " + time + " phút để nhận món ăn!");
                txtMotoMoney.setText(price + "K");
                btnBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressDialog.show();
                        presenterGoogleMap.getDriverList(GoogleMapActivity.this, locationRestaurant, false);

                    }
                });
            } else {
                txtDistanceTime.setText("Bạn sẽ  " + distance + " km và mất khoảng " + time + " phút!");
                txtCarMoney.setText(15 * price + "K");
                txtMotoMoney.setText(price + "K");
                checkedChooseVerhical();
            }


        }

        @Override
        public void loadDriverCarList () {
            findViewById(R.id.chooseservice).setVisibility(View.GONE);
            progressDialog.show();
            presenterGoogleMap.getDriverList(GoogleMapActivity.this, locationGo, true);
        }

        @Override
        public void loadDriverMotoList () {
            findViewById(R.id.chooseservice).setVisibility(View.GONE);
            progressDialog.show();
            presenterGoogleMap.getDriverList(GoogleMapActivity.this, locationGo, true);
        }

        @Override
        public void getDriverNear ( final Driver driverNear, boolean isbook){
            if (driverNear != null) {
                progressDialog.dismiss();
                findViewById(R.id.layoutInfoDriver).setVisibility(View.VISIBLE);
                txtNameDriver.setText(driverNear.getName());
                txtLicensePlateDriver.setText(driverNear.getLicenseplate());
                rateDriver.setRating((float) driverNear.getRate());
                locationDriverCar = new LatLng(driverNear.getLatitude(), driverNear.getLongitude());
                mapFragment.getMapAsync(this);
                if (isbook) {
                    presenterGoogleMap.pushOrderToDriver(driverNear, locationGo, locationCome, placeNameGo, placeNameCome);

                } else {
                    presenterGoogleMap.pushOrderFoodToDriver(driverNear, billFoodArrayList, locationCurrent, restaurant, restaurant.getAddress(), addressCurrent, priceOrderFood);

                }

            }
            imgInfoDriver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInfoDriver(driverNear);
                }
            });
            imgPhoneDriver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPhoneCallDriver(driverNear);
                }
            });

        }

        private void showInfoDriver (Driver driver){
            Toast.makeText(this, " " + driver.getDistance(), Toast.LENGTH_SHORT).show();
        }

        private void openPhoneCallDriver (Driver driver){
            Toast.makeText(this, "" + driver.getPhone(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void getDriverNearFailed () {
            new KAlertDialog(GoogleMapActivity.this)
                    .setContentText(getString(R.string.getDriverNearFailed))
                    .setConfirmText("ok")
                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                        @Override
                        public void onClick(KAlertDialog kAlertDialog) {
                            startActivity(new Intent(GoogleMapActivity.this, HomeActivity.class));
                        }
                    }).show();
        }

        @Override
        public void onCameraMoveStarted ( int i){
        }



        private void getLocationCurrent () {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(locationCurrent, 18f);
            googleMap.animateCamera(cameraUpdate);
        }

    }
    @Override
    public void onBackPressed () {
        if (findViewById(R.id.layoutInfoDriver).getVisibility() == View.VISIBLE) {
            Dialog.Error(GoogleMapActivity.this, "Đã có chuyến xe, k ");
        } else {
            super.onBackPressed();
            startActivity(new Intent(GoogleMapActivity.this, HomeActivity.class));
            finish();
        }
    }

}
