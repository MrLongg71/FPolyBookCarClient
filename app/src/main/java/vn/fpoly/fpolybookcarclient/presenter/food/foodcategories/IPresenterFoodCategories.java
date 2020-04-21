package vn.fpoly.fpolybookcarclient.presenter.food.foodcategories;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodCategories;

public interface IPresenterFoodCategories {
    void getListFoodCategories();
    void resultGetFoodCategories(ArrayList<FoodCategories>arrFoodCategoris,ArrayList<FoodCategories>arrMenuFood);
}
