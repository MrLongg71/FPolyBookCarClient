package vn.fpoly.fpolybookcarclient.view.history;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;

public interface IVIewHistoryOrder {
    void displayListOrder(ArrayList<OderCar> oderCars, ArrayList<OrderFood> orderFoods);
    void displayListOrderFailed();

}
