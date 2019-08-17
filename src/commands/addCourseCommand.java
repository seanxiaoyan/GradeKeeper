package commands;
import lib280.exception.*;
import entities.Course;
import containers.courseTree;
import lib280.tree.OrderedSimpleTree280;

public class addCourseCommand extends commandStatus{
    private boolean isDuplicate=false;
    /**
     * Create a course with the specified name, and add the course into the system.
     *
     * @param name: course name
     */
    public void addCourse (String name,int grade,int credit){
        try {
        Course c = new Course(name);
        c.setGrade(grade);
        c.setCredit(credit);

        checkDupName(courseTree.tree(),name);
        if(this.isDuplicate){//isDuplicate is set to be True, means that the course is already in the system
            this.isDuplicate=false;// set isDuplicate back to false
            successful = false;// false to add course since the course needed to add is already in the system
            errorMessage = "Cannot add this course: "+name+" ,since it is already in the system";
        }
        else{// course is not yet in the system
        courseTree.tree().insert(c);//insert the course into the tree
        successful=true;}
        }
        catch (InvalidArgument280Exception e){successful=false;
        errorMessage=e.getMessage();}


    }

    /**
     * check if the course is already in the system
     * @param T
     * @param n
     */
    public void checkDupName(OrderedSimpleTree280<Course> T,String n){
        if(!T.isEmpty()) {
            if (!T.rootRightSubtree().isEmpty()) {
                checkDupName(T.rootRightSubtree(), n);
            }
            if (T.rootItem().getName().equals(n)) {
                this.isDuplicate = true;
            }
            if (!T.rootLeftSubtree().isEmpty()) {
                checkDupName(T.rootLeftSubtree(), n);
            }
        }
    }
}
