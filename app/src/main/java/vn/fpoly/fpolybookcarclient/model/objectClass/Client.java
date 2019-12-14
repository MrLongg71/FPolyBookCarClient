package vn.fpoly.fpolybookcarclient.model.objectClass;

import android.os.Parcel;
import android.os.Parcelable;

public class Client implements Parcelable {
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


    protected Client(Parcel in) {
        key = in.readString();
        email = in.readString();
        phone = in.readString();
        name = in.readString();
        token = in.readString();
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }





    public static Creator<Client> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(name);
        parcel.writeString(token);
    }
}
