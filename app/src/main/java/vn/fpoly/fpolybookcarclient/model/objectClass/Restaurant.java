package vn.fpoly.fpolybookcarclient.model.objectClass;

import java.util.List;

public class Restaurant {
    private String key;
    private String name;
    private boolean status;
    private String timeend;
    private String timestart;
    private double longitude;
    private double latitude;
    private String keymenu;
    private String detail;
    private String address;
    private double rate;
    private String image;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }

    public String getTimestart() {
        return timestart;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getKeymenu() {
        return keymenu;
    }

    public void setKeymenu(String keymenu) {
        this.keymenu = keymenu;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Restaurant() {
    }

    public Restaurant(String key, String name, boolean status, String timeend, String timestart, double longitude, double latitude, String keymenu, String detail, String address, double rate, String image) {
        this.key = key;
        this.name = name;
        this.status = status;
        this.timeend = timeend;
        this.timestart = timestart;
        this.longitude = longitude;
        this.latitude = latitude;
        this.keymenu = keymenu;
        this.detail = detail;
        this.address = address;
        this.rate = rate;
        this.image = image;
    }
}
