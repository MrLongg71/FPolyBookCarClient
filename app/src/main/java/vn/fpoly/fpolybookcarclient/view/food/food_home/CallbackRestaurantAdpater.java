package vn.fpoly.fpolybookcarclient.view.food.food_home;

import android.widget.TextView;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;

public interface CallbackRestaurantAdpater {
    void onClickRestautantMenu(Restaurant restaurant,String distance);
}
