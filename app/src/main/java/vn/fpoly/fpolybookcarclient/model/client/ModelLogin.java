package vn.fpoly.fpolybookcarclient.model.client;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vn.fpoly.fpolybookcarclient.presenter.client.PresenterLogin;

public class ModelLogin {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void initLogin(String email, String password, final PresenterLogin presenterLogin) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            DataSnapshot rule = dataSnapshot.child("Client").child(firebaseAuth.getCurrentUser().getUid());
                            if (rule.exists()) {
                                presenterLogin.resultSignIn(true, "");
                            }else {
                                presenterLogin.resultSignIn(false, "You != User!");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    databaseReference.addValueEventListener(valueEventListener);
                } else {
                    presenterLogin.resultSignIn(false, task.getException().getMessage());
                }
            }
        });
    }


}
