package vn.mrlongg71.demogooglemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    LatLng sydney;
    LatLng sydney2;
    AutocompleteSupportFragment autocompleteFragment, autocompleteFragment2;

    Intent bundle = new Intent();
    List<String> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initView();

    }

    private void initView() {
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCgLG2TluJwPxKrP7n491SilbiYEHyJm0c");
        }
        autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragmentA);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.NAME,Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG));
        autocompleteFragment.setHint("Điểm đến");
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                sydney = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                arr.add(place.getName());//0
                arr.add(String.valueOf(place.getLatLng().latitude));
                arr.add(String.valueOf(place.getLatLng().longitude));
                autocompleteFragment.setText(place.getName());
                senData();
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.d("Err", status.getStatusMessage());
            }
        });
        autocompleteFragment2 = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragmentB);
        autocompleteFragment2.setPlaceFields(Arrays.asList(Place.Field.NAME,Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG));
        autocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                sydney2 = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                arr.add(place.getName());

                arr.add(String.valueOf(place.getLatLng().latitude));
                arr.add(String.valueOf(place.getLatLng().longitude));

                autocompleteFragment2.setText(place.getName());
                senData();
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.d("Err", status.getStatusMessage());
            }
        });

    }
    public void senData(){
        if(arr.size() == 6){
            bundle.putStringArrayListExtra("name", (ArrayList<String>) arr);

            setResult(Activity.RESULT_OK,bundle);

            finish();
        }
    }
}
