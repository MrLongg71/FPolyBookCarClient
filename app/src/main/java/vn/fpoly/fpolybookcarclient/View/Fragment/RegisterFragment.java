package vn.fpoly.fpolybookcarclient.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import vn.fpoly.fpolybookcarclient.Library.Dialog;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.Client;
import vn.fpoly.fpolybookcarclient.Presenter.PresenterLogin;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.View.Interface.ViewLogin;


public class RegisterFragment extends Fragment implements View.OnClickListener, ViewLogin {
    private TextInputEditText edtPhone, edtName, edtPassword, edtEmail;
    private EditText edtcoutry;
    private Button btnRegister;
    private ImageButton imgBackBtnRegister;
    private FirebaseAuth mAuth;
    private PresenterLogin presenterLogin;

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
        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(this);
        imgBackBtnRegister.setOnClickListener(this);

        return view;
    }

    private void initView(View view) {
        presenterLogin      = new PresenterLogin(this);
        edtPhone            = view.findViewById(R.id.edtPhoneRegister);
        edtcoutry           = view.findViewById(R.id.edtcode);
        edtName             = view.findViewById(R.id.edtNameRegister);
        edtPassword         = view.findViewById(R.id.edtPasswordRegister);
        edtEmail            = view.findViewById(R.id.edtEmailRegister);
        btnRegister         = view.findViewById(R.id.btnRegister);
        imgBackBtnRegister  = view.findViewById(R.id.imgBackRegister);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                createClientWithEmail();
                break;
            case R.id.imgBackRegister:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }


    private void createClientWithEmail(){
        if(checkValid()){
            final String name = edtName.getText().toString().trim();
            final String email = edtEmail.getText().toString().trim();
            String pass = edtPassword.getText().toString().trim();
            final String phone = edtPhone.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String key = task.getResult().getUser().getUid();
                        Client client = new Client(key, email, phone, name);
                        presenterLogin.doRegisterEmail(client);
                        onSuccess();
                    }
                }
            });

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
    public void onSuccess() {
        Dialog.Success(getActivity());
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        VerifyPhoneFragment verifyPhoneFragment = new VerifyPhoneFragment();

        Bundle bundle = new Bundle();
        bundle.putString("phone" , edtPhone.getText().toString().trim());
        verifyPhoneFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_client, verifyPhoneFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onFailed() {
        Dialog.Error(getActivity());

    }
}