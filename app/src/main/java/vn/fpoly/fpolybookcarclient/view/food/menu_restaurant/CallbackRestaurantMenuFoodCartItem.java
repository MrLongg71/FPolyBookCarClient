package vn.fpoly.fpolybookcarclient.view.food.menu_restaurant;

import android.widget.TextView;

public interface  CallbackRestaurantMenuFoodCartItem {
    void initIncreasing(int i,TextView txtAmount);
    void initReduction(int i,TextView txtAmount);

}
