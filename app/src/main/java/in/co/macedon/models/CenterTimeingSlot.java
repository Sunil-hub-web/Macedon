package in.co.macedon.models;

public class CenterTimeingSlot {

    String timing_id,fromtime,totime,day,sequence,service_id,status;
    public CenterTimeingSlot(String timing_id, String fromtime, String totime, String day, String sequence,
                             String service_id,String status) {
        this.timing_id = timing_id;
        this.fromtime = fromtime;
        this.totime = totime;
        this.day = day;
        this.sequence = sequence;
        this.service_id = service_id;
        this.status = status;
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

    public String getImage() {
        return status;
    }

    public void setImage(String image) {
        this.status = image;
    }
}
