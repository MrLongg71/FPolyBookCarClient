package vn.fpoly.fpolybookcarclient.model.food;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodPager;
import vn.fpoly.fpolybookcarclient.presenter.food.PresenterFood;

public class ModelFood {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void dowloadListFood(final PresenterFood presenterFood) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataImage = dataSnapshot.child("ImageFood");
                for (DataSnapshot valueImage : dataImage.getChildren()) {
                    FoodPager food = valueImage.getValue(FoodPager.class);
                    food.setKey(valueImage.getKey());

                    List<String> arrImage = new ArrayList<>();
                    DataSnapshot dataSnapshot1 = dataSnapshot.child("ImageFood").child(food.getKey());
                    for (DataSnapshot valuteImage1 : dataSnapshot1.getChildren()) {
                        arrImage.add(valuteImage1.getValue(String.class));

                    }
                    food.setArrImage(arrImage);
                    presenterFood.resultGetListFood(food);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);

    }
}
