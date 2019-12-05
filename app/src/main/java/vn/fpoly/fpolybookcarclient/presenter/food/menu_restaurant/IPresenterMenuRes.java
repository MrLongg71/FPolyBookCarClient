package vn.fpoly.fpolybookcarclient.presenter.food.menu_restaurant;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;

public interface IPresenterMenuRes {
    void getListMenuFood(String keyRestaurent);
    void resultListMenuFood(ArrayList<FoodMenu> foodMenuArrayList, boolean success);
}
