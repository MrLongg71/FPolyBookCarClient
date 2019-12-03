package vn.fpoly.fpolybookcarclient.presenter.food.restaurant;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;

public interface IPresenterRestaurant {
    void getListRestaurant();
    void resultGetListRestaurant(ArrayList<Restaurant>arrRestaurant);
    void getListFood(ArrayList<String>arrKeyRestaurant);
    void resultGetListFood(ArrayList<FoodMenu>arrFoodMenu);
}
