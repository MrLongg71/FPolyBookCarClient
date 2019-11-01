package vn.fpoly.fpolybookcarclient.model.objectClass;

public class HintFood {
    private String title;
    private String detail;
    private int Image;
    private String minute;
    private String rate;

    public HintFood(String title, String detail, int image, String minute, String rate) {
        this.title = title;
        this.detail = detail;
        Image = image;
        this.minute = minute;
        this.rate = rate;
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

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
