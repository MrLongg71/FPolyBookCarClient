package vn.fpoly.fpolybookcarclient.presenter.maps;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.maps.ModelGoogleMap;
import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.PushOrderToDriver;
import vn.fpoly.fpolybookcarclient.view.maps.ViewGoogleMap;

public class PresenterGoogleMap implements IPPresenterGoogleMap {
    ViewGoogleMap viewGoogleMap;
    ModelGoogleMap modelGoogleMap;
    List<Driver> driverList = new ArrayList<>();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    double pricee = 0;
    int distancee = 0;

    public PresenterGoogleMap(ViewGoogleMap viewGoogleMap) {
        this.viewGoogleMap = viewGoogleMap;
        modelGoogleMap = new ModelGoogleMap();
        FirebaseMessaging.getInstance().subscribeToTopic("android");


    }


    @Override
    public void getPolyline(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome) {
        modelGoogleMap.dowlodPolylineList(activity, googleMap, locationGo, locationCome, this);


    }

    @Override
    public void getDetailDistance(int distance, int time, double price) {
        if (distance > 10) {
            price = 10000 * distance;
        }
        price = 5000 * distance;
        pricee = price;
        distancee = distance;
        viewGoogleMap.showDetailDistance(distance, time, price);

    }

    @Override
    public void getDriverList(Activity activity, LatLng locationGo) {
        modelGoogleMap.dowloadDriverCarList(activity, locationGo, this);
    }

    @Override
    public void distanceDriverNear(Driver driver) {

        driverList.add(driver);

        if (driverList.size() > 0) {
            if (driver.isStatus() && !driver.isWorking()) {
                Driver driverProvisional = driverList.get(0);
                for (Driver driverValue : driverList) {
                    Log.d("tets", driverProvisional.getDistance() + " - " + driverValue.getDistance());
                    if (driverProvisional.getDistance() > driverValue.getDistance()) {
                        driverProvisional = driverValue;
                    }
                }
                viewGoogleMap.getDriverNear(driverProvisional);

            }
        }


    }

    @Override
    public void pushOrderToDriver(Driver driver, LatLng locationGo, String placeNameGo, String placeNameCome, LatLng locationCome) {
        if (driver != null) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            String date = simpleDateFormat.format(calendar.getTime());

            final PushOrderToDriver pushOrderToDriver = new PushOrderToDriver();
            String keyOrder = database.push().getKey();

            OderCar oderCar = new OderCar(keyOrder, firebaseAuth.getCurrentUser().getUid(), driver.getKeydriver()
                    , placeNameGo, placeNameCome, date, locationGo.latitude, locationGo.longitude, locationCome.latitude
                    , locationCome.longitude, pricee, driver.getRate(), distancee, false, false);
            database.child("OrderCar").child(keyOrder).setValue(oderCar).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        database = firebaseDatabase.getReference("order").push();
                        database.setValue(pushOrderToDriver);
                    }
                }
            });



        }
    }


}
