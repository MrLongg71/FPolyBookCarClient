
package vn.fpoly.fpolybookcarclient.model.maps;

import android.app.Activity;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.BillFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.PushOrderToDriver;
import vn.fpoly.fpolybookcarclient.presenter.maps.PresenterGoogleMap;
import vn.fpoly.fpolybookcarclient.server.DownloadPolyLine;
import vn.fpoly.fpolybookcarclient.server.ParserPolyline;

public class ModelGoogleMap {


    private FirebaseDatabase databaseDriver = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = databaseDriver.getReference();
    private FirebaseDatabase firebaseDatabaseOrder = FirebaseDatabase.getInstance();
    private DatabaseReference databaseOrder = firebaseDatabaseOrder.getReference();

    //Sai chính tả nó báo xanh xanh ni thì nhớ fix
    public void downloadPolylineList(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome, final PresenterGoogleMap presenterGoogleMap) {
        String LINK = Constans.LINK_GOOGLE_API_DRAW_POLYLINE + locationGo.latitude + Constans.Comma + locationGo.longitude + Constans.Destination + locationCome.latitude + Constans.Comma + locationCome.longitude + Constans.Language + activity.getString(R.string.google_map_api2);
//        ParserPolyline parserPolyline = new ParserPolyline();
        DownloadPolyLine downloadPolyLine = new DownloadPolyLine();
        //open dowload
        downloadPolyLine.execute(LINK);

        try {
            String dataJSON = downloadPolyLine.get();
            List<LatLng> latLngList = ParserPolyline.getListLocation(dataJSON);
            int disttance = ParserPolyline.getDistance(dataJSON) / 1000;
            int time = ParserPolyline.getTime(dataJSON) / 60;
            PolylineOptions polylineOptions = new PolylineOptions();
            for (LatLng latLng : latLngList) {
                polylineOptions.add(latLng);
            }
            Polyline polyline = googleMap.addPolyline(polylineOptions);
            polyline.setColor(Color.rgb(0, 191, 255));

//            MapAnimator.getInstance().setSecondaryLineColor();
            //MapAnimator.getInstance().animateRoute(googleMap, latLngList);

            presenterGoogleMap.getDetailDistance(disttance, time, 0);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dowloadDriverCarList(final Activity activity, final LatLng locationGo, final boolean isbook, final PresenterGoogleMap presenterGoogleMap) {

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot nodeCar = dataSnapshot.child("Driver").child("Car");
                ArrayList<Driver> driverArrayList = new ArrayList<>();
                for (DataSnapshot valueDriverCar : nodeCar.getChildren()) {

                    Driver driver = valueDriverCar.getValue(Driver.class);
                    assert driver != null;
                    driver.setKeydriver(valueDriverCar.getKey());

                    LatLng locationDriver = new LatLng(driver.getLatitude(), driver.getLongitude());

                    String LINK = Constans.LINK_GOOGLE_API_DRAW_POLYLINE + locationDriver.latitude + Constans.Comma + locationDriver.longitude + Constans.Destination + locationGo.latitude + Constans.Comma + locationGo.longitude + Constans.Language + activity.getString(R.string.google_map_api2);
//                    ParserPolyline parserPolyline = new ParserPolyline();


                    DownloadPolyLine downloadPolyLine = new DownloadPolyLine();
                    String dataJSON;
                    //open dowload
                    downloadPolyLine.execute(LINK);
                    try {
                        dataJSON = downloadPolyLine.get();
//                        List<LatLng> latLngList = ParserPolyline.getListLocation(dataJSON);
                        double disttance = ParserPolyline.getDistance(dataJSON) / 1000;
                        double time = ParserPolyline.getTime(dataJSON) / 60;
                        driver.setDistance(disttance);
                        driver.setTime(time);
                        if(driver.isStatus() && !driver.isWorking() && driver.getDistance() < 10){
                            driverArrayList.add(driver);
                        }

                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                presenterGoogleMap.distanceDriverNear(driverArrayList,isbook);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    public void initPushNotification(final OderCar oderCar, final PushOrderToDriver pushOrderToDriver) {
        databaseOrder.child("Order").child(oderCar.getKeydriver()).child(oderCar.getKeyOrder()).setValue(oderCar).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
//                    //TODO
                databaseOrder.child("notification").push().setValue(pushOrderToDriver);

                }
            }
        });



    }
    public void initPushNotificationBookFood(final OrderFood orderFood, final PushOrderToDriver pushOrderToDriver,ArrayList<BillFood> billFoodArrayList) {

        String keyBillDetail = databaseOrder.push().getKey();
        orderFood.setKeyBillDetail(keyBillDetail);
        databaseOrder.child("OrderFood").child(orderFood.getKeyDriver()).child(orderFood.getKey()).setValue(orderFood).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
//                    //TODO
                    databaseOrder.child("notification").push().setValue(pushOrderToDriver);
                }
            }
        });
        for(BillFood billFood : billFoodArrayList){
            String keyBill = databaseOrder.push().getKey();
            billFood.setKey(keyBill);
            databaseOrder.child("BillFoodDetails").child(keyBillDetail).child(keyBill).setValue(billFood).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });
        }




    }
}


