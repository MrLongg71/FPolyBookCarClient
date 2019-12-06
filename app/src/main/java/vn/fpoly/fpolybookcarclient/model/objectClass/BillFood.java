package vn.fpoly.fpolybookcarclient.model.objectClass;

public class BillFood {
    private String key,keyFood;
    private int amountBuy;

    public BillFood(String key, String keyFood, int amountBuy) {
        this.key = key;
        this.keyFood = keyFood;
        this.amountBuy = amountBuy;
    }

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
}

