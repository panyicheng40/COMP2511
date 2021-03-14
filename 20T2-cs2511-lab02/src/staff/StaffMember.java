package staff;

import java.time.LocalDate;


/**
 * A staff member
 * @author Robert Clifton-Everest
 *
 */
public class StaffMember {

    // Attributes to store the staff member's name, salary, hire date, and end date.
    public String Name;
    public double Salary;
    public LocalDate HireDate;
    public LocalDate EndDate;

    public StaffMember(String Name, double Salary, LocalDate HireDate, LocalDate EndDate) {
        setName(Name);
        setSalary(Salary);
        setHireDate(HireDate);
        setEndDate(EndDate);
    }

    // Appropriate constructors, getters, setters, and other methods.
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public String getHireDate() {
        return HireDate.toString();
    }

    public void setHireDate(LocalDate HireDate) {
        this.HireDate = HireDate;
    }

    public String getEndDate() {
        return EndDate.toString();
    }

    public void setEndDate(LocalDate EndDate) {
        this.EndDate = EndDate;
    }

    // Overridden toString() methods.
    @Override
	public String toString() {
        String str = "Name:" + getName() + "\t Salary:" + getSalary() + "\t HireDate:" + getHireDate() + "\t EndDate:" + getEndDate();
        return str;
    }

    // Overridden equals(..) methods.
    @Override
	public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(this.getClass() == obj.getClass()) {
			StaffMember temp = (StaffMember) obj;
            if(this.getName().equals(temp.getName()) && this.getSalary() == temp.getSalary() && this.getHireDate().equals(temp.getHireDate()) && this.getEndDate().equals(temp.getEndDate())) return true;
            else return false;
		}
		else return false;
    }

}
