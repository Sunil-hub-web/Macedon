package in.co.macedon.models;

public class SingleCenterPackageModel {

    String package_id,package_name,package_duration,class_week,service_id,package_price,package_description;

    public SingleCenterPackageModel(String package_id, String package_name, String package_duration, String class_week,
                                    String service_id, String package_price, String package_description) {

        this.package_id = package_id;
        this.package_name = package_name;
        this.package_duration = package_duration;
        this.class_week = class_week;
        this.service_id = service_id;
        this.package_price = package_price;
        this.package_description = package_description;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
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

    public String getClass_week() {
        return class_week;
    }

    public void setClass_week(String class_week) {
        this.class_week = class_week;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getPackage_price() {
        return package_price;
    }

    public void setPackage_price(String package_price) {
        this.package_price = package_price;
    }

    public String getPackage_description() {
        return package_description;
    }

    public void setPackage_description(String package_description) {
        this.package_description = package_description;
    }
}
