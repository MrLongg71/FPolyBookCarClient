package vn.fpoly.fpolybookcarclient.view.client;

public interface IViewRegister {
    void onSendSuccess();
    void onSendFailed();

    void onVerifyOTPSuccess();
    void onVerifyOTPFailed();

    void onSuccessSignUp();
    void onFailedSignUp();

}
