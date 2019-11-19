package vn.fpoly.fpolybookcarclient.model.breakfast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.BreakFast;
import vn.fpoly.fpolybookcarclient.presenter.breakfast.PresenterBreakFast;

public class ModelBreakFast  {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public void dowloadListBreakFast(final PresenterBreakFast presenterBreakFast){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNewsBreakFast = dataSnapshot.child("NewsBreakFast");
                for (DataSnapshot valuteNews : dataNewsBreakFast.getChildren()){
                    BreakFast breakFast = valuteNews.getValue(BreakFast.class);
                    breakFast.setKey(valuteNews.getKey());
                    List<String>arrBreakFast = new ArrayList<>();
                    DataSnapshot dataSnapshot1 = dataSnapshot.child("ImageNewsBreakFast").child(breakFast.getKey());
                    for (DataSnapshot valueImage : dataSnapshot1.getChildren()){
                        arrBreakFast.add(valueImage.getValue(String.class));
                    }
                    breakFast.setArrImage(arrBreakFast);
                    presenterBreakFast.resualGetBreakFast(breakFast);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }
}
