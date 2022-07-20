package in.co.macedon.models;

public class Banner_ModelClass {

    String banner_id,banner_title,banner_image;

    public Banner_ModelClass(String banner_id, String banner_title, String banner_image) {
        this.banner_id = banner_id;
        this.banner_title = banner_title;
        this.banner_image = banner_image;
    }

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_title() {
        return banner_title;
    }

    public void setBanner_title(String banner_title) {
        this.banner_title = banner_title;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }
}
