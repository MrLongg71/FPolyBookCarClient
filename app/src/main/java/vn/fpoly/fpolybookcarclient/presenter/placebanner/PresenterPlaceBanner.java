package vn.fpoly.fpolybookcarclient.presenter.placebanner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.PlaceBanner;
import vn.fpoly.fpolybookcarclient.model.placebanner.ModelPlaceBanner;
import vn.fpoly.fpolybookcarclient.view.Fragment.IViewPlaceBanner;

public class PresenterPlaceBanner implements IPresenterPlaceBanner {
    private ModelPlaceBanner modelPlaceBanner;
    private IViewPlaceBanner iViewPlaceBanner;
    private ArrayList<PlaceBanner> arrPlaceBanner = new ArrayList<>();

    public PresenterPlaceBanner(IViewPlaceBanner iViewPlaceBanner) {
        this.iViewPlaceBanner = iViewPlaceBanner;
        modelPlaceBanner = new ModelPlaceBanner();

    }

    @Override
    public void getListPlace() {
        modelPlaceBanner.dowloadListPlace(this);
    }

    @Override
    public void resultGetListPlace(PlaceBanner placeBanner) {
        arrPlaceBanner.add(placeBanner);
        iViewPlaceBanner.displayListPlace(arrPlaceBanner);
    }
}
