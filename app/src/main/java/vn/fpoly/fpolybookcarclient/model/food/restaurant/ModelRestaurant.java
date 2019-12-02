package vn.fpoly.fpolybookcarclient.model.food.restaurant;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.presenter.food.foodsale.PresenterFoodSale;

public class ModelRestaurant {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void dowloadListNewsFood(final PresenterFoodSale presenterFoodSale){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNews = dataSnapshot.child("Restaurant");
                for (DataSnapshot valueNews : dataNews.getChildren()){
                    Restaurant restaurant = valueNews.getValue(Restaurant.class);
                    restaurant.setKey(valueNews.getKey());
                    List<String> arrImage = new ArrayList<>();
                    DataSnapshot dataSnapshot1 = dataSnapshot.child("ImageNewsFood").child(restaurant.getKey());
                    for (DataSnapshot valueImage : dataSnapshot1.getChildren()){
                        arrImage.add(valueImage.getValue(String.class));
                    }
                    restaurant.setArrImage(arrImage);
                    presenterFoodSale.resultGetListFoodSale(restaurant);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }

}
