package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class FoodCategories {
    private String key;
    private String title;
    private List<String> arrImage;

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

    public List<String> getArrImage() {
        return arrImage;
    }

    public void setArrImage(List<String> arrImage) {
        this.arrImage = arrImage;
    }

    public FoodCategories(String key, String title, List<String> arrImage) {
        this.key = key;
        this.title = title;
        this.arrImage = arrImage;
    }
}
