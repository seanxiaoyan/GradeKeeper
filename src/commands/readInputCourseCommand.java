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
        /* 读入TXT文件 */

        try {
            FileReader reader = new FileReader(givenPath);
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            String line;

            while ((line = br.readLine()) != null) {
                // one Line per time
                //grab the following information in the line
                int nameIndexBeg=0;int nameIndexEnd=0;int gradeIndexEnd=0;
                int spaces=3;
                for (int i=0;i<line.length();i++){
                    // since there are 2 spaces separate name, grade, and creditUnit
                        if(line.charAt(i)==' '){//through iteration, when hit the space char
                            if(nameIndexEnd==0){//check if it is first space, if nameIndexEnd is still 0, it is first space
                                nameIndexEnd=i;// assign nameIndexEnd to the index of first space
                            }
                            else{gradeIndexEnd=i;}//assign gradeIndexEnd to the second space
                        }
                }
                System.out.println(nameIndexEnd+" "+gradeIndexEnd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //simple test
        readInputCourseCommand in = new readInputCourseCommand();
        in.readinputCourse("C:\\Users\\51676\\GradeKeeper\\output.txt");
    }
}