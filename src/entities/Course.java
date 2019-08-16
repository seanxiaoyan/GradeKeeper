package entities;

import lib280.exception.InvalidArgument280Exception;

public class Course extends basicCourse implements Comparable<Course> {
    private int grade;
    private int credit;
    //constructor, input is an integer

    public Course(String name) {
        super(name);
    }

    public int getGrade() {
        return this.grade;
    }
    public void setGrade(int g){
        if (g>0&&g<=100) {
            this.grade = g;
        } else {
            throw new InvalidArgument280Exception("The grade cannot be null or empty, and the grade must be positive " +
                    "and less or equal to 100.  This following grade is not acceptable " + g );
        }
    }

    public int getCredit() {
        return this.credit;
    }
    public void setCredit(int g){
        if (g ==3 || g==6) {
            this.credit = g;
        } else {
            throw new InvalidArgument280Exception("credit units invalid: " + g );
        }
    }
    public static void main(String[] args) {
        int numErrors = 0;
        Course g = new Course("CMPT214");
        g.setGrade(20);
        System.out.println("The grade of the course "+g.getName()+" is " + g.getGrade() + "\n");
        if (g.getGrade()!=20) {
            System.out.println("The constructor or getName failed");
            ++numErrors;
        }


        System.out.println("The number of errors found is " + numErrors);
    }


    @Override
    public int compareTo(Course course) {
        if (this.getGrade()==course.getGrade()){
            return 0;
        }
        else if (this.getGrade()>course.getGrade()){
            return 1;
        }
        else{
            return -1;
        }
    }
}
