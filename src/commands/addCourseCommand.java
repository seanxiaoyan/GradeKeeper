package commands;
import entities.Course;
import containers.courseTree;
import lib280.tree.OrderedSimpleTree280;
import lib280.exception.*;
import lib280.list.*;
public class addCourseCommand extends commandStatus{
    /**
     * private boolean field, used to check if the course already exist.
     */
    private boolean isDuplicate=false;

    /**
     *   private field
     *   a list to store all the current courses name, use it
     *   to check duplicate name when adding course
     */
    private LinkedList280<String> nameList = new LinkedList280<String>();

    /**
     * tree traversal to get all the name of the courses that are currently stored in the tree
     * @param T a binary search tree which stores Course objects
     */
    public void traversal(OrderedSimpleTree280<Course> T) {

        if(!T.isEmpty()){ // check if the tree is empty first.
            if(!T.rootRightSubtree().isEmpty()){
                traversal(T.rootRightSubtree());
            }

            nameList.insert(T.rootItem().getName());

            if(!T.rootLeftSubtree().isEmpty()){
                traversal(T.rootLeftSubtree());}
             }
    }
    /**
     * Method addCourse: Create a course with its name. grade and credit units. Then add the course into the system.
     * @param name: name of course
     * @param grade: grade of course
     * @param credit: credit of course
     */
    public void addCourse (String name,int grade,int credit){
        //Check the name of the course first. If the name already exists, means the course is already in the system
        traversal(courseTree.tree());

        if(nameList.has(name)){

            successful = false;// false to add course, command was not successful
            errorMessage = "Cannot add this course: "+name+" ,since it is already in the system";
        }
        else{
            try {
                Course c = new Course(name); // create a new course object
                c.setGrade(grade);
                c.setCredit(credit);
                courseTree.tree().insert(c);//insert the course into the tree
                successful = true; // command was successful
            }
            catch (InvalidArgument280Exception e){successful=false; // command was not successful
            errorMessage=e.getMessage();}
        }
    }

}
