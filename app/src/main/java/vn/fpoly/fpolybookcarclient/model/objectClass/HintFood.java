package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class HintFood {
    private String title;
    private String address;
    private List<String>arrImage;
    private String time;
    private String key;
    private String like;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getArrImage() {
        return arrImage;
    }

    public void setArrImage(List<String> arrImage) {
        this.arrImage = arrImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public HintFood() {
    }

    public HintFood(String title, String address, List<String> arrImage, String time, String key, String like) {
        this.title = title;
        this.address = address;
        this.arrImage = arrImage;
        this.time = time;
        this.key = key;
        this.like = like;
    }
}
