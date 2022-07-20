package in.co.macedon.models;

public class TimeSlotGetSet {

    String timeslots;

    public TimeSlotGetSet(String timeslots) {
        this.timeslots = timeslots;
    }

    public String getTimeslots() {
        return timeslots;
    }

    public TimeSlotGetSet setTimeslots(String timeslots) {
        this.timeslots = timeslots;
        return this;
    }
}
