package vn.fpoly.fpolybookcarclient.Model.ObjectClass;

public class CakesBanner {
    private String title;
    private String detail;
    private int Image;

    public CakesBanner(String title, String detail, int image) {
        this.title = title;
        this.detail = detail;
        Image = image;
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
}
