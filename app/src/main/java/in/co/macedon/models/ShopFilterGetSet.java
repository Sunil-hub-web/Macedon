package in.co.macedon.models;

public class ShopFilterGetSet {

    String name;

    public ShopFilterGetSet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ShopFilterGetSet setName(String name) {
        this.name = name;
        return this;
    }
}
