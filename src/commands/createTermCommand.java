package commands;
import containers.termAccess;
/**
 * The command to create and initialize the one instance of Term.
 */
public class createTermCommand extends commandStatus{
    /**
     * Create and initialize the one instance of a term.
     *
     * @param name the name of the term
     */
    public void createTerm(String name) {
        if (name == null || name.equals("")) {
            successful = false;
            errorMessage = "The name of a term cannot be null or empty.  " + "It is " + name;
            return;
        }


        try {
            termAccess.initialize(name);
            successful = true;
        } catch (RuntimeException e) {
            successful = false;
            errorMessage = e.getMessage();
        }
    }
}
