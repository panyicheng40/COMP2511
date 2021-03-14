package staff.test;

import java.time.LocalDate;
import staff.StaffMember;
import staff.Lecturer;
/**
 * 
 * This is the StaffTest in the package staff.test
 *
 */
public class StaffTest {
    
    // Calls printStaffDetails(...) twice with each of the above as arguments.
    public static void printStaffDetails(StaffMember Detail) {
		System.out.println(Detail.toString());
    }
    
    //
    public static void main(String[] args) {

      // Creates a StaffMember with a name and salary of your choosing.
      StaffMember StaffA = new StaffMember("AAA", 123.4, LocalDate.of(1999,12,31), LocalDate.of(2000,01,01));
      StaffMember StaffB = new StaffMember("BBB", 432.1, LocalDate.of(1999,12,31), LocalDate.of(2000,01,01));

      // Creates an instance of Lecturer with a name, salary, school and academic status of your choosing.
      Lecturer LecturerX = new Lecturer("XXX", 1024, LocalDate.of(1999,12,31), LocalDate.of(2000,01,01), "CSE", "A");
      Lecturer LecturerY = new Lecturer("XXX", 1024, LocalDate.of(1999,12,31), LocalDate.of(2000,01,01), "CSE", "A");
      Lecturer LecturerZ = new Lecturer("XXX", 1024, LocalDate.of(1999,12,31), LocalDate.of(2000,01,01), "CSE", "A");
        
      // Calls printStaffDetails(...) twice with each of the above as arguments.
      System.out.println("------Printing Staff Details------");
      printStaffDetails(StaffA);
      printStaffDetails(StaffB);
      System.out.println("------Printing Lecturer Details------");
      printStaffDetails(LecturerX);

      // Tests the equals(..) method of the two classes.
      if(StaffA.equals(StaffA) && !StaffA.equals(StaffB)) System.out.println("Reflexive: \tPASS");
      else System.out.println("Reflexive: \tFAIL");

      if(LecturerX.equals(LecturerY) && LecturerY.equals(LecturerX)) System.out.println("Symmetric: \tPASS");
      else System.out.println("Symmetric: \tFAIL");

      if(LecturerX.equals(LecturerY) && LecturerY.equals(LecturerZ) && LecturerZ.equals(LecturerX)) System.out.println("Transitive: \tPASS");
      else System.out.println("Transitive: \tFAIL");

      if(LecturerX.equals(LecturerY)) {
        if(LecturerX.equals(LecturerY)) System.out.println("Consistent: \tPASS");
        else System.out.println("Consistent: \tFAIL");
      }
      else System.out.println("Consistent: \tFAIL");

      if(StaffA.equals(null)) System.out.println("Non-null Check: FAIL");
      else System.out.println("Non-null Check: PASS");





      



    }

}