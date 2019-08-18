package commands;
import entities.Course;
import lib280.tree.OrderedSimpleTree280;

/**
 * Command to obtain a String representation of the current state of the system. The current state
 * is placed in the curState field.
 */
public class currentStateCommand extends commandStatus {
    /**
     * A string containing the current state of the system.
     */
    private String curState="";

    /**
     * the revers of in-order obtainTotal to obtain all course objects stored in the tree, from highest grade to lowest grade.
     * @param T Binary search tree that contains course objects
     */
    public void traversal(OrderedSimpleTree280<Course> T) {

        if(!T.isEmpty()){ // check if the tree is empty first.
            if(!T.rootRightSubtree().isEmpty()){
            traversal(T.rootRightSubtree());}
            curState+=(T.rootItem().getName()+" "+T.rootItem().getGrade()+" "+T.rootItem().getCredit()+"\n");
            if(!T.rootLeftSubtree().isEmpty()){
            traversal(T.rootLeftSubtree());}
            successful = true;
        }
        else{
            successful=false;// tree is empty, cannot obtain course
            errorMessage="There is no course in the system"; // give message
        }
    }

    /**
     * Return a string containing the state of the system.
     *
     * @precond wasSuccessful()
     * @return a string containing the state of the system
     */
    public String getCurState() {
        return curState;
    }
}
