package commands;


import entities.Course;
import containers.courseTree;

import lib280.tree.AVLNode280;
import lib280.tree.AVLTree280;

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
     * Obtain the current state of the system and place the string in curState.
     * the course are listed by grades from highest to lowest
     */

    public void traversal(AVLTree280<Course> T) {


        if(!T.isEmpty()){
            if(!T.rootRightSubtree().isEmpty()){
            traversal(T.rootRightSubtree());}
            curState+=(T.rootItem().getName()+" "+T.rootItem().getGrade()+" "+T.rootItem().getCredit()+"\n");
            if(!T.rootLeftSubtree().isEmpty()){
            traversal(T.rootLeftSubtree());}
        }
        successful = true;

    }

    /**
     * Return a string containing the state of the system.
     *
     * @precond wasSuccessful()
     * @return a string containing the state of the system
     */
    public String getCurState() {
        if (!wasSuccessful())
            throw new RuntimeException("The method findCurState() must be "
                    + "invoked before this method");

        return curState;
    }
}
