package vn.fpoly.fpolybookcarclient.presenter.food.foodcategories;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.food.foodcategories.ModelFoodCategories;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodCategories;
import vn.fpoly.fpolybookcarclient.view.food.food_home.IViewFoodCategories;

public class PresenterFoodCategories  implements IPresenterFoodCategories{
    private ModelFoodCategories modelFoodCategories;
    private IViewFoodCategories iViewFoodCategories;


    public PresenterFoodCategories(IViewFoodCategories iViewFoodCategories) {
        this.iViewFoodCategories = iViewFoodCategories;
        modelFoodCategories = new ModelFoodCategories();
    }

    @Override
    public void getListFoodCategories() {
        modelFoodCategories.dowloadListFoodCategories(this);
    }

    @Override
    public void resultGetFoodCategories(ArrayList<FoodCategories> arrFoodCategoris, ArrayList<FoodCategories> arrMenuFood) {
        iViewFoodCategories.displayFoodCategories(arrFoodCategoris,arrMenuFood);
    }


}
