package entities;


public class Course extends basicCourse implements Comparable<Course> {
    private int grade;
    private int credit;
    //constructor, input is an integer

    public Course(String name) {
        super(name);
    }

    public int getGrade() { return this.grade; }

    public void setGrade(int g){ this.grade = g; }

    public int getCredit() { return this.credit; }

    public void setCredit(int g) { this.credit = g; }

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
