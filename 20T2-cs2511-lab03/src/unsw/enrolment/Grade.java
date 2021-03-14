package unsw.enrolment;

public class Grade {
    private int mark;
    private String grade;

    public Grade(int mark) {
        this.mark = mark;
        if (mark < 50) grade = "FL";
        else if (mark < 65) grade = "PS";
        else if (mark < 75) grade = "DN";
        else grade = "HD";
    }

    public boolean checkPassing() {
        return mark >= 50;
    }

}
