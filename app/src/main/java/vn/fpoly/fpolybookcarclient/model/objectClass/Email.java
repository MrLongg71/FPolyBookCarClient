package vn.fpoly.fpolybookcarclient.model.objectClass;

public class Email {
    private String title;
    private String detail;
    private int image;

    public Email(String title, String detail, int image) {
        this.title = title;
        this.detail = detail;
        this.image = image;
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
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
