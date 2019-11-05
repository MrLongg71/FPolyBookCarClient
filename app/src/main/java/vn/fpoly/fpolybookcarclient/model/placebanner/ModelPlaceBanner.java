package vn.fpoly.fpolybookcarclient.model.placebanner;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.PlaceBanner;
import vn.fpoly.fpolybookcarclient.presenter.placebanner.PresenterPlaceBanner;

public class ModelPlaceBanner {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void dowloadListPlace(final PresenterPlaceBanner presenterPlaceBanner) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNewsPlace = dataSnapshot.child("NewsPlace");
                for (DataSnapshot valueNewsPlace : dataNewsPlace.getChildren()) {
                    PlaceBanner placeBanner = valueNewsPlace.getValue(PlaceBanner.class);
                    placeBanner.setKey(valueNewsPlace.getKey());

                    DataSnapshot dataImage = dataSnapshot.child("ImageNewsPlace").child(placeBanner.getKey());
                    List<String >arrImage = new ArrayList<>();

                    for (DataSnapshot valueImage : dataImage.getChildren()){
                        arrImage.add(valueImage.getValue(String.class));
                    }
                    placeBanner.setArrImage(arrImage);
                    presenterPlaceBanner.resultGetListPlace(placeBanner);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(eventListener);
    }
}
