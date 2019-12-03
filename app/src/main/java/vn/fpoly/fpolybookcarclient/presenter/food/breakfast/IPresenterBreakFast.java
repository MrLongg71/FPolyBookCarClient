package vn.fpoly.fpolybookcarclient.presenter.food.breakfast;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.BreakFast_MenuFood;

public interface IPresenterBreakFast {
    void getListBreakFast();
    void resualGetBreakFast(ArrayList<BreakFast_MenuFood>arrBreakFast,ArrayList<BreakFast_MenuFood>arrMenuFood);
}
