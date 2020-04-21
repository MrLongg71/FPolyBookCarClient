package vn.fpoly.fpolybookcarclient.presenter.client;

import vn.fpoly.fpolybookcarclient.model.client.ModelLogin;
import vn.fpoly.fpolybookcarclient.view.client.IViewLogin;

public class PresenterLogin implements IPresenterLogin {

    private IViewLogin iViewLogin;
    private ModelLogin modelLogin;

    public PresenterLogin(IViewLogin iViewLogin) {
        this.iViewLogin = iViewLogin;
        modelLogin = new ModelLogin();
    }

    @Override
    public void doSignIn(String email, String password) {
        modelLogin.initLogin(email,password,this);
    }

    @Override
    public void resultSignIn(boolean success, String message) {
        if(success){
            iViewLogin.onSuccess();
        }else {
            iViewLogin.onFailed(message);
        }
    }
}
