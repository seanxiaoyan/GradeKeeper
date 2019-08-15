package containers;

import entities.Course;

import java.util.HashMap;

/**
 * A singleton class to access the dictionary containing the course.
 */
public class courseSetAccess {
    /**
     * Private constructor to ensure that no instance of this class is created.
     */
    private courseSetAccess() {}
    /** The dictionary for course. */
    private static HashMap<String, Course> dictionary;
    /**
     * Return the dictionary that maps name to course.
     *
     * @return the dictionary that maps course name to course
     */
    public static HashMap<String, Course> dictionary() {
        if (dictionary == null) {
            /* Create the initial dictionary. */
            dictionary = new HashMap<String, Course>();
        }
        return dictionary;
    }
}
