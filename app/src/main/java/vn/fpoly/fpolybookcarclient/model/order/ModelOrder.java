package vn.fpoly.fpolybookcarclient.model.order;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;
import vn.fpoly.fpolybookcarclient.presenter.order.PresenterOrder;

public class ModelOrder {
    private DatabaseReference dataRoot = FirebaseDatabase.getInstance().getReference();
    public void dowloadOrderFoodReview(final String idOrder, final String child, final PresenterOrder presenterOrder){
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("LongKhoa", "onDataChange: " + idOrder);
                DataSnapshot dataOrderFood = dataSnapshot.child(child).child(auth.getCurrentUser().getUid()).child(idOrder);
                OrderFood orderFood = dataOrderFood.getValue(OrderFood.class);
                DataSnapshot valueDriver = dataSnapshot.child("Driver").child("Car").child(orderFood.getKeyDriver());
                vn.fpoly.fpolybookcarclient.model.objectClass.Driver driver = valueDriver.getValue(Driver.class);

                presenterOrder.resultDowloadOrderFood(true,orderFood,driver);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenterOrder.resultDowloadOrderFood(false,null,null);
            }
        };
        dataRoot.addListenerForSingleValueEvent(valueEventListener);
    }
    public void dowloadOrderCarReview(final String idOrder, final String child, final PresenterOrder presenterOrder){
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataOrderCar = dataSnapshot.child(child).child(auth.getCurrentUser().getUid()).child(idOrder);
                OderCar oderCar = dataOrderCar.getValue(OderCar.class);
                DataSnapshot valueDriver = dataSnapshot.child("Driver").child("Car").child(oderCar.getKeydriver());
                vn.fpoly.fpolybookcarclient.model.objectClass.Driver driver = valueDriver.getValue(vn.fpoly.fpolybookcarclient.model.objectClass.Driver.class);

                presenterOrder.resultDowloadOrderCar(true,oderCar,driver);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenterOrder.resultDowloadOrderCar(false,null,null);
            }
        };
        dataRoot.addListenerForSingleValueEvent(valueEventListener);
    }
    public void initSetRatingDriver(final String child, double rate, final String idDriver, String idOrder , final PresenterOrder presenterOrder){
        dataRoot.child(child).child(idDriver).child(idOrder).child("rate").setValue(rate).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                totalRateDriver(child,idDriver);
                presenterOrder.resultSetRate(true);
            }
        });
    }
    private void totalRateDriver(final String child, final String idDriver){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               DataSnapshot dataDriver = dataSnapshot.child(child).child(idDriver);
               double total = 0;
               for(DataSnapshot value : dataDriver.getChildren()){
                   total += value.child("rate").getValue(Double.class);
               }
               dataRoot.child("Driver").child("Car").child(idDriver).child("rate").setValue(total);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dataRoot.addListenerForSingleValueEvent(valueEventListener);
    }
}
