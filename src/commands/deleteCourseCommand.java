package commands;
import entities.Course;
import containers.courseTree;

public class deleteCourseCommand extends commandStatus {

    /**
     * Delete a course in the system
     * @param name name of course
     * @param grade grade of course
     * @param creditUnints credit Units of course
     */
    public void deleteCourse (String name,int grade,int creditUnints){
        // create a new course object
        Course cToDelete = new Course(name);
        cToDelete.setGrade(grade);
        cToDelete.setCredit(creditUnints);

        // check if this there is a same object in the tree
        if(courseTree.tree().has(cToDelete)){ // if there is a same object, delete it
            courseTree.tree().search(cToDelete);
            courseTree.tree().deleteItem();
            successful=true;
        }
        else{ // if there is not, give message and set boolean successful to false
            successful=false;
            errorMessage="course not found, deletion failed";
        }
    }

}
