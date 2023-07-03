package in.co.macedon.models;

public class SingleCenterDataModel {

    String center_id,center_name,centerimage,city_name,pin,address1,email,contact_no;

    public SingleCenterDataModel(String center_id, String center_name, String centerimage, String city_name,
                                 String pin, String address1, String email, String contact_no) {

        this.center_id = center_id;
        this.center_name = center_name;
        this.centerimage = centerimage;
        this.city_name = city_name;
        this.pin = pin;
        this.address1 = address1;
        this.email = email;
        this.contact_no = contact_no;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public String getCenterimage() {
        return centerimage;
    }

    public void setCenterimage(String centerimage) {
        this.centerimage = centerimage;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }


}
