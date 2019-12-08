package vn.fpoly.fpolybookcarclient.model.objectClass;

public class OrderFood {
    private String key,keyRestaurant,keyDriver,keyClient,keyBillDetail,date;
    private double latitudeClient,longitudeClient,price,rate,distance;
    private boolean status,finish;

    public OrderFood(String key, String keyRestaurant, String keyDriver, String keyClient, String keyBillDetail, String date, double latitudeClient, double longitudeClient, double price, double rate, double distance, boolean status, boolean finish) {
        this.key = key;
        this.keyRestaurant = keyRestaurant;
        this.keyDriver = keyDriver;
        this.keyClient = keyClient;
        this.keyBillDetail = keyBillDetail;
        this.date = date;
        this.latitudeClient = latitudeClient;
        this.longitudeClient = longitudeClient;
        this.price = price;
        this.rate = rate;
        this.distance = distance;
        this.status = status;
        this.finish = finish;
    }

    public OrderFood() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public String getKeyBillDetail() {
        return keyBillDetail;
    }

    public void setKeyBillDetail(String keyBillDetail) {
        this.keyBillDetail = keyBillDetail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyRestaurant() {
        return keyRestaurant;
    }

    public void setKeyRestaurant(String keyRestaurant) {
        this.keyRestaurant = keyRestaurant;
    }

    public String getKeyDriver() {
        return keyDriver;
    }

    public void setKeyDriver(String keyDriver) {
        this.keyDriver = keyDriver;
    }

    public String getKeyClient() {
        return keyClient;
    }

    public void setKeyClient(String keyClient) {
        this.keyClient = keyClient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLatitudeClient() {
        return latitudeClient;
    }

    public void setLatitudeClient(double latitudeClient) {
        this.latitudeClient = latitudeClient;
    }

    public double getLongitudeClient() {
        return longitudeClient;
    }

    public void setLongitudeClient(double longitudeClient) {
        this.longitudeClient = longitudeClient;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
