package vn.fpoly.fpolybookcarclient.presenter.client;

public interface IPresenterLogin {
    void doSignIn(String email,String password);
    void resultSignIn(boolean success,String message);


}
