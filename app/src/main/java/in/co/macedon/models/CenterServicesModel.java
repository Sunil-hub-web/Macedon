package in.co.macedon.models;

public class CenterServicesModel {

    String center_id,profile_image,center_name,city_id,city_name,area_id,areaname;

    public CenterServicesModel(String center_id, String profile_image, String center_name, String city_id,
                               String city_name, String area_id, String areaname) {

        this.center_id = center_id;
        this.profile_image = profile_image;
        this.center_name = center_name;
        this.city_id = city_id;
        this.city_name = city_name;
        this.area_id = area_id;
        this.areaname = areaname;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }
}
