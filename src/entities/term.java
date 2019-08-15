package entities;

import lib280.tree.AVLNode280;
import lib280.tree.AVLTree280;

public class term {
    private String name;
    private AVLTree280<Course> courseInTerm = new AVLTree280<>();

    //constructor
    public term (String tName){
        if (tName != null && !tName.equals("")) {
            this.name = tName;
        } else {
            throw new RuntimeException("The name of a term cannot be null or empty. " +
                    "The following name is invalid " + tName);
        }
    }

    // method to get term name
    public String getName() {
        return this.name;
    }
    public String toString(){
        return courseInTerm.toStringByLevel();
    }
    public void addCourse(Course course){
        courseInTerm.insert(course);
    }
    public static void main(String[] args) {
        Course cmpt214 = new Course("CMPT214");
        cmpt214.setGrade(74);
        Course ENG114 = new Course("ENG114");
        ENG114.setGrade(85);
        Course cmpt215 = new Course("CMPT215");
        cmpt215.setGrade(77);
        Course cmpt260 = new Course("CMPT260");
        cmpt260.setGrade(94);
        Course cmpt270 = new Course("CMPT270");
        cmpt270.setGrade(83);
        Course cmpt280 = new Course("CMPT280");
        cmpt280.setGrade(86);
        term T1 = new term("18-19T1");
        T1.addCourse(cmpt214);
        T1.addCourse(cmpt215);
        T1.addCourse(cmpt260);
        T1.addCourse(cmpt270);
        T1.addCourse(cmpt280);
        T1.addCourse(ENG114);
        System.out.println(T1.toString());
    }

}
