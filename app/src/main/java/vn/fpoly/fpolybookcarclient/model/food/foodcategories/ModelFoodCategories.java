package vn.fpoly.fpolybookcarclient.model.food.foodcategories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodCategories;
import vn.fpoly.fpolybookcarclient.presenter.food.foodcategories.PresenterFoodCategories;

public class ModelFoodCategories {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ArrayList<FoodCategories>arrFoodCategorie = new ArrayList<>();
    private ArrayList<FoodCategories>arrMenuFood = new ArrayList<>();


    public void dowloadListFoodCategories(final PresenterFoodCategories presenterFoodCategories) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataFoodCategories = dataSnapshot.child("ExploreCategories");
                DataSnapshot dataFoodMenu = dataSnapshot.child("NewsPopularFood");

                for (DataSnapshot valueFoodCategories : dataFoodCategories.getChildren()) {
                    FoodCategories foodCategories = valueFoodCategories.getValue(FoodCategories.class);

                    arrFoodCategorie.add(foodCategories);

                }
                for (DataSnapshot valueMenFood : dataFoodMenu.getChildren()){
                    FoodCategories foodCategories = valueMenFood.getValue(FoodCategories.class);
                    arrMenuFood.add(foodCategories);
                }

                presenterFoodCategories.resultGetFoodCategories(arrFoodCategorie,arrMenuFood);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }
}
