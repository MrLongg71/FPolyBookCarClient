package vn.fpoly.fpolybookcarclient.Presenter;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.Client;
import vn.fpoly.fpolybookcarclient.View.Fragment.SignInFragment;
import vn.fpoly.fpolybookcarclient.View.Interface.ViewLogin;

public class PresenterClient implements IPPresenterClient {

    Client modelClient;
    ViewLogin viewLogin;
    SignInFragment signInFragment;
    public PresenterClient(ViewLogin viewLogin){
        viewLogin = viewLogin;

    }

    @Override
    public void doSignIn(String phone) {
    }

    @Override
    public void resultSignIn(boolean result) {

    }


}
