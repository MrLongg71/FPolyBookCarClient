package vn.fpoly.fpolybookcarclient.View.Activity;


import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.Library.Dialog;
import vn.fpoly.fpolybookcarclient.Model.ObjectClass.OderCar;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.View.Activity.GoogleMapActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLocation_Go_ComeActivity extends AppCompatActivity {

    private LatLng locationGo,locationCome;
    private String placeNameGo,placeNameCome,keyClient;
    private AutocompleteSupportFragment chooseLocationGo,chooseLocationCome;
    private FirebaseAuth auth;
    private OderCar oderCar;
    private Intent intent;
    private Bundle bundle;
    private List<String> arrBook;

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
//        oderCar = new OderCar(keyClient,placeNameGo,placeNameCome,"",locationGo.latitude,locationGo.longitude,locationCome.latitude,locationCome.longitude,1,5,10,true ,true);

    }

    private void initView() {
        chooseLocationGo = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.chooseLocationGo);
        chooseLocationCome = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.chooseLocationCome);

        arrBook = new ArrayList<>();
        intent = new Intent();
        bundle = new Bundle();

    }
    private void eventChooseGo(){
        chooseLocationGo.setHint("Điểm đi");
        chooseLocationGo.setPlaceFields(Arrays.asList(Place.Field.NAME,Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG));
        chooseLocationGo.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                bundle.putString(Constans.KEY_BUNDEL_PLACENAME_GO,place.getName());
                bundle.putDouble(Constans.KEY_BUNDEL_LATITUDE_GO,place.getLatLng().latitude);
                bundle.putDouble(Constans.KEY_BUNDEL_LONGITUDE_GO,place.getLatLng().longitude);
                senDataResult();
            }

            @Override
            public void onError(@NonNull Status status) {

            }
        });
    }

    private void eventChooseCome(){
        chooseLocationCome.setHint("Điểm đến");
        chooseLocationCome.setPlaceFields(Arrays.asList(Place.Field.NAME,Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG));
        chooseLocationCome.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                bundle.putString(Constans.KEY_BUNDEL_PLACENAME_COME,place.getName());
                bundle.putDouble(Constans.KEY_BUNDEL_LATITUDE_COME,place.getLatLng().latitude);
                bundle.putDouble(Constans.KEY_BUNDEL_LONGITUDE_COME,place.getLatLng().longitude);
                senDataResult();
            }


            @Override
            public void onError(@NonNull Status status) {

            }
        });
    }


    private void senDataResult(){
        if(bundle.size() == 6){
            intent.putExtra(Constans.KEY_BUNDEL_BOOK,bundle);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }
    }

}
