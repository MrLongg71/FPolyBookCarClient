package vn.fpoly.fpolybookcarclient.presenter.account;

import vn.fpoly.fpolybookcarclient.model.account.ModelAccount;
import vn.fpoly.fpolybookcarclient.model.objectClass.Client;
import vn.fpoly.fpolybookcarclient.view.account.IViewAccount;

public class PresenterAccount implements IPresenterAccount {
    private IViewAccount iViewAccount;
    private ModelAccount modelAccount;

    public PresenterAccount(IViewAccount iViewAccount) {
        this.iViewAccount = iViewAccount;
        modelAccount = new ModelAccount();
    }

    @Override
    public void getInfoAccount() {
        modelAccount.dowloadInfoAccoint(this);
    }

    @Override
    public void resultGetInfoAccount(Client client) {
        iViewAccount.displayInfoAccount(client);
    }

    @Override
    public void updateInfoAccount(Client client) {
        modelAccount.updateInfo(client,this);
    }

    @Override
    public void resultUpdate(boolean success) {
        if(success){
            iViewAccount.updateInfoAccountSuccess();
        }else {
            iViewAccount.upadteInfoAccountFailed();
        }

    }

}
