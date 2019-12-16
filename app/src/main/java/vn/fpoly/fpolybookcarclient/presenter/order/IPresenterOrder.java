package vn.fpoly.fpolybookcarclient.presenter.order;

import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;

public interface IPresenterOrder {
    void getOrder(String idOrder,String event);

    void resultDowloadOrderFood(boolean success, OrderFood orderFood, vn.fpoly.fpolybookcarclient.model.objectClass.Driver driver);
    void resultDowloadOrderCar(boolean success, OderCar oderCar, Driver driver);

    void setRateDriver(double rate,String idDriver,String idOrder,String event);

    void resultSetRate(boolean success);
}
