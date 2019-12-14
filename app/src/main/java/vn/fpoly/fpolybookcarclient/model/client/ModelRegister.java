package vn.fpoly.fpolybookcarclient.model.client;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.model.objectClass.Client;
import vn.fpoly.fpolybookcarclient.presenter.client.PresenterRegister;

public class ModelRegister {
    boolean REQUES_CODE_SMS = false;
    String mVerificationId;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void createClientWithEmailAndPhone(final Client client, String password, final PresenterRegister presenterSignUp) {
        auth.createUserWithEmailAndPassword(client.getEmail(), password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                client.setKey(authResult.getUser().getUid());
                databaseReference.child(Constans.childClient).child(authResult.getUser().getUid()).setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            presenterSignUp.resultSignUp(true, "");
                        } else {
                            presenterSignUp.resultSignUp(false, task.getException().getMessage());
                        }

                    }
                });
            }
        });


    }


    public void doSendSMS(String phone, TextView txtResend, Activity activity, PresenterRegister presenterSignUp) {
        sentCodeSMS(phone, txtResend, activity, presenterSignUp);
    }

    public void sentCodeSMS(String phone, final TextView txtResend, final Activity activity, final PresenterRegister presenterSignUp) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+84" + phone,
                60,
                TimeUnit.SECONDS,
                activity,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential, activity, presenterSignUp);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        REQUES_CODE_SMS = false;
                        if (e instanceof FirebaseTooManyRequestsException) {
                            presenterSignUp.resultSendOTP(false, "spam");
                        }
                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {
                        REQUES_CODE_SMS = true;
                        mVerificationId = verificationId;
                        presenterSignUp.resultSendOTP(true,"");
                        mResendCode(txtResend, activity);
                    }
                }
        );
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential, final Activity activity, final PresenterRegister presenterSignUp) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            presenterSignUp.resultVerifyOTP(true, "");
                        } else {
                            presenterSignUp.resultVerifyOTP(false, task.getException().getMessage());
                        }
                    }
                });
    }

    public void mResendCode(final TextView txtResend, final Activity activity) {
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtResend.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                txtResend.setText("Didn't get OTP ? Resend?");
            }
        }.start();
    }

    public void initCheckVerifyOTP(String verify, Activity activity, PresenterRegister presenterSignUp) {
        if (REQUES_CODE_SMS) {
            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId, verify);
            signInWithPhoneAuthCredential(phoneAuthCredential, activity, presenterSignUp);
        }
    }


}
