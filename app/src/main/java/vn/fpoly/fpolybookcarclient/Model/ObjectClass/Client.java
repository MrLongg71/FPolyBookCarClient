package vn.fpoly.fpolybookcarclient.Model.ObjectClass;

public class Client {
    private String key,email,phone,name;

    public Client(String key, String email, String phone, String name) {
        this.key = key;
        this.email = email;
        this.phone = phone;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
