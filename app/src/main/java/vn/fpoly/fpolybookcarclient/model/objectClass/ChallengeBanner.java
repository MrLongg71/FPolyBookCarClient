package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class ChallengeBanner   {
    private String key;
    private String image;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ChallengeBanner() {
    }

    public ChallengeBanner(String key, String image) {
        this.key = key;
        this.image = image;
    }
}
