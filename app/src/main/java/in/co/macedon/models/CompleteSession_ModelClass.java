package in.co.macedon.models;

public class CompleteSession_ModelClass {

    String date,time,gymName,gymLocation,inTime,outTime;

    public CompleteSession_ModelClass(String date, String time, String gymName, String gymLocation, String inTime, String outTime) {
        this.date = date;
        this.time = time;
        this.gymName = gymName;
        this.gymLocation = gymLocation;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getGymLocation() {
        return gymLocation;
    }

    public void setGymLocation(String gymLocation) {
        this.gymLocation = gymLocation;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
