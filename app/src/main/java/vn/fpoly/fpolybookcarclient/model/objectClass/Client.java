package vn.fpoly.fpolybookcarclient.model.objectClass;

public class Client {
    private String key,email,phone,name,token;


    public Client(String key, String email, String phone, String name, String token) {
        this.key = key;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.token = token;
    }

    public Client() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
