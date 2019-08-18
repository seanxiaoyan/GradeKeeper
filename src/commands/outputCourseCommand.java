package commands;
import containers.courseTree;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * obtain all the courses in the system and write them to a text file
 */
public class outputCourseCommand extends commandStatus{
    public void outputCourse (String targetPath){
        try { // try and catch
            File writename = new File(targetPath); // relative path
            writename.createNewFile(); // create new file
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            currentStateCommand state = new currentStateCommand();

            //obtain the string that is going to be written into the file
            state.traversal(courseTree.tree());
            out.write(state.getCurState()); // here, the string is the result of .getCurState()

            out.flush();
            out.close();
            successful=true;

        } catch (Exception e) {
            successful=false;
            errorMessage= e.getMessage();
        }
    }
    public static void main(String[] args) {
        //simple test
        outputCourseCommand out = new outputCourseCommand();
        out.outputCourse("C:\\Users\\51676\\GradeKeeper\\output.txt");
    }
}

