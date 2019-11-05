package vn.fpoly.fpolybookcarclient.presenter.eatwhat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.eatwhat.ModelEatWhatBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.HintFood;
import vn.fpoly.fpolybookcarclient.presenter.foodbanner.IPresenterFoodBanner;
import vn.fpoly.fpolybookcarclient.view.Fragment.IViewEatWhat;

public class PresenterEatWhat implements IPresenterEatWhat {
    private ModelEatWhatBanner modelEatWhatBanner;
    private ArrayList<HintFood> arrHintFood = new ArrayList<>();
    private IViewEatWhat iViewEatWhat;

    public PresenterEatWhat(IViewEatWhat iViewEatWhat) {
        this.iViewEatWhat = iViewEatWhat;
        modelEatWhatBanner = new ModelEatWhatBanner();
    }

    @Override
    public void getListEatWhat() {
        modelEatWhatBanner.dowloadListEatWhat(this);
    }

    @Override
    public void resultGetListEat(HintFood hintFood) {
        arrHintFood.add(hintFood);
        iViewEatWhat.displayListEatWhat(arrHintFood);
    }
}
