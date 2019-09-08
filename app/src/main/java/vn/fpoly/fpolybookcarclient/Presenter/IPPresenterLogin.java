package vn.fpoly.fpolybookcarclient.Presenter;

import android.app.Activity;

public interface IPPresenterLogin {
    void doLoginEmail(String email, String password);
    void doSendSMS(String phone,Activity activity);
    void doLoginPhone(String phone, String verify, Activity activity);

}
