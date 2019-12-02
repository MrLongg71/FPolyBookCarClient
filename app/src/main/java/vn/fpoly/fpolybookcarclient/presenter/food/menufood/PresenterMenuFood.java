package vn.fpoly.fpolybookcarclient.presenter.food.menufood;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.PopularFood;
import vn.fpoly.fpolybookcarclient.model.food.menufood.ModelMenuFood;
import vn.fpoly.fpolybookcarclient.view.food.IViewPopularFood;

public class PresenterMenuFood implements IPresenterMenuFood {
    private ModelMenuFood modelMenuFood;
    private ArrayList<PopularFood>arrPopularFood = new ArrayList<>();
    private IViewPopularFood iViewPopularFood;

    public PresenterMenuFood(IViewPopularFood iViewPopularFood) {
        this.iViewPopularFood = iViewPopularFood;
        modelMenuFood = new ModelMenuFood();
    }

    @Override
    public void getListPopularFood() {
        modelMenuFood.dowloadListPopularFood(this);
    }

    @Override
    public void resualGetListPopularFood(PopularFood popularFood) {
        arrPopularFood.add(popularFood);
        iViewPopularFood.displayPopularFood(arrPopularFood);
    }
}
