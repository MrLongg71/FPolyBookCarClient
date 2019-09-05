package vn.fpoly.fpolybookcarclient.Presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;

import java.util.concurrent.Executor;

import vn.fpoly.fpolybookcarclient.Model.ModelClient;
import vn.fpoly.fpolybookcarclient.View.Fragment.SignInFragment;
import vn.fpoly.fpolybookcarclient.View.Interface.ViewClient;

public class PresenterClient implements IPPresenterClient {

    ModelClient modelClient;
    ViewClient viewClient;
    SignInFragment signInFragment;
    public PresenterClient(ViewClient viewClient){
        viewClient = viewClient;

    }

    @Override
    public void doSignIn(String phone) {
    }

    @Override
    public void resultSignIn(boolean result) {

    }


}
