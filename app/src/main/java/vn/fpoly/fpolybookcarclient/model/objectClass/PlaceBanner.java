package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class PlaceBanner {

    private String key;
    private String title;
    private String like;
    private String detail;
    private List<String>arrImage;

    public PlaceBanner(String key, String title, String like, String detail, List<String> arrImage) {
        this.key = key;
        this.title = title;
        this.like = like;
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

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
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

    public PlaceBanner() {
    }
}
