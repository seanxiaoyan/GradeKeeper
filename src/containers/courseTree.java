package containers;
import entities.Course;
import lib280.tree.AVLTree280;
public class courseTree {
    /**
     * Private constructor to ensure that no instance of this class is created.
     */
    private courseTree() {}

    /** The dictionary for doctors. */
    private static AVLTree280<Course> tree;

    /**
     * Return the dictionary that maps names to doctors.
     *
     * @return the dictionary that maps names to doctors
     */
    public static AVLTree280<Course> tree() {
        if (tree == null) {
            /* Create the initial dictionary. */
            tree = new AVLTree280<>();
        }
        return tree;
    }
}
