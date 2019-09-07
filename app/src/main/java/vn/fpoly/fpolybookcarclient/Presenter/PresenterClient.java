package vn.fpoly.fpolybookcarclient.Presenter;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.Client;
import vn.fpoly.fpolybookcarclient.View.Fragment.SignInFragment;
import vn.fpoly.fpolybookcarclient.View.Interface.ViewClient;

public class PresenterClient implements IPPresenterClient {

    Client modelClient;
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
