package commands;
import entities.Course;
import containers.courseSetAccess;
public class addCourseCommand extends commandStatus{
    /**
     * Create a course with the specified name, and add the course into the system.
     *
     * @param name: course name
     */
    public void addCourse (String name,int grade){
        if (courseSetAccess.dictionary().containsKey(name)) {
            successful=false;
            errorMessage =
                    "course not added as there already " + "is a course with the name " + name;
        }
        else{
            Course course = null;
            try {
                course = new Course(name);
                course.setGrade(grade);
            } catch (RuntimeException e) {
                successful = false;
                errorMessage = e.getMessage();
                return;
            }
            Course sameNameCourse = courseSetAccess.dictionary().put(name, course);
            if (sameNameCourse != null) {
                // put the original doctor back
                courseSetAccess.dictionary().put(name, sameNameCourse);
                successful = false;
                errorMessage =
                        "The name is in the course dictionary even though "
                                + "containsKey failed.  Name " + name + " not entered.";
            } else
                successful = true;
        }

    }
}
