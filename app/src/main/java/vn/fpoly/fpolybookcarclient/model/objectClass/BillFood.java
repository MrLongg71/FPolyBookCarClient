package vn.fpoly.fpolybookcarclient.model.objectClass;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BillFood implements Parcelable {
    private String key,keyFood;
    private int amountBuy;

    public BillFood(String key, String keyFood, int amountBuy) {
        this.key = key;
        this.keyFood = keyFood;
        this.amountBuy = amountBuy;
    }

    protected BillFood(Parcel in) {
        key = in.readString();
        keyFood = in.readString();
        amountBuy = in.readInt();
    }

    public static final Creator<BillFood> CREATOR = new Creator<BillFood>() {
        @Override
        public BillFood createFromParcel(Parcel in) {
            return new BillFood(in);
        }

        @Override
        public BillFood[] newArray(int size) {
            return new BillFood[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyFood() {
        return keyFood;
    }

    public void setKeyFood(String keyFood) {
        this.keyFood = keyFood;
    }

    public int getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(int amountBuy) {
        this.amountBuy = amountBuy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(keyFood);
        parcel.writeInt(amountBuy);
    }
}

