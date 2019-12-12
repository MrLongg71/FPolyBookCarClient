package vn.fpoly.fpolybookcarclient.presenter.history;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;

public interface IPresenterHistoryOrder {
    void getListOrder();
    void resultGetListOrder(ArrayList<OderCar> oderCars, ArrayList<OrderFood> orderFoods,boolean success);

}
