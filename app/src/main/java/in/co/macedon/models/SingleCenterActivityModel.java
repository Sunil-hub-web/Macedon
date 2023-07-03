package in.co.macedon.models;

public class SingleCenterActivityModel {

    String service_master_id,service_master_name,image;

    public SingleCenterActivityModel(String service_master_id, String service_master_name, String image) {
        this.service_master_id = service_master_id;
        this.service_master_name = service_master_name;
        this.image = image;
    }

    public String getService_master_id() {
        return service_master_id;
    }

    public void setService_master_id(String service_master_id) {
        this.service_master_id = service_master_id;
    }

    public String getService_master_name() {
        return service_master_name;
    }

    public void setService_master_name(String service_master_name) {
        this.service_master_name = service_master_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
