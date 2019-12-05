package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class FoodCategories {
    private String key;
    private String title;
    private String image;

    public FoodCategories() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FoodCategories(String key, String title, String image) {
        this.key = key;
        this.title = title;
        this.image = image;
    }
}
