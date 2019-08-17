package commands;
import containers.courseTree;

import java.io.File;

import java.io.BufferedWriter;

import java.io.FileWriter;

/**
 *  write output to file
 */
public class outputCourseCommand extends commandStatus{
    public void outputCourse (String targetPath){
        try { // try and catch
            File writename = new File(targetPath); // relative path, create new file if output.txt not exist
            writename.createNewFile(); // create new file
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            currentStateCommand state = new currentStateCommand();
            state.traversal(courseTree.tree());
            out.write(state.getCurState());

            out.flush(); //
            out.close(); //
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

