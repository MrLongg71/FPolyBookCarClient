package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class FoodSale {
    private String key;
    private String title;
    private String distance;
    private String detail;
    private List<String> arrImage;

    public FoodSale() {
    }

    public FoodSale(String key, String title, String distance, String detail, List<String> arrImage) {
        this.key = key;
        this.title = title;
        this.distance = distance;
        this.detail = detail;
        this.arrImage = arrImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<String> getArrImage() {
        return arrImage;
    }

    public void setArrImage(List<String> arrImage) {
        this.arrImage = arrImage;
    }
}
