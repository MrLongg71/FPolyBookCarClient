package vn.fpoly.fpolybookcarclient.presenter.cakebanner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.cakebanner.ModelCakeBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.CakesBanner;
import vn.fpoly.fpolybookcarclient.view.Fragment.IViewCakeBanner;

public class PresenterCakeBanner implements IPresenterCakeBanner  {
    private ModelCakeBanner modelCakeBanner;
    private IViewCakeBanner iViewCakeBanner;
    private ArrayList<CakesBanner>arrCaleBanner = new ArrayList<>();

    public PresenterCakeBanner(IViewCakeBanner  iViewCakeBanner) {
        this.iViewCakeBanner = iViewCakeBanner;
        modelCakeBanner = new ModelCakeBanner();

    }

    @Override
    public void getListCakeBanner() {
        modelCakeBanner.dowloaLisCake(this);
    }



    @Override
    public void resultGetListCake(CakesBanner cakesBanner) {
        arrCaleBanner.add(cakesBanner);
        iViewCakeBanner.displayListCake(arrCaleBanner);
    }
}
