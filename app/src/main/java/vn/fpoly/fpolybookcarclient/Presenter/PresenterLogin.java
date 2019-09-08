package vn.fpoly.fpolybookcarclient.Presenter;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.Client;
import vn.fpoly.fpolybookcarclient.View.Fragment.SignInFragment;
import vn.fpoly.fpolybookcarclient.View.Interface.ViewLogin;

public class PresenterLogin implements IPPresenterLogin {

    ViewLogin viewLogin;
    boolean REQUES_CODE_SMS = false;
    String mVerificationId;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    public PresenterLogin(ViewLogin viewLogin){
        this.viewLogin = viewLogin;

    }


    @Override
    public void doLoginEmail(String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    viewLogin.onSuccess();
                }else {
                    viewLogin.onFailed();
                }
            }
        });

    }

    @Override
    public void doSendSMS(String phone,Activity activity) {
        sentCodeSMS(phone,activity);
    }

    @Override
    public void doLoginPhone(String phone, String verify, Activity activity) {
        if(REQUES_CODE_SMS){
            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId,verify);
            signInWithPhoneAuthCredential(phoneAuthCredential,activity);
        }
    }
    private void sentCodeSMS(String phone, final Activity activity) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+84" + phone,
                60,
                TimeUnit.SECONDS,
                activity,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential,activity);

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {
                        REQUES_CODE_SMS = true;
                        mVerificationId = verificationId;
//                        mResendCode();
                    }
                }
        );
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential, final Activity activity) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            viewLogin.onSuccess();
                        }
                    }
                });
    }


}
