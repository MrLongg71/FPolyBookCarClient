
package vn.fpoly.fpolybookcarclient.presenter.maps;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
    Calendar calendar = Calendar.getInstance();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    double pricee = 0;
    int distancee = 0;

    public PresenterGoogleMap(ViewGoogleMap viewGoogleMap) {
        this.viewGoogleMap = viewGoogleMap;
        modelGoogleMap = new ModelGoogleMap();

    }


    @Override
    public void getPolyline(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome) {
        modelGoogleMap.dowlodPolylineList(activity, googleMap, locationGo, locationCome, this);

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
    public void getDriverList(Activity activity, LatLng locationGo) {
        modelGoogleMap.dowloadDriverCarList(activity, locationGo, this);
    }

    @Override
    public void distanceDriverNear(final Driver driver) {
        //phần này e xử lí để trả ra driver gần nhất




//        if(driver.getDistance()  <10 && driver.isStatus() && !driver.isWorking()){
//            driverList.add(driver);
//        }

//        driverList.add(driver);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Collections.sort(driverList, new Comparator<Driver>() {
//                    @Override
//                    public int compare(Driver driver, Driver t1) {
//                        return (int) (driver.getDistance() - t1.getDistance());
//                    }
//                });
//
//            }
//        },30000);
//        for(Driver driver1 : driverList){
//            Log.d("kiemtradixeta", driver1.getEmail()+" - "+driver1.getDistance() +  " ");
//        }
//        Log.d("kiemtradixeta", "----");

//        Log.d("kiemra" , driverList.size() + "");
//        driverList.add(driver);
//        Log.d("kiemra" , driverList.size() + "");
//
//                 if (driverList.size() > 0) {
//                     if (driver.isStatus() && !driver.isWorking()) {
//                         Driver driverProvisional = driverList.get(0);
//                         for (Driver driverValue : driverList) {
//                             Log.d("tets", driverProvisional.getDistance() + " - " + driverValue.getDistance());
//                             if (driverProvisional.getDistance() > driverValue.getDistance()) {
//                                 driverProvisional = driverValue;
//                             }
//                         }
//
//                         viewGoogleMap.getDriverNear(driverProvisional);
//
//
//                     }
//
//                 }
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

}