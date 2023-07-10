package in.co.macedon.models;

public class Subscriptions_ModelClass {

    String user_membership_history_id,package_name,package_duration,total_sesson,package_price,purchace_date,expair_date,paid_amount,Center_Id,Center_Name;

    public Subscriptions_ModelClass(String user_membership_history_id, String package_name, String package_duration,
                                    String total_sesson, String package_price, String purchace_date,
                                    String expair_date, String paid_amount, String center_Id,String center_Name) {

        this.user_membership_history_id = user_membership_history_id;
        this.package_name = package_name;
        this.package_duration = package_duration;
        this.total_sesson = total_sesson;
        this.package_price = package_price;
        this.purchace_date = purchace_date;
        this.expair_date = expair_date;
        this.paid_amount = paid_amount;
        Center_Id = center_Id;
        Center_Name = center_Name;
    }

    public String getUser_membership_history_id() {
        return user_membership_history_id;
    }

    public void setUser_membership_history_id(String user_membership_history_id) {
        this.user_membership_history_id = user_membership_history_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_duration() {
        return package_duration;
    }

    public void setPackage_duration(String package_duration) {
        this.package_duration = package_duration;
    }

    public String getTotal_sesson() {
        return total_sesson;
    }

    public void setTotal_sesson(String total_sesson) {
        this.total_sesson = total_sesson;
    }

    public String getPackage_price() {
        return package_price;
    }

    public void setPackage_price(String package_price) {
        this.package_price = package_price;
    }

    public String getPurchace_date() {
        return purchace_date;
    }

    public void setPurchace_date(String purchace_date) {
        this.purchace_date = purchace_date;
    }

    public String getExpair_date() {
        return expair_date;
    }

    public void setExpair_date(String expair_date) {
        this.expair_date = expair_date;
    }

    public String getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(String paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getCenter_Id() {
        return Center_Id;
    }

    public void setCenter_Id(String center_Id) {
        Center_Id = center_Id;
    }

    public String getCenter_Name() {
        return Center_Name;
    }

    public void setCenter_Name(String center_Name) {
        Center_Name = center_Name;
    }
}
