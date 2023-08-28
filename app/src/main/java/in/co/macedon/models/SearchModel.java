package in.co.macedon.models;

public class SearchModel {

    String id,center_name,status,city_name,areaname,hygene,logo_image,profile_image,service_id,details;

    public SearchModel(String id, String center_name, String status, String city_name, String areaname,
                       String hygene, String logo_image, String profile_image, String service_id, String details) {
        this.id = id;
        this.center_name = center_name;
        this.status = status;
        this.city_name = city_name;
        this.areaname = areaname;
        this.hygene = hygene;
        this.logo_image = logo_image;
        this.profile_image = profile_image;
        this.service_id = service_id;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getHygene() {
        return hygene;
    }

    public void setHygene(String hygene) {
        this.hygene = hygene;
    }

    public String getLogo_image() {
        return logo_image;
    }

    public void setLogo_image(String logo_image) {
        this.logo_image = logo_image;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
