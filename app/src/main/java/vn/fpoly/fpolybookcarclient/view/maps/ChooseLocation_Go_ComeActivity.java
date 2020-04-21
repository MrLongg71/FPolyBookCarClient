
package vn.fpoly.fpolybookcarclient.view.maps;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.library.GeocoderAddress;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLocation_Go_ComeActivity extends AppCompatActivity {

    private LatLng locationGo, locationCome;
    private String placeNameGo, placeNameCome, keyClient;
    private AutocompleteSupportFragment chooseLocationGo, chooseLocationCome;
    private FirebaseAuth auth;
    private OderCar oderCar;
    private Intent intent;
    private Bundle bundle;
    private List<String> arrBook;
    private int selected = 0;
    private LatLngBounds lngBounds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location__go__come);
        initView();
        initEvent();
    }

    private void initEvent() {
        if (!Places.isInitialized()) {
            Places.initialize(ChooseLocation_Go_ComeActivity.this, getString(R.string.google_map_api1));
        }
        eventChooseGo();
        eventChooseCome();

    }

    private void initView() {
        chooseLocationGo = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.chooseLocationGo);
        chooseLocationCome = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.chooseLocationCome);
        lngBounds = new LatLngBounds(new LatLng(10.7542869, 106.1333236), new LatLng(106.1333236, 10.7542869));
        arrBook = new ArrayList<>();
        try {
            intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setBoundsBias(lngBounds)
                    .build(this);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
        bundle = new Bundle();


    }

    private void eventChooseGo() {
        RectangularBounds.newInstance(lngBounds);
        chooseLocationGo.setHint("Diem di");
        chooseLocationGo.setCountry("vn");
        chooseLocationGo.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG));
        Intent intent = getIntent();
        if (intent != null) {
            LatLng latLngCurrent = new LatLng(intent.getDoubleExtra(Constans.KEY_BUNDEL_LATITUDE_GO, 0), intent.getDoubleExtra(Constans.KEY_BUNDEL_LONGITUDE_GO, 0));
            chooseLocationGo.setText(GeocoderAddress.getAddress(ChooseLocation_Go_ComeActivity.this, latLngCurrent.latitude, latLngCurrent.longitude));
            bundle.putString(Constans.KEY_BUNDEL_PLACENAME_GO, GeocoderAddress.getAddress(ChooseLocation_Go_ComeActivity.this, latLngCurrent.latitude, latLngCurrent.longitude));
            bundle.putDouble(Constans.KEY_BUNDEL_LATITUDE_GO, intent.getDoubleExtra(Constans.KEY_BUNDEL_LATITUDE_GO, 0));
            bundle.putDouble(Constans.KEY_BUNDEL_LONGITUDE_GO, intent.getDoubleExtra(Constans.KEY_BUNDEL_LONGITUDE_GO, 0));
        }
        chooseLocationGo.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                selected = 1;
                bundle.putString(Constans.KEY_BUNDEL_PLACENAME_GO, place.getName());
                bundle.putDouble(Constans.KEY_BUNDEL_LATITUDE_GO, place.getLatLng().latitude);
                bundle.putDouble(Constans.KEY_BUNDEL_LONGITUDE_GO, place.getLatLng().longitude);
                senDataResult();
            }

            @Override
            public void onError(@NonNull Status status) {
                finish();
            }
        });
    }

    private void eventChooseCome() {
        chooseLocationCome.setHint("Điểm đến");
        chooseLocationCome.setCountry("vn");
        chooseLocationCome.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG));
        chooseLocationCome.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                bundle.putString(Constans.KEY_BUNDEL_PLACENAME_COME, place.getName());
                bundle.putDouble(Constans.KEY_BUNDEL_LATITUDE_COME, place.getLatLng().latitude);
                bundle.putDouble(Constans.KEY_BUNDEL_LONGITUDE_COME, place.getLatLng().longitude);
                senDataResult();
            }


            @Override
            public void onError(@NonNull Status status) {
                finish();
            }
        });
    }


    private void senDataResult() {
        if (bundle.size() == 6) {
            intent.putExtra(Constans.KEY_BUNDEL_BOOK, bundle);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        if (bundle.size() == 6 && selected == 0) {
            intent.putExtra(Constans.KEY_BUNDEL_BOOK, bundle);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}
