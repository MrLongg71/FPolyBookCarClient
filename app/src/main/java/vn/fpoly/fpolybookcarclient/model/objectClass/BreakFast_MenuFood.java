package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class BreakFast_MenuFood {
    private String key;
    private String title;
    private String distance;
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BreakFast_MenuFood() {
    }

    public BreakFast_MenuFood(String key, String title, String distance, String image) {
        this.key = key;
        this.title = title;
        this.distance = distance;
        this.image = image;
    }
}
