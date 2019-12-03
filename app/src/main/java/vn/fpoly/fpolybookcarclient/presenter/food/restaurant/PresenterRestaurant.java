package vn.fpoly.fpolybookcarclient.presenter.food.restaurant;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.food.restaurant.ModelRestaurant;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.view.food.IViewRestaurant;

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
    }

    @Override
    public void getListFood(ArrayList<String> arrKeyRestaurant) {
        modelFoodSale.dowloadListFood(arrKeyRestaurant,this);
    }

    @Override
    public void resultGetListFood(ArrayList<FoodMenu> arrFoodMenu) {
        iViewFoodSale.displayFoodMenu(arrFoodMenu);
    }


}
