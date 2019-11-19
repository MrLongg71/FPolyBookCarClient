package vn.fpoly.fpolybookcarclient.presenter.food;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodPager;

public interface IPresenterFood {
    void getListFood();
    void resultGetListFood(FoodPager food);
}
