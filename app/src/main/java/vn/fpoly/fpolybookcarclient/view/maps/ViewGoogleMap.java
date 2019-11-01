package vn.fpoly.fpolybookcarclient.view.maps;

import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;

public interface ViewGoogleMap {
   void drawPolyline();
   void showDetailDistance(int distance, int time,int price);

   void loadDriverCarList();
   void loadDriverMotoList();
   void getDriverNear(Driver driverNear);
   void getDriverNearFailed();
}
