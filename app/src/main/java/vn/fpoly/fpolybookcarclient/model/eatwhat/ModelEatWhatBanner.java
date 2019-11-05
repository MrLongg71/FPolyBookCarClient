package vn.fpoly.fpolybookcarclient.model.eatwhat;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.HintFood;
import vn.fpoly.fpolybookcarclient.presenter.eatwhat.PresenterEatWhat;
import vn.fpoly.fpolybookcarclient.presenter.foodbanner.PresenterFoodBanner;

public class ModelEatWhatBanner {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public  void dowloadListEatWhat(final PresenterEatWhat presenterEatWhat){
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNewsEatWhat = dataSnapshot.child("NewsEatWhat");
                for (DataSnapshot valueNews : dataNewsEatWhat.getChildren()){
                    HintFood  hintFood = valueNews.getValue(HintFood.class);
                    hintFood.setKey(valueNews.getKey());
                    List<String >arrImage = new ArrayList<>();
                    DataSnapshot dataImage = dataSnapshot.child("ImageNewsEatWhat").child(hintFood.getKey());
                    for (DataSnapshot valuImage : dataImage.getChildren() ){
                        arrImage.add(valuImage.getValue(String.class));
                    }
                    hintFood.setArrImage(arrImage);
                    presenterEatWhat.resultGetListEat(hintFood);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(eventListener);
    }
}
