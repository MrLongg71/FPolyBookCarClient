package vn.fpoly.fpolybookcarclient.presenter.food.restaurant;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.food.restaurant.ModelRestaurant;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.view.food.food_home.IViewRestaurant;

public class PresenterRestaurant implements IPresenterRestaurant {
    private ModelRestaurant modelFoodSale;
    private IViewRestaurant iViewFoodSale;

    public PresenterRestaurant(IViewRestaurant iViewFoodSale) {
        this.iViewFoodSale = iViewFoodSale;
        modelFoodSale = new ModelRestaurant();

    }

    @Override
    public void getListRestaurant() {
        modelFoodSale.dowloadListRestaurant(this);
    }

    @Override
    public void resultGetListRestaurant(ArrayList<Restaurant> arrRestaurant) {
        iViewFoodSale.displayRestaurant(arrRestaurant);
        iViewFoodSale.displayFoodMenu(arrRestaurant);
    }






}
