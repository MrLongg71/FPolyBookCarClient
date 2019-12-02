package vn.fpoly.fpolybookcarclient.presenter.food.breakfast;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.food.restaurantbreakfast.ModelRestaurantBreakFast;
import vn.fpoly.fpolybookcarclient.model.objectClass.BreakFast;
import vn.fpoly.fpolybookcarclient.view.food.IViewBreakFast;

public class PresenterBreakFast implements IPresenterBreakFast  {
    private ModelRestaurantBreakFast modelBreakFast;
    private ArrayList<BreakFast>arrBreakFast = new ArrayList<>();
    private IViewBreakFast iViewBreakFast;

    public PresenterBreakFast(IViewBreakFast iViewBreakFast) {
        this.iViewBreakFast = iViewBreakFast;
        modelBreakFast = new ModelRestaurantBreakFast();
    }

    @Override
    public void getListBreakFast() {
        modelBreakFast.dowloadListBreakFast(this);
    }

    @Override
    public void resualGetBreakFast(BreakFast breakFast) {
        arrBreakFast.add(breakFast);
        iViewBreakFast.displayBreakFast(arrBreakFast);
    }
}
