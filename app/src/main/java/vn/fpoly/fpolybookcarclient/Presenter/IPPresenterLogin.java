package vn.fpoly.fpolybookcarclient.Presenter;

import android.app.Activity;
import android.widget.TextView;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.Client;

public interface IPPresenterLogin {
    //    *****Login****//
    void doLoginEmail(String email, String password);

    void doSendSMS(String phone, TextView txtResend, Activity activity);

    void doLoginPhone(String phone, String verify, Activity activity);

    //    *****Register****//
    void doRegisterEmail(Client client);

}
