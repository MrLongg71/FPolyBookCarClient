package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class ChallengeBanner   {
    private String key;
    private List<String>arrImage;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getArrImage() {
        return arrImage;
    }

    public void setArrImage(List<String> arrImage) {
        this.arrImage = arrImage;
    }

    public ChallengeBanner() {
    }

    public ChallengeBanner(String key, List<String> arrImage) {
        this.key = key;
        this.arrImage = arrImage;
    }
}
