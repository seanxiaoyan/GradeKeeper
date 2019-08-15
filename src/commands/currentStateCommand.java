package commands;

import containers.courseSetAccess;

import containers.termAccess;

import java.util.Collection;
import entities.Course;
import entities.term;

/**
 * Command to obtain a String representation of the current state of the system. The current state
 * is placed in the curState field.
 */
public class currentStateCommand extends commandStatus {
    /**
     * A string containing the current state of the system.
     */
    private String curState;

    /**
     * Obtain the current state of the system and place the string in curState.
     */
    public void findCurState() {
        curState = "\nThe course in the system are \n";
        Collection<Course> course = courseSetAccess.dictionary().values();
        for (Course p : course)
            curState = curState + p;
        curState = curState + "\nThe doctors in the system are \n";

        curState = curState + "\nThe Term is " + termAccess.term();
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
