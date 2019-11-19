package vn.fpoly.fpolybookcarclient.presenter.foodcategories;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.foodcategories.ModelFoodCategories;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodCategories;
import vn.fpoly.fpolybookcarclient.view.Fragment.IViewFoodCategories;

public class PresenterFoodCategories  implements IPresenterFoodCategories{
    private ModelFoodCategories modelFoodCategories;
    private IViewFoodCategories iViewFoodCategories;
    private ArrayList<FoodCategories>arrFoodCategories = new ArrayList<>();

    public PresenterFoodCategories(IViewFoodCategories iViewFoodCategories) {
        this.iViewFoodCategories = iViewFoodCategories;
        modelFoodCategories = new ModelFoodCategories();
    }

    @Override
    public void getListFoodCategories() {
        modelFoodCategories.dowloadListFoodCategories(this);
    }

    @Override
    public void resultGetFoodCategories(FoodCategories foodCategories) {
        arrFoodCategories.add(foodCategories);
        iViewFoodCategories.displayFoodCategories(arrFoodCategories);
    }
}
