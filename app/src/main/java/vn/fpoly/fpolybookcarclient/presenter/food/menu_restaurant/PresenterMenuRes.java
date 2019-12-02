package vn.fpoly.fpolybookcarclient.presenter.food.menu_restaurant;

import vn.fpoly.fpolybookcarclient.model.food.ModelMenuRestaurant;
import vn.fpoly.fpolybookcarclient.view.food.menu_restaurant.IViewMenuRes;

public class PresenterMenuRes {
    IViewMenuRes iViewMenuRes;
    ModelMenuRestaurant modelMenuRestaurant;

    public PresenterMenuRes(IViewMenuRes iViewMenuRes) {
        this.iViewMenuRes = iViewMenuRes;
        modelMenuRestaurant = new ModelMenuRestaurant();
    }

}
