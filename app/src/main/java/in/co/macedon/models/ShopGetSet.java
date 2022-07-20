package in.co.macedon.models;

public class ShopGetSet {

    int image;
    String name, salesprice, regularprice, discount, premiumprice, rating;


    public ShopGetSet(int image, String name, String salesprice, String regularprice, String discount, String premiumprice, String rating) {
        this.image = image;
        this.name = name;
        this.salesprice = salesprice;
        this.regularprice = regularprice;
        this.discount = discount;
        this.premiumprice = premiumprice;
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public ShopGetSet setImage(int image) {
        this.image = image;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShopGetSet setName(String name) {
        this.name = name;
        return this;
    }

    public String getSalesprice() {
        return salesprice;
    }

    public ShopGetSet setSalesprice(String salesprice) {
        this.salesprice = salesprice;
        return this;
    }

    public String getRegularprice() {
        return regularprice;
    }

    public ShopGetSet setRegularprice(String regularprice) {
        this.regularprice = regularprice;
        return this;
    }

    public String getDiscount() {
        return discount;
    }

    public ShopGetSet setDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public String getPremiumprice() {
        return premiumprice;
    }

    public ShopGetSet setPremiumprice(String premiumprice) {
        this.premiumprice = premiumprice;
        return this;
    }


    public String getRating() {
        return rating;
    }

    public ShopGetSet setRating(String rating) {
        this.rating = rating;
        return this;
    }
}
