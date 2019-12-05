package vn.fpoly.fpolybookcarclient.view.food.menu_restaurant;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;

public interface IViewMenuRes {
    void displayListMenuFood(ArrayList<FoodMenu> foodMenulist);
    void displayListMenuFoodFailed();
}
