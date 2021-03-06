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
import android.view.WindowManager;
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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.fpoly.fpolybookcarclient.BuildConfig;
import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.library.Dialog;
import vn.fpoly.fpolybookcarclient.library.GeocoderAddress;
import vn.fpoly.fpolybookcarclient.model.objectClass.BillFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.presenter.maps.PresenterGoogleMap;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.activity.HomeActivity;
import vn.fpoly.fpolybookcarclient.view.order.ReviewOrderActivity;


public class GoogleMapActivity extends AppCompatActivity implements
        OnMapReadyCallback, View.OnClickListener, ViewGoogleMap, GoogleMap.OnCameraIdleListener {

    private GoogleMap googleMap;
    private MapFragment mapFragment;
    private LatLng locationCurrent, locationGo, locationCome, locationDriverCar;
    private String placeNameGo, placeNameCome, placeNameCurrent, LINK;
    private final int REQUESCODE = 1;
    private TextView txtMotoMoney, txtCarMoney, txtDistanceTime, txtNameDriver, txtLicensePlateDriver;
    private CircleImageView imgDriver;
    private ImageView imgSMSDriver, imgPhoneDriver, imgCancelDriver;
    private RatingBar rateDriver;
    private LocationManager locationManager;
    private LinearLayout layoutChooseLocation,lineInfoDriver;
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
    int cancel = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        GoogleMapActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPreferences = getSharedPreferences("toado", 0);

        locationLatitude = sharedPreferences.getString("locationlatitude", "");
        locationLongitude = sharedPreferences.getString("locationlongitude", "");

        locationCurrent = new LatLng(Double.parseDouble(locationLatitude), Double.parseDouble(locationLongitude));
        initView();

        int checkPermissionCoarseLocaltion_COARSE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int checkPermissionCoarseLocaltion_FINE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (checkPermissionCoarseLocaltion_COARSE != PackageManager.PERMISSION_GRANTED && checkPermissionCoarseLocaltion_FINE != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GoogleMapActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUESCODE);
        } else {
            getLocationClient();
        }
        LocalBroadcastManager.getInstance(GoogleMapActivity.this).registerReceiver(mMessageReceiver, new IntentFilter(Constans.REVIEW_ORDER));


    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String idOrder = intent.getStringExtra(Constans.REVIEW_ORDER_ID);
            String event = intent.getStringExtra(Constans.REVIEW_ORDER_EVENT);

            Log.d("LONgKUTE", "onReceive: " + idOrder + " - " + event);

            Intent review = new Intent(new Intent(GoogleMapActivity.this, ReviewOrderActivity.class));
            review.putExtra(Constans.REVIEW_ORDER_ID, idOrder);
            review.putExtra(Constans.REVIEW_ORDER_EVENT, event);
            startActivity(review);

            finish();

        }
    };

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
        imgCancelDriver = findViewById(R.id.imgCancelDriver);
        progressDialog = new ProgressDialog(GoogleMapActivity.this);
        progressDialog.setMessage("Loading");
        presenterGoogleMap = new PresenterGoogleMap(this);
        imgMarker = findViewById(R.id.imgMarker);
        layoutChooseLocation.setOnClickListener(this);
        btnBook.setOnClickListener(this);
        imgButtonMyLocation = findViewById(R.id.imgButtonMyLocation);
        locationManager = this.getSystemService(LocationManager.class);
        lineInfoDriver  = findViewById(R.id.layoutInfoDriver);
        edtLocationCurrent = findViewById(R.id.edtLocationCurrent);
        initEventBookFood();

    }


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
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setMinZoomPreference(6.0f);
        googleMap.setMaxZoomPreference(14.0f);

        if (layoutChooseLocation.getVisibility() == View.VISIBLE) {

            placeNameCurrent = "You are here!";
            getLocationCurrent();
            imgButtonMyLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getLocationCurrent();
                }
            });
            edtLocationCurrent.setText(GeocoderAddress.getAddress(GoogleMapActivity.this, locationCurrent.latitude, locationCurrent.longitude));
