package vn.fpoly.fpolybookcarclient.view.account;

import vn.fpoly.fpolybookcarclient.model.objectClass.Client;

public interface IViewAccount {
    void displayInfoAccount(Client client);

    void updateInfoAccountSuccess();
    void upadteInfoAccountFailed();
}
