package commands;
import java.io.*;

public class readInputCourseCommand extends commandStatus {

    /**
     * read the course from input file.
     * precondition: in the input text file, this format has to be followed: one course per line, leaving only one space
     * between course name and grade, one space between grade and credit units.
     * notice that the format of the systems' output file (from outputCourse Command) strictly follows the rule.
     *
     * @param givenPath the path of the file
     */
    public void readinputCourse(String givenPath) {
        /* read text file */

        try {
            FileReader reader = new FileReader(givenPath);
            BufferedReader br = new BufferedReader(reader); // convert to buffered reader

            String line;
            while ((line = br.readLine()) != null) {
                // one Line per time
                // since there are 2 spaces separate name, grade, and creditUnit, need to obtain the index of these 2 spaces
                int firstSpaceIndex=0;int secondSpaceIndex=0;
                for (int i=0;i<line.length();i++){
                        if(line.charAt(i)==' '){//through iteration, when hit the space char
                            if(firstSpaceIndex==0){//check if it is first space, if nameIndexEnd is still 0, it is first space
                                firstSpaceIndex=i;// assign nameIndexEnd to the index of first space
                            }
                            else{secondSpaceIndex=i;}//assign gradeIndexEnd to the second space
                        }
                }

                //get name, grade and credit units of one course
                String name=line.substring(0,firstSpaceIndex);
                String gradeString = line.substring(firstSpaceIndex+1,secondSpaceIndex);
                String creditString= line.substring(secondSpaceIndex+1,line.length());

                int grade = Integer.parseInt(gradeString);
                int credit = Integer.parseInt(creditString);
                //check the validity of the grade
                if(grade<0 || grade >100){ // if grade is invalid
                    successful=false;
                    errorMessage+="invalid grade of the course "+name+"\n";
                }
                //check the validity of the credit units
                else if(credit!=3&&credit!=6) { // if credit units is invalid
                    successful=false;
                    errorMessage+="invalid credit units of the course"+credit+"\n";
                }
                else{ // if grade and credit units are valid, add the course
                    addCourseCommand addCourse = new addCourseCommand();
                    addCourse.addCourse(name, grade,credit);
                    if(!addCourse.wasSuccessful()){ // if addition was unsuccessful
                        errorMessage=addCourse.getErrorMessage();
                        successful=false;
                    }
                    else { // if course was added successfully
                        successful=true;
                        System.out.println("Course "+name+" was successfully added");
                    }
                    }
            }
        } catch (IOException e) {
            successful=false;
            errorMessage=e.getMessage();
        } catch (StringIndexOutOfBoundsException e){
            successful=false;
            errorMessage="invalid input";
        }
    }
    public static void main(String[] args) {
        //simple test
        readInputCourseCommand in = new readInputCourseCommand();
        in.readinputCourse("output.txt");
    }
}