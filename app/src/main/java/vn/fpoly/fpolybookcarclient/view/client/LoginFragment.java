package vn.fpoly.fpolybookcarclient.view.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import es.dmoral.toasty.Toasty;
import vn.fpoly.fpolybookcarclient.library.Dialog;
import vn.fpoly.fpolybookcarclient.presenter.client.PresenterLogin;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.Activity.HomeActivity;

public class LoginFragment extends Fragment implements View.OnClickListener, ViewLogin {
    private Button btnloginHome, btnLoginPhone;
    private SignInButton btngoogle;
    private TextInputEditText edtuser, edtpass;
    private TextView txtforgot;
    private ImageButton imgBtnBackLogIn;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient signInClient;
    private PresenterLogin presenterLogin;
    private int REQUEST_CODE_SIGNIN_GOOGLE = 123;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        firebaseAuth = FirebaseAuth.getInstance();
        presenterLogin = new PresenterLogin(this);

        btnloginHome.setOnClickListener(this);
        btngoogle.setOnClickListener(this);
        btnLoginPhone.setOnClickListener(this);
        imgBtnBackLogIn.setOnClickListener(this);
        btngoogle.setSize(SignInButton.SIZE_STANDARD);

        createClientWithGoogle();

        return view;
    }

    private void initView(View view) {
        btnLoginPhone   = view.findViewById(R.id.btnLoginWithPhone);
        btnloginHome    = view.findViewById(R.id.btnLoginHome);
        btngoogle       = view.findViewById(R.id.btngoogle);
        edtuser         = view.findViewById(R.id.edtEmailLogin);
        edtpass         = view.findViewById(R.id.edtPassLogin);
        txtforgot       = view.findViewById(R.id.txtforgot);
        btngoogle       = view.findViewById(R.id.btngoogle);
        txtforgot       = view.findViewById(R.id.txtforgot);
        btngoogle       = view.findViewById(R.id.btngoogle);
        txtforgot       = view.findViewById(R.id.txtforgot);
        imgBtnBackLogIn = view.findViewById(R.id.imgBackLogIn);

        btngoogle       = view.findViewById(R.id.btngoogle);

        txtforgot       = view.findViewById(R.id.txtforgot);

        edtuser.setText("abc@gmail.com");
        edtpass.setText("123456");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnLoginHome:
                loginClientWithEmail();
                startActivity(new Intent(getActivity(), HomeActivity.class));

                break;

            case R.id.btngoogle:
                logInWithGoogle();
                break;
            case R.id.btnLoginWithPhone:
                loginClientWithPhone();
                break;
            case R.id.imgBackLogIn:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }

    private void loginClientWithPhone() {
        startActivity(new Intent(getActivity(), LoginSMSActivity.class));
    }

    private void loginClientWithEmail() {
        if (checkValid()) {
            String user = edtuser.getText().toString().trim();
            String pass = edtpass.getText().toString().trim();
            presenterLogin.doLoginEmail(user, pass);
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

        } else {
            startActivity(new Intent(getActivity(), HomeActivity.class));
            return true;
        }
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

    @Override
    public void onSuccess() {
        Dialog.Success(getActivity());

    }

    @Override
    public void onFailed() {
        Dialog.Error(getActivity());
    }
}
