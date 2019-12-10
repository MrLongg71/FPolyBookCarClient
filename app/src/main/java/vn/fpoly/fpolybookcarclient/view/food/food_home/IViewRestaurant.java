package vn.fpoly.fpolybookcarclient.view.food.food_home;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;

public interface IViewRestaurant {
    void displayRestaurant(ArrayList<Restaurant> arrRestaurant);
    void displayFoodMenu(ArrayList<Restaurant>arrRestaurant);
}
