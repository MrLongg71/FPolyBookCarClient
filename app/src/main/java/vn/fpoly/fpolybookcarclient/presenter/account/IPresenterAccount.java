package vn.fpoly.fpolybookcarclient.presenter.account;

import vn.fpoly.fpolybookcarclient.model.objectClass.Client;

public interface IPresenterAccount {
    void getInfoAccount();
    void resultGetInfoAccount(Client client);

    void updateInfoAccount(Client client);
    void resultUpdate(boolean success);

}
