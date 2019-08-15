package entities;

public class basicCourse{
    private String name;
    public basicCourse(String name) {
        if (name != null && !name.equals("")) {
            this.name = name;
        } else {
            throw new RuntimeException("The name of a doctor cannot be null or empty.  It is " + name);
        }
    }
    public String getName() {
        return this.name;
    }
    public String toString() {
        return this.name + "\n";
    }

    //simple testing
    public static void main(String[] args) {
        int numErrors = 0;
        basicCourse c = new basicCourse("CMPT214");
        System.out.println("Basic course is " + c + "\n");
        if (!c.getName().equals("CMPT214")) {
            System.out.println("The constructor or getName failed");
            ++numErrors;
        }

        c = new basicCourse("CMPT215");
        System.out.println("Basic course is " + c + "\n");
        if (!c.getName().equals("CMPT215")) {
            System.out.println("The constructor or getName failed");
            ++numErrors;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
