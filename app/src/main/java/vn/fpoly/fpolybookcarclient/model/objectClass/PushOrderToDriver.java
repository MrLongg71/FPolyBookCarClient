package vn.fpoly.fpolybookcarclient.model.objectClass;

public class PushOrderToDriver {
    private String idOrder;
    private String idDriver;
    private boolean isBookCar;


    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public PushOrderToDriver() {
    }

    public PushOrderToDriver(String idOrder, String idDriver, boolean isBookCar) {
        this.idOrder = idOrder;
        this.idDriver = idDriver;
        this.isBookCar = isBookCar;
    }
}
