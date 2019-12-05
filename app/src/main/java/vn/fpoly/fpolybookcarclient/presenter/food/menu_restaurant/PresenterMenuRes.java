package vn.fpoly.fpolybookcarclient.presenter.food.menu_restaurant;

import android.util.Log;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.food.menu_restaurent.ModelMenuRestaurant;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.view.food.menu_restaurant.IViewMenuRes;

public class PresenterMenuRes implements IPresenterMenuRes {
    private IViewMenuRes iViewMenuRes;
    private ModelMenuRestaurant modelMenuRestaurant;

    public PresenterMenuRes(IViewMenuRes iViewMenuRes) {
        this.iViewMenuRes = iViewMenuRes;
        modelMenuRestaurant = new ModelMenuRestaurant();
    }


    @Override
    public void getListMenuFood(String keyRestaurent) {
        modelMenuRestaurant.dowloadListMenuFoodRes(keyRestaurent,this);
    }

    @Override
    public void resultListMenuFood(ArrayList<FoodMenu> foodMenuArrayList, boolean success) {
        if(success){
            iViewMenuRes.displayListMenuFood(foodMenuArrayList);
        }else {
            iViewMenuRes.displayListMenuFoodFailed();
        }
    }
}
