package vn.fpoly.fpolybookcarclient.Model;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.SphericalUtil;
import com.logicbeanzs.uberpolylineanimation.MapAnimator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.Model.ObjectClass.Driver;
import vn.fpoly.fpolybookcarclient.Presenter.PresenterGoogleMap;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.Service.DownloadPolyLine;
import vn.fpoly.fpolybookcarclient.Service.ParserPolyline;
import vn.fpoly.fpolybookcarclient.View.Interface.ViewGoogleMap;

public class ModelGoogleMap {
    private FirebaseDatabase databaseDriver;
    private DatabaseReference databaseReference;

    public void dowlodPolylineList(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome,final PresenterGoogleMap presenterGoogleMap) {
        String LINK = Constans.LINK_GOOGLE_API_DRAW_POLYLINE + locationGo.latitude + Constans.Comma + locationGo.longitude + Constans.Destination + locationCome.latitude + Constans.Comma + locationCome.longitude + Constans.Language + activity.getString(R.string.google_map_api2);
        ParserPolyline parserPolyline = new ParserPolyline();
        DownloadPolyLine downloadPolyLine = new DownloadPolyLine();

        //open dowload
        downloadPolyLine.execute(LINK);

        try {
            String dataJSON = downloadPolyLine.get();
            List<LatLng> latLngList = parserPolyline.getListLocation(dataJSON);
            int disttance = parserPolyline.getDistance(dataJSON)/1000;
            int time = parserPolyline.getTime(dataJSON)/60;
            PolylineOptions polylineOptions = new PolylineOptions();
            for (LatLng latLng : latLngList) {
                polylineOptions.add(latLng);
            }
            Polyline polyline = googleMap.addPolyline(polylineOptions);
            polyline.setColor(Color.rgb(0, 191, 255));

//            MapAnimator.getInstance().setSecondaryLineColor();
            //MapAnimator.getInstance().animateRoute(googleMap, latLngList);

            presenterGoogleMap.getDetailDistance(disttance,time,0);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dowloadDriverCarList(final Activity activity, final LatLng locationGo, final PresenterGoogleMap presenterGoogleMap) {
        databaseDriver = FirebaseDatabase.getInstance();
        databaseReference = databaseDriver.getReference();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot nodeCar = dataSnapshot.child("Driver").child("Car");

                for (DataSnapshot valueDriverCar : nodeCar.getChildren()) {
                    Driver driver = valueDriverCar.getValue(Driver.class);
                    driver.setKeydriver(valueDriverCar.getKey());
                    LatLng locationDriver = new LatLng(driver.getLatitude(), driver.getLongitude());

                    String LINK = Constans.LINK_GOOGLE_API_DRAW_POLYLINE + locationDriver.latitude + Constans.Comma + locationDriver.longitude + Constans.Destination + locationGo.latitude + Constans.Comma + locationGo.longitude + Constans.Language + activity.getString(R.string.google_map_api2);
                    ParserPolyline parserPolyline = new ParserPolyline();
                    DownloadPolyLine downloadPolyLine = new DownloadPolyLine();
                    String dataJSON = null;
                    //open dowload
                    downloadPolyLine.execute(LINK);
                    try {
                        dataJSON = downloadPolyLine.get();
                        List<LatLng> latLngList = parserPolyline.getListLocation(dataJSON);
                        double disttance = parserPolyline.getDistance(dataJSON) / 1000;
                        double time = parserPolyline.getTime(dataJSON) / 60;
                        driver.setDistance(disttance);
                        driver.setTime(time);
                        presenterGoogleMap.distanceDriverNear(driver);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }


}
