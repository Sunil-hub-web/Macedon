package in.co.macedon.models;

import java.util.ArrayList;

public class SingleCenterActivityModel {

    String service_master_id,service_master_name,image;
    ArrayList<CenterTimeingSlot> centerTimeingSlots;
    ArrayList<MemberShipModel> centerPackageModels;
    private boolean expanded;

    public SingleCenterActivityModel(String service_master_id, String service_master_name, String image,
                                     ArrayList<CenterTimeingSlot> centerTimeingSlots,
                                     ArrayList<MemberShipModel> centerPackageModels) {

        this.service_master_id = service_master_id;
        this.service_master_name = service_master_name;
        this.image = image;
        this.centerTimeingSlots = centerTimeingSlots;
        this.centerPackageModels = centerPackageModels;
        this.expanded = false;
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

    public ArrayList<CenterTimeingSlot> getCenterTimeingSlots() {
        return centerTimeingSlots;
    }

    public void setCenterTimeingSlots(ArrayList<CenterTimeingSlot> centerTimeingSlots) {
        this.centerTimeingSlots = centerTimeingSlots;
    }

    public ArrayList<MemberShipModel> getCenterPackageModels() {
        return centerPackageModels;
    }

    public void setCenterPackageModels(ArrayList<MemberShipModel> centerPackageModels) {
        this.centerPackageModels = centerPackageModels;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
