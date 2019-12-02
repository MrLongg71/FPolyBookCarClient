package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class News {

    private String key;
    private String title;
    private String like;
    private String detail;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public News() {
    }

    public News(String key, String title, String like, String detail, String image) {
        this.key = key;
        this.title = title;
        this.like = like;
        this.detail = detail;
        this.image = image;
    }
}
