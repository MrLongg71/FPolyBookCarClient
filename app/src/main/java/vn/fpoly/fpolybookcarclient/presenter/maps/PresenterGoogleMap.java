
package vn.fpoly.fpolybookcarclient.presenter.maps;
import android.app.Activity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.maps.ModelGoogleMap;
import vn.fpoly.fpolybookcarclient.model.objectClass.BillFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.PushOrderToDriver;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.view.maps.ViewGoogleMap;

public class PresenterGoogleMap implements IPPresenterGoogleMap {
    ViewGoogleMap viewGoogleMap;
    ModelGoogleMap modelGoogleMap;
    Calendar calendar = Calendar.getInstance();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    double pricee = 0;
    int distancee = 0;

    public PresenterGoogleMap(ViewGoogleMap viewGoogleMap) {
        this.viewGoogleMap = viewGoogleMap;
        modelGoogleMap = new ModelGoogleMap();

    }


    @Override
    public void getPolyline(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome) {
        modelGoogleMap.downloadPolylineList(activity, googleMap, locationGo, locationCome, this);

    }

    @Override
    public void getDetailDistance(int distance, int time, double price) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh");
        int hourCurrent = Integer.parseInt(simpleDateFormat.format(calendar.getTime()));

        if(hourCurrent > 18){
            price = 15 * distance;
        }else if (distance > 10) {
            price = 10 * distance;
        }else {
            price = 5 * distance;
        }
        pricee = price;
        distancee = distance;
        viewGoogleMap.showDetailDistance(distance, time, price);
    }

    @Override
    public void getDriverList(Activity activity, LatLng locationGo, boolean isbook) {
        modelGoogleMap.dowloadDriverCarList(activity, locationGo, isbook,this);
    }

    @Override
    public void distanceDriverNear(ArrayList<Driver> driverList, boolean isbook) {
        Collections.sort(driverList);
        if(driverList.size() >0){
            viewGoogleMap.getDriverNear(driverList.get(0),isbook);
        }else {
            viewGoogleMap.getDriverNearFailed();
        }
    }








    @Override
    public void pushOrderToDriver(Driver driver, LatLng locationGo, LatLng locationCome, String placeNameGo, String placeNameCome) {
        if (driver != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            String date = simpleDateFormat.format(calendar.getTime());

            String keyOrder = database.push().getKey();
            final PushOrderToDriver pushOrderToDriver = new PushOrderToDriver(keyOrder,driver.getKeydriver());

            OderCar oderCar = new OderCar(keyOrder, firebaseAuth.getCurrentUser().getUid(), driver.getKeydriver()
                    , placeNameGo, placeNameCome, date, locationGo.latitude, locationGo.longitude, locationCome.latitude
                    , locationCome.longitude, pricee, driver.getRate(), distancee, false, false);



            modelGoogleMap.initPushNotification(oderCar, pushOrderToDriver);


        }
    }


    public void pushOrderFoodToDriver(Driver driver, ArrayList<BillFood> billFoodArrayList, LatLng locationClient, Restaurant restaurant, String placeNameGo, String placeNameCome,double price) {
        if (driver != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            String date = simpleDateFormat.format(calendar.getTime());

            String keyOrder = database.push().getKey();
            final PushOrderToDriver pushOrderToDriver = new PushOrderToDriver(keyOrder,driver.getKeydriver());


            OrderFood orderFood = new OrderFood(keyOrder,restaurant.getKey(),driver.getKeydriver(),FirebaseAuth.getInstance().getCurrentUser().getUid(),"",date,locationClient.latitude,locationClient.longitude,price,5,distancee,true,false);

            modelGoogleMap.initPushNotificationBookFood(orderFood, pushOrderToDriver,billFoodArrayList);


        }
    }
}