package in.co.macedon.models;

public class Subscriptions_ModelClass {

    String membership_id,membership_name,membership_description,membership_price,membership_period,no_of_class;

    public Subscriptions_ModelClass(String membership_id, String membership_name, String membership_description, String membership_price, String membership_period, String no_of_class) {
        this.membership_id = membership_id;
        this.membership_name = membership_name;
        this.membership_description = membership_description;
        this.membership_price = membership_price;
        this.membership_period = membership_period;
        this.no_of_class = no_of_class;
    }

    public String getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(String membership_id) {
        this.membership_id = membership_id;
    }

    public String getMembership_name() {
        return membership_name;
    }

    public void setMembership_name(String membership_name) {
        this.membership_name = membership_name;
    }

    public String getMembership_description() {
        return membership_description;
    }

    public void setMembership_description(String membership_description) {
        this.membership_description = membership_description;
    }

    public String getMembership_price() {
        return membership_price;
    }

    public void setMembership_price(String membership_price) {
        this.membership_price = membership_price;
    }

    public String getMembership_period() {
        return membership_period;
    }

    public void setMembership_period(String membership_period) {
        this.membership_period = membership_period;
    }

    public String getNo_of_class() {
        return no_of_class;
    }

    public void setNo_of_class(String no_of_class) {
        this.no_of_class = no_of_class;
    }
}
