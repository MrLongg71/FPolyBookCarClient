package vn.fpoly.fpolybookcarclient.model.popularfood;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.PopularFood;
import vn.fpoly.fpolybookcarclient.presenter.popularfood.PresenterPopularFood;

public class ModelPoppularFood {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public void dowloadListPopularFood(final PresenterPopularFood presenterPopularFood){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNews = dataSnapshot.child("NewsPopularFood");
                for (DataSnapshot valueNews : dataNews.getChildren()){
                    PopularFood popularFood = valueNews.getValue(PopularFood.class);
                    popularFood.setKey(valueNews.getKey());
                    List<String>arrImage = new ArrayList<>();
                    DataSnapshot dataSnapshot1 = dataSnapshot.child("ImageNewsPopularFood").child(popularFood.getKey());
                    for (DataSnapshot valueImage : dataSnapshot1.getChildren()){
                        arrImage.add(valueImage.getValue(String.class));
                    }
                    popularFood.setArrImage(arrImage);
                    presenterPopularFood.resualGetListPopularFood(popularFood);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }
}
