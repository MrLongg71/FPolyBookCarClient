package vn.fpoly.fpolybookcarclient.model.challengebanner;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.ChallengeBanner;
import vn.fpoly.fpolybookcarclient.presenter.challengebanner.PresenterChallengerBanner;

public class ModelChallengeBanner {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void dowloadListChallege(final PresenterChallengerBanner presenterChallengerBanner){
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataImage = dataSnapshot.child("ImageNewsChallege");
                for (DataSnapshot valueImage : dataImage.getChildren()){
                    ChallengeBanner challengeBanner = valueImage.getValue(ChallengeBanner.class);
                    challengeBanner.setKey(valueImage.getKey());
                    List<String>arrImage = new ArrayList<>();
                    DataSnapshot dataImage1 = dataSnapshot.child("ImageNewsChallege").child(challengeBanner.getKey());
                    for (DataSnapshot value: dataImage1.getChildren() ){
                        arrImage.add(value.getValue(String.class));
                    }
                    challengeBanner.setArrImage(arrImage);

                    presenterChallengerBanner.resultGetListChallenge(challengeBanner);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(eventListener);
    }
}
