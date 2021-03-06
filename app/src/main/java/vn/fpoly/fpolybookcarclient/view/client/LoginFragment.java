package vn.fpoly.fpolybookcarclient.view.client;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import vn.fpoly.fpolybookcarclient.library.Dialog;
import vn.fpoly.fpolybookcarclient.presenter.client.PresenterLogin;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.activity.HomeActivity;
import vn.fpoly.fpolybookcarclient.view.activity.SplashScreenActivity;

public class LoginFragment extends Fragment implements View.OnClickListener ,IViewLogin {
    private Button btnloginHome;
    private ImageView btnAdd,btnGoogle,btnLoginPhone;
    private EditText edtuser, edtpass;
    private TextView txtforgot;
    private FirebaseAuth   firebaseAuth = FirebaseAuth.getInstance();
    private GoogleSignInClient signInClient;
    private PresenterLogin presenterLogin;
    private int REQUEST_CODE_SIGNIN_GOOGLE = 123;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);


        btnAdd.setOnClickListener(this);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        btnloginHome.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        btnLoginPhone.setOnClickListener(this);

        createClientWithGoogle();

        return view;
    }

    private void initView(View view) {
        btnLoginPhone   = view.findViewById(R.id.btnPhone);
        btnloginHome    = view.findViewById(R.id.btnLoginHome);
        btnAdd           = view.findViewById(R.id.btnAdd);
        edtuser         = view.findViewById(R.id.edtEmailLogin);
        edtpass         = view.findViewById(R.id.edtPassLogin);
        txtforgot       = view.findViewById(R.id.txtforgot);
        btnGoogle       = view.findViewById(R.id.btnGoogle);
        presenterLogin = new PresenterLogin(this);
        edtuser.setText("abc@gmail.com");
        edtpass.setText("123456");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginHome:
                loginClientWithEmail();
                break;
            case R.id.btnGoogle:
                logInWithGoogle();
                break;
            case R.id.btnPhone:
                loginClientWithPhone();
                break;
            case R.id.btnAdd:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_client,new RegisterFragment()).commit();
                break;

        }
    }

    private void loginClientWithPhone() {
//        startActivity(new Intent(getActivity(), LoginSMSActivity.class));
    }


    private void loginClientWithEmail() {
        if (checkValid()) {
            progressDialog.show();
            String email = edtuser.getText().toString().trim();
            String pass = edtpass.getText().toString().trim();
            presenterLogin.doSignIn(email,pass);
        }
    }

    private boolean checkValid() {
        String user = edtuser.getText().toString().trim();
        String pass = edtpass.getText().toString().trim();
        if (user.length() == 0) {
            edtuser.setError(getString(R.string.checkedituser));
            edtuser.requestFocus();
            return false;

        } else if (pass.length() == 0) {
            edtpass.setError(getString(R.string.checkeditpass));
            edtpass.requestFocus();
            return false;

        } else if (pass.length() < 6) {
            edtpass.setError(getString(R.string.checklenghpass));
            edtpass.requestFocus();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            edtuser.setError(getString(R.string.checkvalidemail));
            edtuser.requestFocus();
            return false;

        }
        return  true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGNIN_GOOGLE && resultCode == Activity.RESULT_OK) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            String tokenId = task.getResult().getIdToken();
            AuthCredential credential = GoogleAuthProvider.getCredential(tokenId, null);
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toasty.error(getActivity(), getString(R.string.loginfail), Toasty.LENGTH_SHORT, true).show();
                    }else {
                        onSuccess();
                    }
                }
            });
        }
    }

    private void logInWithGoogle() {
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, REQUEST_CODE_SIGNIN_GOOGLE);
    }

    private void createClientWithGoogle() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestProfile()
                .build();
        signInClient = GoogleSignIn.getClient(getActivity(), signInOptions);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onSuccess() {
        progressDialog.dismiss();

//        Toasty.success(Objects.requireNonNull(getActivity()),getActivity().getString(R.string.success),3);

        startActivity(new Intent(getActivity(), HomeActivity.class));
//        getActivity().finish();
    }

    @Override
    public void onFailed(String message) {
        progressDialog.dismiss();
        Dialog.Error(getActivity(),message);
    }



}
