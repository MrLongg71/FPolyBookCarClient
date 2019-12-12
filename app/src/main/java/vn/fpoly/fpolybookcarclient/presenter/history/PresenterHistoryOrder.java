package vn.fpoly.fpolybookcarclient.presenter.history;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.history.ModelHistoryOrder;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;
import vn.fpoly.fpolybookcarclient.view.history.IVIewHistoryOrder;

public class PresenterHistoryOrder implements IPresenterHistoryOrder {
    private  IVIewHistoryOrder ivIewHistoryOrder;
    private ModelHistoryOrder modelHistoryOrder;


    public PresenterHistoryOrder(IVIewHistoryOrder ivIewHistoryOrder) {
        this.ivIewHistoryOrder = ivIewHistoryOrder;
        modelHistoryOrder = new ModelHistoryOrder();
    }

    @Override
    public void getListOrder() {
        modelHistoryOrder.dowloadListOrder(this);
    }

    @Override
    public void resultGetListOrder(ArrayList<OderCar> oderCars, ArrayList<OrderFood> orderFoods, boolean success) {
        if(success){
            ivIewHistoryOrder.displayListOrder(oderCars,orderFoods);
        }else {
            ivIewHistoryOrder.displayListOrderFailed();
        }

    }

}
