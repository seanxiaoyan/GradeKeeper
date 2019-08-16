package commands;
import entities.Course;
import containers.courseTree;
import lib280.tree.OrderedSimpleTree280;

public class deleteCourseCommand extends commandStatus {

    /**
     * delete course in the data structure specified by course name
     * @param name course name
     */
    public void deleteCourse (String name){
        searchByName(courseTree.tree(),name);
        if (!successful){
            errorMessage="course not found, the course is not in the system";
        }
    }

    /**
     *
     * @param T binary search tree
     * @param n name of course
     */
    public void searchByName(OrderedSimpleTree280<Course> T, String n){
        if(T.isEmpty()){
            successful=false;
        }
        else{
        if (!T.rootRightSubtree().isEmpty()) {
            searchByName(T.rootRightSubtree(), n);
        }
        if (T.rootItem().getName().equals(n)) {
            T.deleteItem();
            successful=true;
        }
        if (!T.rootLeftSubtree().isEmpty()) {
            searchByName(T.rootLeftSubtree(), n);
        }
    }}
}
