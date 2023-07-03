package in.co.macedon.models;

public class CenterTimeingSlot {

    String timing_id,fromtime,totime,day,sequence,service_id,service_master_id,service_master_name,image;
    public CenterTimeingSlot(String timing_id, String fromtime, String totime, String day, String sequence,
                             String service_id, String service_master_id, String service_master_name, String image) {
        this.timing_id = timing_id;
        this.fromtime = fromtime;
        this.totime = totime;
        this.day = day;
        this.sequence = sequence;
        this.service_id = service_id;
        this.service_master_id = service_master_id;
        this.service_master_name = service_master_name;
        this.image = image;
    }

    public String getTiming_id() {
        return timing_id;
    }

    public void setTiming_id(String timing_id) {
        this.timing_id = timing_id;
    }

    public String getFromtime() {
        return fromtime;
    }

    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
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
