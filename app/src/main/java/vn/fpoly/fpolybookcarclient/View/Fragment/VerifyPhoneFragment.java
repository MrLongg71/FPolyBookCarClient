package vn.fpoly.fpolybookcarclient.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.developer.kalert.KAlertDialog;

import es.dmoral.toasty.Toasty;
import vn.fpoly.fpolybookcarclient.Library.Dialog;
import vn.fpoly.fpolybookcarclient.Presenter.PresenterLogin;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.View.Activity.HomeActivity;
import vn.fpoly.fpolybookcarclient.View.Interface.ViewLogin;

public class VerifyPhoneFragment extends Fragment implements ViewLogin, View.OnClickListener {
    EditText edtphone1,edtphone2,edtphone3,edtphone4,edtphone5,edtphone6;
    Button btnVery;
    TextView txtPhone,txtResend;
    String phone = "";
    PresenterLogin presenterLogin;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verifyphone, container, false);
        initView(view);
        btnVery.setOnClickListener(this);
        txtResend.setOnClickListener(this);
        return view;
    }

    private boolean checkvalid(){
        String code1 = edtphone1.getText().toString().trim();
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

}
