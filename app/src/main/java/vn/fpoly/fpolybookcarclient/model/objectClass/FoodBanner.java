package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class FoodBanner {
    private String title;
    private String detail;
    private String key;
    private String like;
    private List<String> arrImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public List<String> getArrImage() {
        return arrImage;
    }

    public void setArrImage(List<String> arrImage) {
        this.arrImage = arrImage;
    }

    public FoodBanner() {
    }

    public FoodBanner(String title, String detail, String key, String like, List<String> arrImage) {
        this.title = title;
        this.detail = detail;
        this.key = key;
        this.like = like;
        this.arrImage = arrImage;
    }
}
