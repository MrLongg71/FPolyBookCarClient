package vn.fpoly.fpolybookcarclient.presenter.foodsale;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.foodsale.ModelFoodSale;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodSale;
import vn.fpoly.fpolybookcarclient.view.Fragment.IViewFoodSale;

public class PresenterFoodSale implements IPresenterFoodSale {
    private ModelFoodSale modelFoodSale;
    private ArrayList<FoodSale>arrFoodSale = new ArrayList<>();
    private IViewFoodSale iViewFoodSale;

    public PresenterFoodSale(IViewFoodSale iViewFoodSale) {
        this.iViewFoodSale = iViewFoodSale;
        modelFoodSale = new ModelFoodSale();

    }

    @Override
    public void getListFoodSale() {
        modelFoodSale.dowloadListNewsFood(this);
    }

    @Override
    public void resultGetListFoodSale(FoodSale foodSale) {
        arrFoodSale.add(foodSale);
        iViewFoodSale.displayFoodSale(arrFoodSale);
    }
}
