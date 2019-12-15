package vn.fpoly.fpolybookcarclient.view.client;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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

import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import vn.fpoly.fpolybookcarclient.model.objectClass.Client;
import vn.fpoly.fpolybookcarclient.presenter.client.PresenterRegister;
import vn.fpoly.fpolybookcarclient.R;

public class VerifyPhoneFragment extends Fragment implements IViewRegister, View.OnClickListener {

    private EditText edtphone1, edtphone2, edtphone3, edtphone4, edtphone5, edtphone6;
    private Button btnVery;
    private TextView txtPhone, txtResend;
    private String phone = "";
    private String pass = "";
    private SmsVerifyCatcher smsVerifyCatcher;
    private PresenterRegister presenterSignUp;
    private Client client;
    private ProgressDialog progressDialog;

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

    private boolean checkvalid() {
        String code1 = edtphone1.getText().toString().trim();
        String code2 = edtphone2.getText().toString().trim();
        String code3 = edtphone3.getText().toString().trim();
        String code4 = edtphone4.getText().toString().trim();
        String code5 = edtphone5.getText().toString().trim();
        String code6 = edtphone6.getText().toString().trim();
        if (code1.length() == 0 || code2.length() == 0 || code3.length() == 0 || code4.length() == 0) {
            Toasty.error(getActivity(), getString(R.string.checkedtcodevery), Toasty.LENGTH_SHORT).show();
            return false;
        } else {
            progressDialog.show();
            String sverify = code1 + code2 + code3 + code4 + code5 + code6;
            checkVerify(phone, sverify);
            return true;
        }

    }


    private void checkVerify(String phone, String verify) {
        presenterSignUp.doVerifyOTP(verify, getActivity());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtresendcode:
                if (txtResend.getText().equals(getString(R.string.resendcode)) && phone != null) {
                    presenterSignUp.doSendVerifyOTP(phone, txtResend, getActivity());
                } else {
                    Toasty.error(getActivity(), "Có lỗi xảy ra");
                    getActivity().finish();
                }
                break;
            case R.id.btnvery:
                checkvalid();
                break;
        }
    }

    private void initView(View view) {
        presenterSignUp = new PresenterRegister(this);
        edtphone1 = view.findViewById(R.id.edtnumber1);
        edtphone2 = view.findViewById(R.id.edtnumber2);
        edtphone3 = view.findViewById(R.id.edtnumber3);
        edtphone4 = view.findViewById(R.id.edtnumber4);
        edtphone5 = view.findViewById(R.id.edtnumber5);
        edtphone6 = view.findViewById(R.id.edtnumber6);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtResend = view.findViewById(R.id.txtresendcode);
        btnVery = view.findViewById(R.id.btnvery);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
        
        
        client = getArguments().getParcelable("client");
        phone = client.getPhone();
        pass = getArguments().getString("pass");

        txtPhone.setText(getString(R.string.messotp) + " " + phone);
        presenterSignUp.doSendVerifyOTP(phone, txtResend, getActivity());
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

    private String parsCode(String mess) {
        Pattern pattern = Pattern.compile("\\\\b\\\\d{6}\\\\b");
        Matcher matcher = pattern.matcher(mess);
        String code = "";
        while (matcher.find()) {
            code = matcher.group(0);
        }
        return code;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void autoMoveNext() {
        edtphone1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edtphone1.getText().toString().length() >= 1) {
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
                if (edtphone2.getText().toString().length() >= 1) {
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
                if (edtphone3.getText().toString().length() >= 1) {
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
                if (edtphone4.getText().toString().length() >= 1) {
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
                if (edtphone5.getText().toString().length() >= 1) {
                    edtphone6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void onSendSuccess() {
       Toasty.success(getActivity(),"Send success OTP SMS " + phone, 3);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onSendFailed() {
        Toasty.warning(Objects.requireNonNull(getActivity()),"The phone " +phone + " send many OTP (SPAM) ", 3);

    }

    @SuppressLint("CheckResult")
    @Override
    public void onVerifyOTPSuccess() {
        presenterSignUp.doSignUp(client,pass);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onVerifyOTPFailed() {
        progressDialog.dismiss();
        Toasty.error(Objects.requireNonNull(getActivity()),getActivity().getString(R.string.virify_failed), 3);

    }

    @SuppressLint("CheckResult")
    @Override
    public void onSuccessSignUp() {
        progressDialog.dismiss();
        Toasty.success(Objects.requireNonNull(getActivity()),getActivity().getString(R.string.virify_success), 3);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_client, new LoginFragment()).commit();
    }

    @SuppressLint("CheckResult")
    @Override
    public void onFailedSignUp() {
        Toasty.error(Objects.requireNonNull(getActivity()),getActivity().getString(R.string.virify_failed), 3);
        getActivity().finish();
    }
}
