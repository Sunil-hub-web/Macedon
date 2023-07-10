package in.co.macedon.models;

public class CompleteSession_ModelClass {

    String scan_id,center_id,user_id,date,time,user_membership_history_id,commition_amount,center_name;

    public CompleteSession_ModelClass(String scan_id, String center_id, String user_id, String date, String time,
                                      String user_membership_history_id, String commition_amount,
                                      String center_name) {
        this.scan_id = scan_id;
        this.center_id = center_id;
        this.user_id = user_id;
        this.date = date;
        this.time = time;
        this.user_membership_history_id = user_membership_history_id;
        this.commition_amount = commition_amount;
        this.center_name = center_name;
    }

    public String getScan_id() {
        return scan_id;
    }

    public void setScan_id(String scan_id) {
        this.scan_id = scan_id;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser_membership_history_id() {
        return user_membership_history_id;
    }

    public void setUser_membership_history_id(String user_membership_history_id) {
        this.user_membership_history_id = user_membership_history_id;
    }

    public String getCommition_amount() {
        return commition_amount;
    }

    public void setCommition_amount(String commition_amount) {
        this.commition_amount = commition_amount;
    }

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }
}
