package vn.fpoly.fpolybookcarclient.model.foodbanner;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodBanner;
import vn.fpoly.fpolybookcarclient.presenter.foodbanner.PresenterFoodBanner;

public class ModelFoodBanner {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void dowloadListFood(final PresenterFoodBanner presenterFoodBanner) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNewsFood = dataSnapshot.child("NewsFood");
                for (DataSnapshot valueFood : dataNewsFood.getChildren()) {
                    FoodBanner foodBanner = valueFood.getValue(FoodBanner.class);
                    foodBanner.setKey(valueFood.getKey());

                    List<String> arrImage = new ArrayList<>();
                    DataSnapshot dataImage = dataSnapshot.child("ImageNewsFood").child(foodBanner.getKey());
                    for (DataSnapshot valueImage : dataImage.getChildren()) {
                        arrImage.add(valueImage.getValue(String.class));
                    }
                    foodBanner.setArrImage(arrImage);
                    presenterFoodBanner.resultGetListFood(foodBanner);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(eventListener);
    }
}
