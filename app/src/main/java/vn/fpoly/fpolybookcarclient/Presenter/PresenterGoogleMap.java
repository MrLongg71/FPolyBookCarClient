package vn.fpoly.fpolybookcarclient.Presenter;

import android.app.Activity;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.Model.ModelGoogleMap;
import vn.fpoly.fpolybookcarclient.Model.ObjectClass.Driver;
import vn.fpoly.fpolybookcarclient.View.Interface.ViewGoogleMap;

public class PresenterGoogleMap implements IPPresenterGoogleMap{
    ViewGoogleMap viewGoogleMap;
    ModelGoogleMap modelGoogleMap;
    List<Driver> driverList = new ArrayList<>();


    public  PresenterGoogleMap(ViewGoogleMap viewGoogleMap){
        this.viewGoogleMap = viewGoogleMap;
        modelGoogleMap = new ModelGoogleMap();

    }


    @Override
    public void getPolyline(Activity activity, GoogleMap googleMap, LatLng locationGo,LatLng locationCome) {
        modelGoogleMap.dowlodPolylineList(activity,googleMap,locationGo,locationCome,this);


    }

    @Override
    public void getDetailDistance(int distance, int time,int price) {
        if(distance > 10){
            price = 10000*distance;
        }
        price = 5000 * distance;

        viewGoogleMap.showDetailDistance(distance,time,price);

    }

    @Override
    public void getDriverList(Activity activity, LatLng locationGo) {

       modelGoogleMap.dowloadDriverCarList(activity,locationGo,this);
    }

    @Override
    public void distanceDriverNear(Driver driver) {

        driverList.add(driver);

        if(driverList.size() >0){
            if(driver.isStatus() && !driver.isWorking()){
                Driver driverProvisional = driverList.get(0);
                for(Driver driverValue : driverList){
                    Log.d("tets" , driverProvisional.getDistance() + " - " + driverValue.getDistance());
                    if(driverProvisional.getDistance() > driverValue.getDistance()){
                        driverProvisional  = driverValue;
                    }
                }
                viewGoogleMap.getDriverNear(driverProvisional);
            }else {
                viewGoogleMap.getDriverNearFailed();
            }
        }



    }


}
