package unsw.enrolment;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Session {

    private String location;
    private DayOfWeek day;
    private LocalTime start;
    private LocalTime end;

    public Session(CourseOffering offering, String location, DayOfWeek day, LocalTime start, LocalTime end) {
        this.location = location;
        this.day = day;
        this.start = start;
        this.end = end;
        offering.addSession(this);
    }

    public String getLocation() {
        return location;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }
	

}
