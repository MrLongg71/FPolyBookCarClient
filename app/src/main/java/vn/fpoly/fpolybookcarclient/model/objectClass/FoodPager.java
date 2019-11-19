package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class FoodPager {
    private String key;
    private List<String>arrImage;

    public FoodPager(String key, List<String> arrImage) {
        this.key = key;
        this.arrImage = arrImage;
    }

    public FoodPager() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getArrImage() {
        return arrImage;
    }

    public void setArrImage(List<String> arrImage) {
        this.arrImage = arrImage;
    }
}
