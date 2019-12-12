package vn.fpoly.fpolybookcarclient.model.history;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;
import vn.fpoly.fpolybookcarclient.presenter.history.PresenterHistoryOrder;

public class ModelHistoryOrder {
    public void dowloadListOrder(final PresenterHistoryOrder presenterHistoryOrder) {
        final DatabaseReference dataOrder = FirebaseDatabase.getInstance().getReference();
        final FirebaseAuth user = FirebaseAuth.getInstance();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataOrderCar = dataSnapshot.child("OrderCarClient").child(user.getCurrentUser().getUid());
                ArrayList<OderCar> oderCarArrayList = new ArrayList<>();
                ArrayList<OrderFood> orderFoodArrayList = new ArrayList<>();

                if (dataOrderCar.exists()) {
                    for (DataSnapshot valueOrderCar : dataOrderCar.getChildren()) {
                        OderCar oderCar = valueOrderCar.getValue(OderCar.class);
                        oderCarArrayList.add(oderCar);


                        DataSnapshot dataOrderFood = dataSnapshot.child("OrderFoodClient").child(user.getCurrentUser().getUid());

                        if (dataOrderFood.exists()) {
                            for (DataSnapshot valueOrderFood : dataOrderFood.getChildren()) {
                                OrderFood orderFood = valueOrderFood.getValue(OrderFood.class);
                                orderFoodArrayList.add(orderFood);
                            }
                        }
                    }
                    presenterHistoryOrder.resultGetListOrder(oderCarArrayList,orderFoodArrayList,true);

                }else {
                    presenterHistoryOrder.resultGetListOrder(oderCarArrayList,orderFoodArrayList,false);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dataOrder.addValueEventListener(valueEventListener);
    }

}
