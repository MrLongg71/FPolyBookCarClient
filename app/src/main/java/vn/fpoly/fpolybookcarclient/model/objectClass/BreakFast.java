package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class BreakFast {
    private String key;
    private String title;
    private String distance;
    private List<String>arrImage;

    public BreakFast() {
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

    public List<String> getArrImage() {
        return arrImage;
    }

    public void setArrImage(List<String> arrImage) {
        this.arrImage = arrImage;
    }

    public BreakFast(String key, String title, String distance, List<String> arrImage) {
        this.key = key;
        this.title = title;
        this.distance = distance;
        this.arrImage = arrImage;
    }
}
