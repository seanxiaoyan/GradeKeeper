package containers;
import entities.Course;
import lib280.tree.OrderedSimpleTree280;
public class courseTree {
    /**
     * Private constructor to ensure that no instance of this class is created.
     */
    private courseTree() {}

    /** The dictionary for doctors. */
    private static OrderedSimpleTree280<Course> tree;

    /**
     * Return the dictionary that maps names to doctors.
     *
     * @return the dictionary that maps names to doctors
     */
    public static OrderedSimpleTree280<Course> tree() {
        if (tree == null) {
            /* Create the initial dictionary. */
            tree = new OrderedSimpleTree280<>();
        }
        return tree;
    }
}
