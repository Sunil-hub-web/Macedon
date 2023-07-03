package in.co.macedon.models;

public class SingleCenterAmetiesModel {

    String facilities_id,facilities_name,image;

    public SingleCenterAmetiesModel(String facilities_id, String facilities_name, String image) {
        this.facilities_id = facilities_id;
        this.facilities_name = facilities_name;
        this.image = image;
    }

    public String getFacilities_id() {
        return facilities_id;
    }

    public void setFacilities_id(String facilities_id) {
        this.facilities_id = facilities_id;
    }

    public String getFacilities_name() {
        return facilities_name;
    }

    public void setFacilities_name(String facilities_name) {
        this.facilities_name = facilities_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
