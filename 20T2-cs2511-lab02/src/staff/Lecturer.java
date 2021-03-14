package staff;

import java.time.LocalDate;


/**
 * 
 * This is the sub-class of StaffMember
 *
 */
public class Lecturer extends StaffMember {

    // An attribute to store the school (e.g. CSE) that the lecturer belongs to
    private String School;

    // An attribute that stores the academic status of the lecturer
    private String Status;
    
    public Lecturer(String Name, double Salary, LocalDate HireDate, LocalDate EndDate, String School, String Status) {
        super(Name, Salary, HireDate, EndDate);
		setSchool(School);
		setStatus(Status);
    }

    // Appropriate getters and setters.
    public String getSchool() {
		return School;
    }
    
	private void setSchool(String School) {
		this.School = School;
    }
    
	public String getStatus() {
		return Status;
    }
    
	private void setStatus(String Status) {
		this.Status = Status;
    }
    
    // An overriding toString() method that includes the school name and academic level.
    @Override
	public String toString() {
        String str = super.toString() + "\t School:" + this.School + "\t Status:" + this.Status;
		return str;
    }

    // An overriding equals(...) method.
    @Override
	public boolean equals(Object obj) {
		if(super.equals(obj)) {
      Lecturer temp = (Lecturer) obj;
      if(this.getSchool().equals(temp.getSchool()) && this.getStatus().equals(temp.getStatus())) return true;
      else return false;
    }
    else return false;
	}

}