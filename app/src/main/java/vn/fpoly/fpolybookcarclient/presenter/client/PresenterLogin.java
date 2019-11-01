package vn.fpoly.fpolybookcarclient.presenter.client;

import android.app.Activity;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import vn.fpoly.fpolybookcarclient.model.client.ModelClient;
import vn.fpoly.fpolybookcarclient.model.objectClass.Client;
import vn.fpoly.fpolybookcarclient.R;

import vn.fpoly.fpolybookcarclient.view.client.ViewLogin;

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
    public void doSendSMS(String phone, TextView txtResend, Activity activity) {
        sentCodeSMS(phone,txtResend,activity);
    }



    @Override
    public void doLoginPhone(String phone, String verify, Activity activity) {
        if(REQUES_CODE_SMS){
            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId,verify);
            signInWithPhoneAuthCredential(phoneAuthCredential,activity);
        }
    }

    @Override
    public void doRegisterEmail(Client client) {
        ModelClient modelClient = new ModelClient();
        modelClient.addClientDatabase(client);
    }
    @Override
    public void sentCodeSMS(String phone, final TextView txtResend, final Activity activity) {

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
                        REQUES_CODE_SMS = false;
                        if (e instanceof FirebaseTooManyRequestsException) {
                            Toast.makeText(activity, "Bạn đã xác nhận quá số lần cho phép. Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {
                        REQUES_CODE_SMS = true;
                        mVerificationId = verificationId;
                        Log.d("test" , verificationId+"");
                        mResendCode(txtResend, activity);
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
                            String key = task.getResult().getUser().getUid();
                            Client client = new Client(key,"", task.getResult().getUser().getPhoneNumber(),"" );
                            ModelClient modelClient = new ModelClient();
                            modelClient.addClientDatabase(client);
                        }else {
                            viewLogin.onFailed();
                        }
                    }
                });
    }
    public void mResendCode(final TextView txtResend, final Activity activity){
        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtResend.setText(String.valueOf(millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {
                txtResend.setText(activity.getString(R.string.resendcode));
            }
        }.start();
    }


}
