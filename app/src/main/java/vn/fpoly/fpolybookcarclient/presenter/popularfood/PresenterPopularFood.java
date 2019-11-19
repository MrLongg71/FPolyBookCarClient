package vn.fpoly.fpolybookcarclient.presenter.popularfood;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.PopularFood;
import vn.fpoly.fpolybookcarclient.model.popularfood.ModelPoppularFood;
import vn.fpoly.fpolybookcarclient.view.Fragment.IViewPopularFood;

public class PresenterPopularFood implements IPresenterPopularFood {
    private ModelPoppularFood modelPoppularFood;
    private ArrayList<PopularFood>arrPopularFood = new ArrayList<>();
    private IViewPopularFood iViewPopularFood;

    public PresenterPopularFood(IViewPopularFood iViewPopularFood) {
        this.iViewPopularFood = iViewPopularFood;
        modelPoppularFood = new ModelPoppularFood();
    }

    @Override
    public void getListPopularFood() {
        modelPoppularFood.dowloadListPopularFood(this);
    }

    @Override
    public void resualGetListPopularFood(PopularFood popularFood) {
        arrPopularFood.add(popularFood);
        iViewPopularFood.displayPopularFood(arrPopularFood);
    }
}
