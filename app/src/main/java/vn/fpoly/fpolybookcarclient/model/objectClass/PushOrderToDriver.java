package vn.fpoly.fpolybookcarclient.model.objectClass;

public class PushOrderToDriver {
    private String idOrder;
    private String idDriver;
    private String event;


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

    public PushOrderToDriver(String idOrder, String idDriver, String event) {
        this.idOrder = idOrder;
        this.idDriver = idDriver;
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
