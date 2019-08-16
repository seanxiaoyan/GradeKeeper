package commands;
import java.io.*;

public class readInputCourseCommand extends commandStatus {
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
                        if(line.charAt(i)==' '){
                            if(nameIndexEnd==0){
                                nameIndexEnd=i;
                            }
                            else{gradeIndexEnd=i;}
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