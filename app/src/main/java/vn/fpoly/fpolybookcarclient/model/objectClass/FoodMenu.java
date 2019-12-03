package vn.fpoly.fpolybookcarclient.model.objectClass;

public class FoodMenu {
    private String keyfood,name,description,image,price,keymenu;

    public FoodMenu(String keyfood, String name, String description, String image, String price, String keymenu) {
        this.keyfood = keyfood;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.keymenu = keymenu;
    }

    public FoodMenu() {
    }

    public String getKeyfood() {
        return keyfood;
    }

    public void setKeyfood(String keyfood) {
        this.keyfood = keyfood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKeymenu() {
        return keymenu;
    }

    public void setKeymenu(String keymenu) {
        this.keymenu = keymenu;
    }
}