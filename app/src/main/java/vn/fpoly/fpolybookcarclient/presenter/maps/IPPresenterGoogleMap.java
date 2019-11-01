package vn.fpoly.fpolybookcarclient.presenter.maps;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;

public interface IPPresenterGoogleMap {
    void getPolyline(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome);
    void getDetailDistance(int distance, int time,int price);

    void getDriverList(Activity activity, LatLng locationGo);
    void distanceDriverNear(Driver driver);
}
