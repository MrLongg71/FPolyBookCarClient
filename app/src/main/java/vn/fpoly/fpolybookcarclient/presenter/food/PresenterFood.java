package vn.fpoly.fpolybookcarclient.presenter.food;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.food.ModelFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodPager;
import vn.fpoly.fpolybookcarclient.view.Fragment.IViewFood;

public class PresenterFood implements IPresenterFood {
    private ModelFood modelFood;
    private IViewFood iViewFood;
    private ArrayList<FoodPager>arrFood = new ArrayList<>();

    public PresenterFood(IViewFood iViewFood) {
        this.iViewFood = iViewFood;
        modelFood = new ModelFood();
    }

    @Override
    public void getListFood() {
        modelFood.dowloadListFood(this);
    }

    @Override
    public void resultGetListFood(FoodPager food) {
        arrFood.add(food);
        iViewFood.displayFood(arrFood);
    }
}
