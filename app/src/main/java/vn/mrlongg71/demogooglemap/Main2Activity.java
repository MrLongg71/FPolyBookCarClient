package vn.mrlongg71.demogooglemap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main2Activity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    GoogleMap googleMap;
    MapFragment mapFragment;
    double latitudeA, longitudeA, latitudeB, longitudeB;
    ParserPolyline parserPolyline;
    DownloadPolyLine downloadPolyLine;
    String duongdan;
    LatLng sydney;
    LatLng sydney2;
    EditText edt, edt1;
    List<String> arr = new ArrayList<>();
    AutocompleteSupportFragment autocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        latitudeA = 10.7908587;
        longitudeA = 106.6799722;
        latitudeB = 10.7865253;
        longitudeB = 106.6784783;

        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        edt = findViewById(R.id.edt);
        edt.setOnClickListener(this);
        edt1 = findViewById(R.id.edt1);
        edt1.setOnClickListener(this);

    }

    private void setupAutoCompleteFragment() {
//        if (!Places.isInitialized()) {
//            Places.initialize(getApplicationContext(), "AIzaSyCgLG2TluJwPxKrP7n491SilbiYEHyJm0c");
//        }
//       autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.ADDRESS,Place.Field.LAT_LNG));
//        autocompleteFragment.setHint("Điểm đến");
//
//        autocompleteFragment.startActivity(new Intent(Main2Activity.this, Main3Activity.class));

//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NonNull Place place) {
//                sydney = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
//
//
//                mapFragment.getMapAsync(Main2Activity.this);
//
//            }
//
//            @Override
//            public void onError(@NonNull Status status) {
//                Log.d("Err" , status.getStatusMessage());
//            }
//        });
//        AutocompleteSupportFragment autocompleteFragment2 = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);
//        autocompleteFragment2.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.ADDRESS,Place.Field.LAT_LNG));
//
//
//        autocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NonNull Place place) {
//
//                sydney2 = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
//                Log.d("kiemtrabbb" , place.getName() + " - " + place.getAddress() + " - " +   sydney2);
//
//                mapFragment.getMapAsync(Main2Activity.this);
//
//            }
//
//            @Override
//            public void onError(@NonNull Status status) {
//                Log.d("Err" , status.getStatusMessage());
//            }
//        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.clear();


//
//        LatLng latLng = new LatLng(latitudeA,longitudeA);
        if (sydney != null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(sydney);
            googleMap.addMarker(markerOptions);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(sydney, 14f);
            googleMap.moveCamera(cameraUpdate);

        }

////
//        LatLng vitriquanan = new LatLng(latitudeB,longitudeB);
        if (sydney2 != null) {
            MarkerOptions markervitriquanan = new MarkerOptions();
            markervitriquanan.position(sydney2);
            googleMap.setContentDescription("fsdfsd");
            googleMap.addMarker(markervitriquanan);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(sydney2, 14f);
            googleMap.moveCamera(cameraUpdate);
        }


        if (sydney2 != null && sydney != null) {
            duongdan = "https://maps.googleapis.com/maps/api/directions/json?origin=" + sydney.latitude + "," + sydney.longitude + "&destination=" + sydney2.latitude + "," + sydney2.longitude + "&language=vi&key=AIzaSyCT-1dJjVqnYIcMt3kVVIFiVFKnbbdLq1M";

            parserPolyline = new ParserPolyline();
            downloadPolyLine = new DownloadPolyLine();
            downloadPolyLine.execute(duongdan);

            try {
                String dataJSON = downloadPolyLine.get();
                Log.d("kiemtra", dataJSON);
                String a = "";
                List<LatLng> latLngList = parserPolyline.LayDanhSachToaDo(dataJSON);
                a = parserPolyline.LayKM(dataJSON);
                PolylineOptions polylineOptions = new PolylineOptions();
                for (LatLng toado : latLngList) {
                    Log.d("kiemtratoadoa", toado.latitude + " - " + toado.longitude);
                    polylineOptions.add(toado);
                }

                Polyline polyline = googleMap.addPolyline(polylineOptions);
                Toast.makeText(this, "distance " + a, Toast.LENGTH_SHORT).show();

//                LatLngBounds total = new LatLngBounds(sydney, sydney2);
//                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(total,0));
//                googleMap.animateCamera(CameraUpdateFactory.zoomIn());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

//            Location a = new Location("");
//            Log.d("ccc", latitudeA + "");
//            a.setLatitude(latitudeA);
//            Log.d("ccc", a.getLatitude() + "");
//            a.setLongitude(longitudeA);
//            Location b = new Location("");
//            b.setLatitude(latitudeB);
//            b.setLongitude(longitudeB);
//            double c = (a.distanceTo(b)) / 1000;
//            Log.d("kiemtra", c + "");
        // }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                arr = data.getStringArrayListExtra("name");
                String name = arr.get(0);
                String name1 = arr.get(3);

                edt.setText(name);
                edt1.setText(name1);


                sydney = new LatLng(Double.parseDouble(arr.get(1)), Double.parseDouble(arr.get(2)));
                sydney2 = new LatLng(Double.parseDouble(arr.get(4)), Double.parseDouble(arr.get(5)));
                if (sydney != null && sydney2 != null) {
                    mapFragment.getMapAsync(this);
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt:
                startActivityForResult(new Intent(Main2Activity.this, Main3Activity.class), 1);
                break;
            case R.id.edt1:
                startActivityForResult(new Intent(Main2Activity.this, Main3Activity.class), 1);
                break;
        }
    }
}
