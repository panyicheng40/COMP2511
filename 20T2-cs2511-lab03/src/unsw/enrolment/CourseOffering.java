package unsw.enrolment;
import java.util.ArrayList;
import java.util.List;

public class CourseOffering {

    private Course course;
    private String term;
    private List<Session> sessions;
    private List<Enrolment> enrolments;

    public CourseOffering(Course course, String term) {
        this.course = course;
        this.term = term;
        this.sessions = new ArrayList<>();
        this.enrolments = new ArrayList<>();
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public Course getCourse() {
        return course;
    }

    public String getTerm() {
        return term;
    }
    
    public void enrol(Student student) {
    	if (course.checkPassing(student)) {
    		
            enrolments.add(new Enrolment(this, student));
            System.out.println(student.getZID() + " enrolled in " + getCourse().getCourseCode());
        }
        else
            System.out.println("Error: " + student.getZID() + " failed to enrol in " + getCourse().getCourseCode());
    }

}
