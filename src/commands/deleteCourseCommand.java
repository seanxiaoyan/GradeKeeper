package commands;
import entities.Course;
import containers.courseTree;
import lib280.tree.OrderedSimpleTree280;

public class deleteCourseCommand extends commandStatus {

    /**
     * delete course in the data structure specified by course name
     * @param name course name
     */
    public void deleteCourse (String name,int grade,int creditUnints){
        Course cToDelete = new Course(name);
        cToDelete.setGrade(grade);
        cToDelete.setCredit(creditUnints);
        if(courseTree.tree().has(cToDelete)){
            courseTree.tree().search(cToDelete);
            courseTree.tree().deleteItem();
            successful=true;
        }
        else{
            successful=false;
            errorMessage="course not found, deletion failed";
        }
    }

}
