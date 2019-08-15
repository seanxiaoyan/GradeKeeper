package containers;
import entities.term;
/**
 * A singleton class to access the one term.
 */
public class termAccess {
    /**
     * Private constructor to ensure that no instance this class is created.
     */
    private termAccess() {}

    /** The one instance of term. */
    private static term term;

    /**
     *
     * @param name: the name of term
     */
    public static void initialize(String name) {
        if (name == null || name.equals(""))
            throw new RuntimeException("The name of a term cannot be null or empty.  " + "It is "
                    + name);
        /**
         * if the term already exist, then throw exception
         */
        if (term != null)
            throw new RuntimeException("Initialize should only be invoked once.");
        /**
         * if the term not exist yet, create it
         */
        term = new term(name);
    }
    /**
     * Return the term.
     *
     * @precond ward != null // i.e., initialize has already been successfully invoked
     * @return the ward
     */
    public static term term() {
        if (term == null)
            throw new RuntimeException("The ward must be previously initialized"
                    + " before it can be accessed.");

        return term;
    }
}
