package unsw.enrolment;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.grade = null;
        this.student = student;
        student.enrol(this);
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public boolean checkPassing() {
        if(grade != null && grade.checkPassing())
            return true;
        else
            return false;
    }

	public void setGrade(Grade grade) {
		this.grade = grade;
    }
    
}
