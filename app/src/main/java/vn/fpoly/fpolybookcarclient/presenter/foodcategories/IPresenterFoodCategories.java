package vn.fpoly.fpolybookcarclient.presenter.foodcategories;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodCategories;

public interface IPresenterFoodCategories {
    void getListFoodCategories();
    void resultGetFoodCategories(FoodCategories foodCategories);
}
