package vn.fpoly.fpolybookcarclient.View.Interface;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.Driver;

public interface ViewGoogleMap {
   void drawPolyline();
   void showDetailDistance(int distance, int time,int price);

   void loadDriverCarList();
   void loadDriverMotoList();
   void getDriverNear(Driver driverNear);
   void getDriverNearFailed();
}
