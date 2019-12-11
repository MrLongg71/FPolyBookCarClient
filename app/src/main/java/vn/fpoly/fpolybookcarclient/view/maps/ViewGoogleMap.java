package vn.fpoly.fpolybookcarclient.view.maps;

import com.google.android.gms.maps.model.LatLng;

import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;

public interface ViewGoogleMap {
   void drawPolyline(LatLng locationGo,LatLng locationCome);
   void showDetailDistance(int distance, int time,double price);
   void loadDriverCarList();
   void loadDriverMotoList();
   void getDriverNear(Driver driverNear, boolean isbook);
   void getDriverNearFailed();
}
