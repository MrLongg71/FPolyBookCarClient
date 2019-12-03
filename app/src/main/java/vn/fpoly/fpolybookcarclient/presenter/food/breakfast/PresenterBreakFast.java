package vn.fpoly.fpolybookcarclient.presenter.food.breakfast;

import android.util.Log;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.food.restaurantbreakfast_menufood.ModelRestaurantBreakFast_MenuFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.BreakFast_MenuFood;
import vn.fpoly.fpolybookcarclient.view.food.IViewBreakFast;

public class PresenterBreakFast implements IPresenterBreakFast  {
    private ModelRestaurantBreakFast_MenuFood modelBreakFast;

    private IViewBreakFast iViewBreakFast;

    public PresenterBreakFast(IViewBreakFast iViewBreakFast) {
        this.iViewBreakFast = iViewBreakFast;
        modelBreakFast = new ModelRestaurantBreakFast_MenuFood();
    }

    @Override
    public void getListBreakFast() {
        modelBreakFast.dowloadListBreakFast(this);
    }

    @Override
    public void resualGetBreakFast(ArrayList<BreakFast_MenuFood> arrBreakFast, ArrayList<BreakFast_MenuFood> arrMenuFood) {
        iViewBreakFast.displayBreakFast(arrBreakFast, arrMenuFood);

    }

}
