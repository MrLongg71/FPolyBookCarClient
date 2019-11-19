package vn.fpoly.fpolybookcarclient.model.foodsale;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodSale;
import vn.fpoly.fpolybookcarclient.presenter.foodsale.PresenterFoodSale;

public class ModelFoodSale {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void dowloadListNewsFood(final PresenterFoodSale presenterFoodSale){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNews = dataSnapshot.child("NewsFood");
                for (DataSnapshot valueNews : dataNews.getChildren()){
                    FoodSale foodSale = valueNews.getValue(FoodSale.class);
                    foodSale.setKey(valueNews.getKey());
                    List<String>arrImage = new ArrayList<>();
                    DataSnapshot dataSnapshot1 = dataSnapshot.child("ImageNewsFood").child(foodSale.getKey());
                    for (DataSnapshot valueImage : dataSnapshot1.getChildren()){
                        arrImage.add(valueImage.getValue(String.class));
                    }
                    foodSale.setArrImage(arrImage);
                    presenterFoodSale.resultGetListFoodSale(foodSale);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }

}
