package vn.fpoly.fpolybookcarclient.presenter.maps;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;

public interface IPPresenterGoogleMap {
    void getPolyline(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome,boolean isBookCar);
    void getDetailDistance(int distance, int time,double price,boolean isBookCar);

    void getDriverList(Activity activity, LatLng locationGo, boolean isbook);
    void distanceDriverNear(ArrayList<Driver> driverList, boolean isbook);
    void pushOrderToDriver(Driver driver,LatLng locationGo,LatLng locationCome,String placeNameGo,String placeNameCome);

}
