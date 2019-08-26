package commands;
import entities.Course;
import containers.courseTree;
import lib280.tree.OrderedSimpleTree280;
import lib280.exception.*;

public class addCourseCommand extends commandStatus{
    /**
     * private boolean field, used to check if the course already exist.
     */
    private boolean isDuplicate=false;


    /**
     * Method addCourse: Create a course with its name. grade and credit units. Then add the course into the system.
     * @param name: name of course
     * @param grade: grade of course
     * @param credit: credit of course
     */
    public void addCourse (String name,int grade,int credit){
        try {
        Course c = new Course(name); // create a new course object
        c.setGrade(grade);
        c.setCredit(credit);

        //since .has() return true as long as the grade is the same, we need double check
        if(courseTree.tree().has(c)){//check if there is a class with the same grade\
            courseTree.tree().search(c);//move the cursor to that item position
            if(courseTree.tree().item().getName().equals(name)){// if the class' name is also the same
                successful = false;// false to add course, command was not successful
                errorMessage = "Cannot add this course: "+name+" ,since it is already in the system";
            }
            courseTree.tree().insert(c);//insert the course into the tree
            successful=true; // command was successful
        }
        else{// course is not yet in the system
        courseTree.tree().insert(c);//insert the course into the tree
        successful=true;} // command was successful
        }
        catch (InvalidArgument280Exception e){successful=false; // command was not successful
        errorMessage=e.getMessage();}
    }

}