//            googleMap.setOnCameraIdleListener(this);
            lisenerCameraMove();
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

    }

    private void lisenerCameraMove() {
        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng center = googleMap.getCameraPosition().target;
                if (center != null) {
                    locationCurrent = center;
                    edtLocationCurrent.setText(GeocoderAddress.getAddress(GoogleMapActivity.this, center.latitude, center.longitude));

                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocationClient() {
        String provider = BuildConfig.DEBUG ? LocationManager.GPS_PROVIDER : LocationManager.NETWORK_PROVIDER;
        locationManager.requestLocationUpdates(provider, 5000L
                , 500.0F, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                        locationCurrent = new LatLng(location.getLatitude(), location.getLongitude());
                        progressDialog.dismiss();
                        mapFragment.getMapAsync(GoogleMapActivity.this);


                        if (locationCurrent != null) {
                            locationCurrent = new LatLng(location.getLatitude(), location.getLongitude());
                            mapFragment.getMapAsync(GoogleMapActivity.this);
                        } else {
                            Toast.makeText(GoogleMapActivity.this, "đéo định vị đc", Toast.LENGTH_SHORT).show();
                            finish();
                        }

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUESCODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationClient();
            } else {
                Toast.makeText(this, "Bạn chưa cấp quyền", Toast.LENGTH_SHORT).show();
            }
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESCODE) {
            if (resultCode == RESULT_OK && data != null) {
                Bundle bundle = data.getBundleExtra(Constans.KEY_BUNDEL_BOOK);

                if (bundle.size() == 6) {
                    locationGo = new LatLng(bundle.getDouble(Constans.KEY_BUNDEL_LATITUDE_GO), bundle.getDouble(Constans.KEY_BUNDEL_LONGITUDE_GO));
                    locationCome = new LatLng(bundle.getDouble(Constans.KEY_BUNDEL_LATITUDE_COME), bundle.getDouble(Constans.KEY_BUNDEL_LONGITUDE_COME));
                    placeNameGo = bundle.getString(Constans.KEY_BUNDEL_PLACENAME_GO);
                    placeNameCome = bundle.getString(Constans.KEY_BUNDEL_PLACENAME_COME);
                }
                mapFragment.getMapAsync(this);
            }
        }

    }

    @Override
    public void showDetailDistance(int distance, int time, double price, boolean isBookCar) {
        progressDialog.dismiss();
        findViewById(R.id.chooseservice).setVisibility(View.VISIBLE);
        imgMarker.setVisibility(View.GONE);
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

    @SuppressLint("MissingPermission")


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutChooseLocation:
                Intent intent = new Intent(GoogleMapActivity.this, ChooseLocation_Go_ComeActivity.class);
                if (locationCurrent != null) {
                    intent.putExtra(Constans.KEY_BUNDEL_LATITUDE_GO, locationCurrent.latitude);
                    intent.putExtra(Constans.KEY_BUNDEL_LONGITUDE_GO, locationCurrent.longitude);
                }
                startActivityForResult(intent, REQUESCODE);
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


    private void searchDriverNearLocationGo() {
        if (CODE_CAR_OR_MOTO == 1) {
            loadDriverMotoList();
        } else if (CODE_CAR_OR_MOTO == 2) {
            loadDriverCarList();
        } else {
            Toast.makeText(this, "Bạn chưa chọn loại xe", Toast.LENGTH_SHORT).show();
        }

    }


    private void addMarker(LatLng location, String place, int icon) {
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title(place)
                .icon(BitmapDescriptorFactory.fromResource(icon)));


    }

    @Override
    public void drawPolyline(LatLng locationGo, LatLng locationCome, boolean isBookCar) {
        presenterGoogleMap.getPolyline(GoogleMapActivity.this, googleMap, locationGo, locationCome, isBookCar);
    }


    @Override
    public void loadDriverCarList() {
        findViewById(R.id.chooseservice).setVisibility(View.GONE);
        progressDialog.show();
        presenterGoogleMap.getDriverList(GoogleMapActivity.this, locationGo, true);
    }


    @Override
    public void loadDriverMotoList() {
        findViewById(R.id.chooseservice).setVisibility(View.GONE);
        progressDialog.show();
        presenterGoogleMap.getDriverList(GoogleMapActivity.this, locationGo, true);
    }


    @Override
    public void getDriverNear(final Driver driverNear, boolean isbook) {
        if (driverNear != null) {
            progressDialog.dismiss();

           lineInfoDriver.setVisibility(View.VISIBLE);

            findViewById(R.id.layoutInfoDriver).setVisibility(View.VISIBLE);
            findViewById(R.id.layoutInfoDriver).setClickable(true);
            findViewById(R.id.layoutInfoDriver).setFocusable(true);


            txtNameDriver.setText(driverNear.getName());
            txtLicensePlateDriver.setText(driverNear.getLicenseplate());
            rateDriver.setRating((float) driverNear.getRate());
            locationDriverCar = new LatLng(driverNear.getLatitude(), driverNear.getLongitude());
            mapFragment.getMapAsync(this);
            if (isbook) {
//                lineInfoDriver.setFocusable(true);
//                lineInfoDriver.setClickable(true);
                presenterGoogleMap.pushOrderToDriver(driverNear, locationGo, locationCome, placeNameGo, placeNameCome);


            } else {
                addressCurrent = GeocoderAddress.getAddress(GoogleMapActivity.this, locationCurrent.latitude, locationCurrent.longitude);
                presenterGoogleMap.pushOrderFoodToDriver(driverNear, billFoodArrayList, locationCurrent, restaurant, restaurant.getAddress(), addressCurrent, priceOrderFood);
//                imgInfoDriver.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (driverNear != null) {
//                            showInfoDriver(driverNear);
//                        }
//                    }
//                });

                imgPhoneDriver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("aiii1","fff");

                        Toast.makeText(GoogleMapActivity.this, "a", Toast.LENGTH_SHORT).show();
                        if (driverNear != null) {
                            openPhoneCallDriver(driverNear);
                        }
                    }
                });
                imgCancelDriver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cancel != 0) {

                            finish();
                        } else {
                            cancel = 1;

                        }

                    }
                });
            }

        }
    }


    private void showInfoDriver(Driver driver) {
        Toast.makeText(this, " " + driver.getDistance(), Toast.LENGTH_SHORT).show();
    }

    private void openPhoneCallDriver(Driver driver) {
        Toast.makeText(this, "" + driver.getPhone(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDriverNearFailed() {
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


    private void getLocationCurrent() {
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(locationCurrent, 18f);
        googleMap.animateCamera(cameraUpdate);
    }

    @Override
    public void onBackPressed() {
        if (findViewById(R.id.layoutInfoDriver).getVisibility() == View.VISIBLE) {
            Dialog.Error(GoogleMapActivity.this, "Đã có chuyến xe, k ");
        } else {
            super.onBackPressed();
            startActivity(new Intent(GoogleMapActivity.this, HomeActivity.class));
            finish();
        }
    }

    @Override
    public void onCameraIdle() {
        LatLng center = googleMap.getCameraPosition().target;
        if (center != null && layoutChooseLocation.getVisibility() == View.VISIBLE) {
            locationCurrent = center;
            addressCurrent = GeocoderAddress.getAddress(GoogleMapActivity.this, locationCurrent.latitude, locationCurrent.longitude);
            edtLocationCurrent.setText(GeocoderAddress.getAddress(GoogleMapActivity.this, center.latitude, center.longitude));
        }
    }
}


