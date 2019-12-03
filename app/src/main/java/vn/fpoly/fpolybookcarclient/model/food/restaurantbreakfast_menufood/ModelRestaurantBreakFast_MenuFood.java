package vn.fpoly.fpolybookcarclient.model.food.restaurantbreakfast_menufood;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.BreakFast_MenuFood;
import vn.fpoly.fpolybookcarclient.presenter.food.breakfast.PresenterBreakFast;

public class ModelRestaurantBreakFast_MenuFood {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ArrayList<BreakFast_MenuFood>arrBreakFast = new ArrayList<>();
    private ArrayList<BreakFast_MenuFood>arrMenuFood = new ArrayList<>();

    public void dowloadListBreakFast(final PresenterBreakFast presenterBreakFast){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNewsBreakFast = dataSnapshot.child("NewsBreakFast");
                DataSnapshot dataNews = dataSnapshot.child("NewsPopularFood");

                for (DataSnapshot valuteNewsBreakFast : dataNewsBreakFast.getChildren()){
                    BreakFast_MenuFood breakFastMenuFood = valuteNewsBreakFast.getValue(BreakFast_MenuFood.class);
                    arrBreakFast.add(breakFastMenuFood);
                }
                for (DataSnapshot valueNewsMenuFood : dataNews.getChildren()){

                    BreakFast_MenuFood popularFood = valueNewsMenuFood.getValue(BreakFast_MenuFood.class);
                    arrMenuFood.add(popularFood);

                }
                presenterBreakFast.resualGetBreakFast(arrBreakFast,arrMenuFood);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }
}
