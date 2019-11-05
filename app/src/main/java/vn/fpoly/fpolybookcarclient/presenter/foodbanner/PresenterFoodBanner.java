package vn.fpoly.fpolybookcarclient.presenter.foodbanner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.foodbanner.ModelFoodBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodBanner;
import vn.fpoly.fpolybookcarclient.view.Fragment.IViewFoodBanner;

public class PresenterFoodBanner implements IPresenterFoodBanner {
    private ModelFoodBanner modelFoodBanner;
    private ArrayList<FoodBanner> arrFoodBanner = new ArrayList<>();
    private IViewFoodBanner iViewFoodBanner;

    public PresenterFoodBanner(IViewFoodBanner iViewFoodBanner) {
        this.iViewFoodBanner = iViewFoodBanner;
        modelFoodBanner = new ModelFoodBanner();
    }

    @Override
    public void getListFood() {
        modelFoodBanner.dowloadListFood(this);
    }

    @Override
    public void resultGetListFood(FoodBanner foodBanner) {
        arrFoodBanner.add(foodBanner);
        iViewFoodBanner.displayListFood(arrFoodBanner);
    }
}
