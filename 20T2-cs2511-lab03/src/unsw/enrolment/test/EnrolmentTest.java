package unsw.enrolment.test;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Grade;
import unsw.enrolment.Lecture;
import unsw.enrolment.Student;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class EnrolmentTest {

    public static void main(String[] args) {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // TODO Create some sessions for the courses
        Lecture comp1511lecture = new Lecture("Location1", DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(14, 0), "AAA");
        Lecture comp1531lecture = new Lecture("Location2", DayOfWeek.TUESDAY, LocalTime.of(12, 0), LocalTime.of(15, 0), "BBB");
        Lecture comp2521lecture = new Lecture("Location3", DayOfWeek.THURSDAY, LocalTime.of(13, 0), LocalTime.of(16, 0), "CCC");

        comp1511Offering.addSession(comp1511lecture);
        comp1531Offering.addSession(comp1531lecture);
        comp2521Offering.addSession(comp2521lecture);

        // TODO Create a student
        Student Me = new Student("z5158177");

        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
        Enrolment comp1511enrolment = new Enrolment(comp1511Offering, Me);
        comp1511Offering.enrol(Me);

        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        Enrolment comp1531enrolment = new Enrolment(comp1531Offering, Me);
        comp1531Offering.enrol(Me);

        // TODO Give the student a passing grade for COMP1511
        comp1511enrolment.setGrade(new Grade(50));

        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        Enrolment comp2521enrolment = new Enrolment(comp2521Offering, Me);
        comp2521Offering.enrol(Me);

    }

}
