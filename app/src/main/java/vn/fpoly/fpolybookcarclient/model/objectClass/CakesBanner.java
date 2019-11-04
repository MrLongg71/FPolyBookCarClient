package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class CakesBanner {
    private String title;
    private String detail;
    private List<String> arrImage;
    private String key;

    public CakesBanner() {
    }

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

    public List<String> getArrImage() {
        return arrImage;
    }

    public void setArrImage(List<String> arrImage) {
        this.arrImage = arrImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CakesBanner(String title, String detail, List<String> arrImage, String key) {
        this.title = title;
        this.detail = detail;
        this.arrImage = arrImage;
        this.key = key;
    }

}
