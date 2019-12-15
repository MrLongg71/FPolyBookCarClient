package vn.fpoly.fpolybookcarclient.view.client;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import vn.fpoly.fpolybookcarclient.model.objectClass.Client;
import vn.fpoly.fpolybookcarclient.presenter.client.PresenterRegister;
import vn.fpoly.fpolybookcarclient.R;


public class RegisterFragment extends Fragment implements View.OnClickListener, IViewRegister {
    private TextInputEditText edtPhone, edtName, edtPassword, edtEmail;
    private EditText edtcoutry;
    private Button btnRegister;
    private ImageView imgBackBtnRegister;
    private PresenterRegister presenterSignUp;
    private ProgressDialog progressDialog;
    private Client client;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);

        edtcoutry.setFocusable(false);
        btnRegister.setOnClickListener(this);

        return view;
    }

    private void initView(View view) {
        presenterSignUp = new PresenterRegister(this);
        edtPhone            = view.findViewById(R.id.edtPhoneRegister);
        edtName             = view.findViewById(R.id.edtUserNameRegister);
        edtPassword         = view.findViewById(R.id.edtPassRegister);
        edtEmail            = view.findViewById(R.id.edtEmailRegister);
        btnRegister         = view.findViewById(R.id.btnRegister);

        imgBackBtnRegister  = view.findViewById(R.id.imgBackRegister);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                createClientWithEmail();
                break;

        }
    }


    private void createClientWithEmail(){
        if(checkValid()){
            progressDialog.show();
            final String name = edtName.getText().toString().trim();
            final String email = edtEmail.getText().toString().trim();
            String pass = edtPassword.getText().toString().trim();
            final String phone = edtPhone.getText().toString().trim();
            client = new Client("",email,phone,name,"");
            // -> otp

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            VerifyPhoneFragment verifyPhoneFragment = new VerifyPhoneFragment();

            if(client != null){
                Bundle bundle = new Bundle();
                bundle.putString("pass" , edtPassword.getText().toString().trim());
                bundle.putParcelable("client",client);
                verifyPhoneFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frame_client, verifyPhoneFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                progressDialog.dismiss();
            }

        }
    }

    private boolean checkValid() {
        String user = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String pass = edtPassword.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        if (user.length() == 0) {
            edtName.setError(getString(R.string.checkedituser));
            edtName.requestFocus();
            return false;
        } else if (email.length() == 0) {
            edtEmail.setError(getString(R.string.checkeditemailsigin));
            edtEmail.requestFocus();
            return false;
        } else if (phone.length() == 0) {
            edtPhone.setError(getString(R.string.checkvalidphone));
            edtPhone.requestFocus();
            return false;
        } else if (pass.length() == 0) {
            edtPassword.setError(getString(R.string.checkeditpass));
            edtPassword.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtPassword.setError(getString(R.string.checkvalidemail));
            edtPassword.requestFocus();
            return false;
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            edtPhone.setError(getString(R.string.checkvalidphone));
            edtPhone.requestFocus();
            return false;
        } else if (phone.length() < 10) {
            edtPhone.setError(getString(R.string.checklengphone));
            edtPhone.requestFocus();
            return false;
        } else if (pass.length() < 6) {
            edtPassword.setError(getString(R.string.checklenghpass));
            edtPassword.requestFocus();
            return false;
        } else {

            return true;

        }

    }


    @Override
    public void onSendSuccess() {

    }

    @Override
    public void onSendFailed() {

    }

    @Override
    public void onVerifyOTPSuccess() {

    }

    @Override
    public void onVerifyOTPFailed() {

    }

    @Override
    public void onSuccessSignUp() {


    }

    @Override
    public void onFailedSignUp() {


    }
}