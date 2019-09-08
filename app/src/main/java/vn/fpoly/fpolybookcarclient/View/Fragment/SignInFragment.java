package vn.fpoly.fpolybookcarclient.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import vn.fpoly.fpolybookcarclient.Presenter.PresenterClient;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.View.Activity.ClientActivity;
import vn.fpoly.fpolybookcarclient.View.Interface.ViewClient;


public class SignInFragment extends Fragment implements View.OnClickListener  {


    TextInputEditText edtPhone,edtuser,edtpass,edtemail;
    EditText edtcoutry;
    Button btnsignup;
    boolean REQUES_CODE_SMS = false;
    String mVerificationId;
    FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        initView(view);
        edtcoutry.setFocusable(false);
        mAuth =  FirebaseAuth.getInstance();
        btnsignup.setOnClickListener(this);
//        btnCodeSMS.setOnClickListener(this);
        return view;
    }

    private void initView(View view) {
        edtPhone    = view.findViewById(R.id.edtphone);
        edtcoutry   = view.findViewById(R.id.edtcode);
        edtuser     = view.findViewById(R.id.edtusernamesignup);
        edtpass     = view.findViewById(R.id.edtpasssignup);
        edtemail    = view.findViewById(R.id.edtemaisignup);
        btnsignup   = view.findViewById(R.id.btnSigup);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSigup:
                initEventPhone();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                VerifyPhoneFragment verifyPhoneFragment = new VerifyPhoneFragment();
                fragmentTransaction.replace(R.id.frame_client,verifyPhoneFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
//            case R.id.btnCodeSMS:
//                eventCodeSMS();

//            break;
//            case R.id.count:
//                Log.d("nnnnn", edtPhone.getText().toString());
//                if(edtPhone.getText().toString() != null){
//                    sentCodeSMS(edtPhone.getText().toString());
//                }
//                break;
//        }

        }
    }
//    private void mResendCode(){
//        new CountDownTimer(30000,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                txtCount.setText(String.valueOf(millisUntilFinished/1000));
//            }
//            @Override
//            public void onFinish() {
//                txtCount.setText("Gửi lại");
//            }
//        }.start();
//    }
    private void sentCodeSMS(String phone){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+84"+phone,
                60,
                TimeUnit.SECONDS,
                 getActivity(),
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);

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
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void initEventPhone(){
        sentCodeSMS(edtPhone.getText().toString());

//        edtCodeOTP.setVisibility(View.VISIBLE);
//        btnClick.setVisibility(View.GONE);
//        btnCodeSMS.setVisibility(View.VISIBLE);
    }
//    private void eventCodeSMS(){
//        if(REQUES_CODE_SMS){
//            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId,edtCodeOTP.getText().toString());
//            signInWithPhoneAuthCredential(phoneAuthCredential);
//        }
//    }

}