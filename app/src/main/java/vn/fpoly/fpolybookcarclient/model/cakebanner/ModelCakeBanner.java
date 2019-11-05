package vn.fpoly.fpolybookcarclient.model.cakebanner;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.CakesBanner;
import vn.fpoly.fpolybookcarclient.presenter.cakebanner.PresenterCakeBanner;

public class ModelCakeBanner {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void dowloaLisCake(final PresenterCakeBanner presenterCakeBanner) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNewsCake = dataSnapshot.child("NewsCake");
                for ( DataSnapshot valueNewsCake : dataNewsCake.getChildren()){
                    CakesBanner cakesBanner = valueNewsCake.getValue(CakesBanner.class);
                    cakesBanner.setKey(valueNewsCake.getKey());
                    DataSnapshot dataImage = dataSnapshot.child("ImageNewsCake").child(cakesBanner.getKey());
                    List<String>arrImage = new ArrayList<>();
                    for (DataSnapshot valueImage : dataImage.getChildren()){
                        arrImage.add(valueImage.getValue(String.class));
                    }
                    cakesBanner.setArrImage(arrImage);
                    presenterCakeBanner.resultGetListCake(cakesBanner);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(eventListener);
    }

}
