package vn.fpoly.fpolybookcarclient.presenter.client;

import android.app.Activity;
import android.widget.TextView;

import vn.fpoly.fpolybookcarclient.model.objectClass.Client;

public interface IPPresenterRegister {

    void doSignUp(Client client,String password);
    void resultSignUp(boolean success,String message);


    void doSendVerifyOTP(String phone,TextView txtResend,Activity activity);
    void doVerifyOTP(String codeVerify,Activity activity);
    void resultVerifyOTP(boolean success, String message);

    void resultSendOTP(boolean success, String message);


}
