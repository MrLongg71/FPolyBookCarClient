package vn.fpoly.fpolybookcarclient.Model.ObjectClass;

public class HistoryBookCar {
    private String place;
    private String date;
    private String genre;
    private int image;

    public HistoryBookCar(String place, String date, String genre, int image) {
        this.place = place;
        this.date = date;
        this.genre = genre;
        this.image = image;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
