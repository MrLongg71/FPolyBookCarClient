package vn.fpoly.fpolybookcarclient.view.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.fpoly.fpolybookcarclient.BuildConfig;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.Fragment.AccountFragment;
import vn.fpoly.fpolybookcarclient.view.Fragment.ActivityFragment;
import vn.fpoly.fpolybookcarclient.view.Fragment.HomeFragment;
import vn.fpoly.fpolybookcarclient.view.Fragment.MailFragment;
import vn.fpoly.fpolybookcarclient.view.maps.GoogleMapActivity;

public class HomeActivity extends AppCompatActivity {

    private int REQUESCODE = 1234;
    private LocationManager locationManager;
    private LatLng locationCurrent;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences  = getSharedPreferences("toado",MODE_PRIVATE);

        locationManager = this.getSystemService(LocationManager.class);
       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setItemIconTintList(null);
        navView.setItemTextColor(getResources().getColorStateList(R.color.colorPrimary));
        loadFragment(new HomeFragment());

        int checkPermissionCoarseLocaltion_COARSE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int checkPermissionCoarseLocaltion_FINE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (checkPermissionCoarseLocaltion_COARSE != PackageManager.PERMISSION_GRANTED && checkPermissionCoarseLocaltion_FINE != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUESCODE);
        } else {

        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.navigation_activity:
                    loadFragment(new ActivityFragment());
                    return true;
                case R.id.navigation_email:
                    loadFragment(new MailFragment());
                    return true;
                case R.id.navigation_accout:
                    loadFragment(new AccountFragment());
                    return true;

            }
            return false;
        }
    };

    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.conten, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESCODE  && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLocationClient();
        }else {
                finish();
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
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("locationlatitude",locationCurrent.latitude+"");
                        editor.putString("locationlongitude",locationCurrent.longitude+"");
                        editor.commit();
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

}
