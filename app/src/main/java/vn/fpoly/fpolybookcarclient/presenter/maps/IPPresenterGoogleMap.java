package vn.fpoly.fpolybookcarclient.presenter.maps;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;

public interface IPPresenterGoogleMap {
    void getPolyline(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome);
    void getDetailDistance(int distance, int time,double price);

    void getDriverList(Activity activity, LatLng locationGo);
    void distanceDriverNear(ArrayList<Driver> driverList);
    void pushOrderToDriver(Driver driver,LatLng locationGo,LatLng locationCome,String placeNameGo,String placeNameCome);

}
