package vn.fpoly.fpolybookcarclient.view.order;

import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;

public interface IViewReview {
    void onSuccessFood(OrderFood orderFood, Driver driver);
    void onSuccessCar(OderCar oderCar, Driver driver);
    void onSuccessRate();

    void onFailed();

}
