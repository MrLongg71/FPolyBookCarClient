package vn.fpoly.fpolybookcarclient.presenter.food.foodsale;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.food.restaurant.ModelRestaurant;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.view.food.IViewRestaurant;

public class PresenterFoodSale implements IPresenterFoodSale {
    private ModelRestaurant modelFoodSale;
    private ArrayList<Restaurant> arrRestaurant = new ArrayList<>();
    private IViewRestaurant iViewFoodSale;

    public PresenterFoodSale(IViewRestaurant iViewFoodSale) {
        this.iViewFoodSale = iViewFoodSale;
        modelFoodSale = new ModelRestaurant();

    }

    @Override
    public void getListFoodSale() {
        modelFoodSale.dowloadListNewsFood(this);
    }

    @Override
    public void resultGetListFoodSale(Restaurant restaurant) {
        arrRestaurant.add(restaurant);
        iViewFoodSale.displayFoodSale(arrRestaurant);
    }
}
