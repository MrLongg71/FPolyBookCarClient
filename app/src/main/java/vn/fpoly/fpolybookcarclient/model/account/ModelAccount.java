package vn.fpoly.fpolybookcarclient.model.account;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import vn.fpoly.fpolybookcarclient.model.objectClass.Client;
import vn.fpoly.fpolybookcarclient.presenter.account.PresenterAccount;

public class ModelAccount {
    public void dowloadInfoAccoint(final PresenterAccount presenterAccount){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final DatabaseReference dataAccout = FirebaseDatabase.getInstance().getReference().child("Client").child(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Client client = dataSnapshot.getValue(Client.class);
                presenterAccount.resultGetInfoAccount(client);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dataAccout.addValueEventListener(valueEventListener);
    }
    public void updateInfo(Client client, final PresenterAccount presenterAccount){
        final DatabaseReference dataAccout = FirebaseDatabase.getInstance().getReference().child("Client").child(Objects.requireNonNull(client.getKey()));
        dataAccout.setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    presenterAccount.resultUpdate(true);
                }else {
                    presenterAccount.resultUpdate(false);
                }
            }
        });

    }
}
