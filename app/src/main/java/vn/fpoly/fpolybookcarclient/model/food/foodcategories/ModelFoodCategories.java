package vn.fpoly.fpolybookcarclient.model.food.foodcategories;

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

    public void dowloadListFoodCategories(final PresenterFoodCategories presenterFoodCategories) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataFoodCategories = dataSnapshot.child("Explore Categories");
                for (DataSnapshot valueFoodCategories : dataFoodCategories.getChildren()) {
                    FoodCategories foodCategories = valueFoodCategories.getValue(FoodCategories.class);
                    foodCategories.setKey(valueFoodCategories.getKey());
                    List<String> arrImage = new ArrayList<>();
                    DataSnapshot dataSnapshot1 = dataSnapshot.child("ImageExploreCategories").child(foodCategories.getKey());
                    for (DataSnapshot valueImage : dataSnapshot1.getChildren() ){
                        arrImage.add(valueImage.getValue(String.class));
                    }
                    foodCategories.setArrImage(arrImage);
                    presenterFoodCategories.resultGetFoodCategories(foodCategories);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }
}
