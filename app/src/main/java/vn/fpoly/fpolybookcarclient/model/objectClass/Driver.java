package vn.fpoly.fpolybookcarclient.model.objectClass;

public class Driver {
    private String keydriver, email, licenseplate, name, phone,token;
    private boolean status, working;
    private double rate,latitude,longitude,distance,time;

    public Driver(String keydriver, String email, String licenseplate, String name, String phone, String token, boolean status, boolean working, double rate, double latitude, double longitude, double distance, double time) {
        this.keydriver = keydriver;
        this.email = email;
        this.licenseplate = licenseplate;
        this.name = name;
        this.phone = phone;
        this.token = token;
        this.status = status;
        this.working = working;
        this.rate = rate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Driver() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getKeydriver() {
        return keydriver;
    }

    public void setKeydriver(String keydriver) {
        this.keydriver = keydriver;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}