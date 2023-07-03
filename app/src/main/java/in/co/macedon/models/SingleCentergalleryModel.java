package in.co.macedon.models;

public class SingleCentergalleryModel {

    String center_gallery_id,cente_image,center_id,created_date;

    public SingleCentergalleryModel(String center_gallery_id, String cente_image, String center_id, String created_date) {
        this.center_gallery_id = center_gallery_id;
        this.cente_image = cente_image;
        this.center_id = center_id;
        this.created_date = created_date;
    }

    public String getCenter_gallery_id() {
        return center_gallery_id;
    }

    public void setCenter_gallery_id(String center_gallery_id) {
        this.center_gallery_id = center_gallery_id;
    }

    public String getCente_image() {
        return cente_image;
    }

    public void setCente_image(String cente_image) {
        this.cente_image = cente_image;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
