package in.co.macedon.models;

public class DieticianGetSet {

    String count, name, regularprice, salesprice;

    public DieticianGetSet(String count, String name, String regularprice, String salesprice) {
        this.count = count;
        this.name = name;
        this.regularprice = regularprice;
        this.salesprice = salesprice;
    }

    public String getCount() {
        return count;
    }

    public DieticianGetSet setCount(String count) {
        this.count = count;
        return this;
    }

    public String getName() {
        return name;
    }

    public DieticianGetSet setName(String name) {
        this.name = name;
        return this;
    }

    public String getRegularprice() {
        return regularprice;
    }

    public DieticianGetSet setRegularprice(String regularprice) {
        this.regularprice = regularprice;
        return this;
    }

    public String getSalesprice() {
        return salesprice;
    }

    public DieticianGetSet setSalesprice(String salesprice) {
        this.salesprice = salesprice;
        return this;
    }
}
