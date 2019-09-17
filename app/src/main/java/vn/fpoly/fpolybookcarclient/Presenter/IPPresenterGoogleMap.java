package vn.fpoly.fpolybookcarclient.Presenter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.Driver;

public interface IPPresenterGoogleMap {
    void getPolyline(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome);
    void getDetailDistance(int distance, int time,int price);

    void getDriverList(Activity activity, LatLng locationGo);
    void distanceDriverNear(Driver driver);
}
