package vn.fpoly.fpolybookcarclient.presenter.order;

import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;
import vn.fpoly.fpolybookcarclient.model.order.ModelOrder;
import vn.fpoly.fpolybookcarclient.view.order.IViewReview;

public class PresenterOrder implements IPresenterOrder {

    private IViewReview iViewReview;
    private ModelOrder modelOrder;

    public PresenterOrder(IViewReview iViewReview) {
        this.iViewReview = iViewReview;
        modelOrder = new ModelOrder();
    }

    @Override
    public void getOrder(String idOrder, String event) {
        if(event.equals("1")){
            modelOrder.dowloadOrderFoodReview(idOrder,"OrderFoodClient",this);
        }else {
            modelOrder.dowloadOrderCarReview(idOrder,"OrderCarClient",this);
        }

    }

    @Override
    public void resultDowloadOrderFood(boolean success, OrderFood orderFood, vn.fpoly.fpolybookcarclient.model.objectClass.Driver driver) {
        if(success){
            iViewReview.onSuccessFood(orderFood,driver);
        }else {
            iViewReview.onFailed();
        }
    }

    @Override
    public void resultDowloadOrderCar(boolean success, OderCar oderCar, Driver driver) {
        if(success){
            iViewReview.onSuccessCar(oderCar,driver);
        }else {
            iViewReview.onFailed();
        }
    }

    @Override
    public void setRateDriver(double rate, String idDriver) {
        modelOrder.initSetRatingDriver(rate,idDriver,this);
    }

    @Override
    public void resultSetRate(boolean success) {
        if(success){
            iViewReview.onSuccessRate();
        }
    }


}
