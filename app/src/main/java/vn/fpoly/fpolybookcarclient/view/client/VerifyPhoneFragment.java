package vn.fpoly.fpolybookcarclient.view.client;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.developer.kalert.KAlertDialog;
import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import vn.fpoly.fpolybookcarclient.library.Dialog;
import vn.fpoly.fpolybookcarclient.presenter.client.PresenterLogin;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.activity.HomeActivity;

public class VerifyPhoneFragment extends Fragment implements ViewLogin, View.OnClickListener {

    private EditText edtphone1, edtphone2, edtphone3, edtphone4, edtphone5, edtphone6;
    private Button btnVery;
    private TextView txtPhone, txtResend;
    private String phone = "";
    private SmsVerifyCatcher smsVerifyCatcher;
    private PresenterLogin presenterLogin;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verifyphone, container, false);
        initView(view);
        btnVery.setOnClickListener(this);
        txtResend.setOnClickListener(this);
        autoMoveNext();
        SmsVeri();


        return view;
    }
    private boolean checkvalid(){
        final String code1 = edtphone1.getText().toString().trim();
        String code2 = edtphone2.getText().toString().trim();
        String code3 = edtphone3.getText().toString().trim();
        String code4 = edtphone4.getText().toString().trim();
        String code5 = edtphone5.getText().toString().trim();
        String code6 = edtphone6.getText().toString().trim();
        if(code1.length() ==0 || code2.length() == 0 || code3 .length() == 0 || code4.length()==0){
            Toasty.error(getActivity(),getString(R.string.checkedtcodevery),Toasty.LENGTH_SHORT).show();
            return false;
        }else {

            String sverify = code1+code2+code3+code4+code5+code6;
            checkVerify(phone,sverify);

            KAlertDialog pDialog = new KAlertDialog(getActivity(), KAlertDialog.SUCCESS_TYPE);
            pDialog .setTitleText(getString(R.string.success));
            pDialog .setContentText(getString(R.string.activateaccout));
            pDialog .show();

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LoginFragment loginFragment = new LoginFragment();
            fragmentTransaction.replace(R.id.frame_client,loginFragment);
            getActivity().getSupportFragmentManager().popBackStack();
            fragmentTransaction.commit();

        }

        return true;
    }

    private void checkVerify(String phone, String verify) {
        presenterLogin.doLoginPhone(phone,verify,getActivity());

    }

    @Override
    public void onSuccess() {
        Dialog.Success(getActivity());
        getActivity().finish();
        startActivity(new Intent(getActivity(), HomeActivity.class));
    }

    @Override
    public void onFailed() {
        Dialog.Error(getActivity());
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtresendcode:
                if(txtResend.getText().equals(getString(R.string.resendcode)) && phone !=null){
                    presenterLogin.doSendSMS(phone,txtResend,getActivity());
                }else {
                    Toasty.error(getActivity(), "Có lỗi xảy ra");
                    getActivity().finish();
                }
                break;
            case R.id.btnvery:
                checkvalid();
                break;
        }
    }
    private void initView(View view){
        presenterLogin  = new PresenterLogin(this);
        edtphone1       = view.findViewById(R.id.edtnumber1);
        edtphone2       = view.findViewById(R.id.edtnumber2);
        edtphone3       = view.findViewById(R.id.edtnumber3);
        edtphone4       = view.findViewById(R.id.edtnumber4);
        edtphone5       = view.findViewById(R.id.edtnumber5);
        edtphone6       = view.findViewById(R.id.edtnumber6);
        txtPhone        = view.findViewById(R.id.txtPhone);
        txtResend       = view.findViewById(R.id.txtresendcode);
        btnVery         = view.findViewById(R.id.btnvery);
        phone           = getArguments().getString("phone");
        txtPhone.setText(getString(R.string.messotp) +" "+ phone);
        presenterLogin.doSendSMS(phone,txtResend,getActivity());
    }

    private void SmsVeri() {
     smsVerifyCatcher = new SmsVerifyCatcher(getActivity(), new OnSmsCatchListener<String>() {
            @Override
            public void onSmsCatch(String message) {
                String code = parsCode(message);
                edtphone1.setText(code);
                edtphone2.setText(code);
                edtphone3.setText(code);
                edtphone4.setText(code);
                edtphone5.setText(code);
                edtphone6.setText(code);
            }
        });
        smsVerifyCatcher.setPhoneNumberFilter("phone");
    }
    private String parsCode(String mess){
        Pattern pattern = Pattern.compile("\\\\b\\\\d{6}\\\\b");
        Matcher matcher = pattern.matcher(mess);
        String code = "";
        while (matcher.find()){
            code = matcher.group(0);
        }
        return code;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    private void autoMoveNext(){
        edtphone1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edtphone1.getText().toString().length() >=1){
                    edtphone2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtphone2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edtphone2.getText().toString().length() >=1){
                    edtphone3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtphone3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edtphone3.getText().toString().length() >=1){
                    edtphone4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtphone4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edtphone4.getText().toString().length() >=1){
                    edtphone5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtphone5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edtphone5.getText().toString().length() >=1){
                    edtphone6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
