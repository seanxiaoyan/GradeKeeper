package startup;
import userInterfaces.InputOutputInterface;
import userInterfaces.ConsoleIO;
import userInterfaces.DialogIO;
import commands.addCourseCommand;
import commands.currentStateCommand;
import commands.outputCourseCommand;
import containers.courseTree;
import commands.deleteCourseCommand;
import commands.readInputCourseCommand;
import commands.weightedAverageCommand;

/**
 * This is the main class for the Grade Keeper Application
 */
public class gradeKeeper {
    /**
     * The interface to be used to read input from the user and output results to the user.
     */
    private InputOutputInterface ioInterface;
    /**
     * Initialize the system by creating the interface for I/O.
     */
    public void initialize() {
        boolean ask = true;
        do{
            try{
                ioInterface = new DialogIO();
                String option = ioInterface.readString("Should dialog boxes be used for I/O?\n " +
                        "(Enter Y for dialog boxes, N for console IO) ");
                if (option != null){
                    if (option.charAt(0) == 'N' || option.charAt(0) == 'n') {
                        ioInterface = new ConsoleIO();
                        ask=false;
                    }
                    else if(option.charAt(0)=='Y' || option.charAt(0)=='y'){
                        ask=false;
                    }
                }
                else{System.exit(0);}
            }
            catch (StringIndexOutOfBoundsException e){
                ioInterface.outputString("Invalid input");
            }
        }
        while (ask);}

    /**
     * Output the prompt that lists the possible tasks, and read the selection chosen by the user.
     *
     * @return the int corresponding to the task selected
     */
    public int readOpId() {
        String[] taskChoices =
                new String[] {"Quit", "Add a new course", "Delete a course","Display all courses in the System","Get " +
                        "weighted average","" +"Save current status (all courses) to a file",
                        "Read courses from a file"};

        return ioInterface.readChoice(taskChoices);
    }
    /**
     * Run the hospital system: initialize, and then accept and carry out operations. Output the
     * patient and doctor dictionaries, and the ward when finishing.
     */
    public void run() {
        initialize();
        int opId = readOpId();
        while (opId != 0) {
            switch (opId) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    deleteCourse();
                    break;
                case 3:
                    displayAllCourseInSys();
                    break;
                case 4:
                    getWeightedAverage();
                    break;
                case 5:
                    outputCourse();
                    break;
                case 6:
                    readInput();
                    break;
                default:
                    ioInterface.outputString("Invalid int value; try again\n");
            }

            opId = readOpId();
        }

        ioInterface.outputString("GradeKeeper Terminated\nSee you again!");
        System.exit(0);
    }

    /**
     * list all courses in the system. When there is no course yet, display message
     */
    public void displayAllCourseInSys() {
        currentStateCommand state = new currentStateCommand();
        state.traversal(courseTree.tree());
        if(state.wasSuccessful()){
        ioInterface.outputString( "Courses(name grade credit) are sorted by grade:\n"+state.getCurState() + "\n");}
        else{
            ioInterface.outputString(state.getErrorMessage());
        }
    }
    public void addCourse() {
        String name = ioInterface.readString("Enter the name of the course: ");
        int grade;
        int credit;
        boolean gradeCheck;

        do {// do-while check the grade
            gradeCheck = true;
            grade = ioInterface.readInt("Enter the grade of this course: ");
            if(grade<0 || grade >100){
                gradeCheck=false;
                ioInterface.outputString("invalid grade"
                    + "\nPlease try again: \n");}
            }
            while (!gradeCheck);


            boolean creditCheck;
            do {// do-while check the credit units
            credit = ioInterface.readInt("Enter the credit units of this course: ");
            if(credit==3||credit==6){ creditCheck=true; }
            else{creditCheck=false;
                ioInterface.outputString("invalid credit units, credit units has to be 3 or 6."
                        + "\nPlease try again: \n");}
            }
            while (!creditCheck);

        addCourseCommand addCourse = new addCourseCommand();
        addCourse.addCourse(name, grade,credit);
        if (!addCourse.wasSuccessful())
            ioInterface.outputString(addCourse.getErrorMessage() + "\n");
    }
    public void deleteCourse(){
        //read input
        String name = ioInterface.readString("Enter the name of the course you wish to delete: ");
        int grade =ioInterface.readInt("Enter the grade of this course: ");
        int credit = ioInterface.readInt("Enter the credit units of this course: ");
        deleteCourseCommand delete= new deleteCourseCommand();
        //delete
        delete.deleteCourse(name,grade,credit);
        if(!delete.wasSuccessful()){
            ioInterface.outputString(delete.getErrorMessage() + "\n");
        }
        else{
            ioInterface.outputString( "course "+name+" was successfully deleted! \n");
        }
    }
    public void getWeightedAverage(){
        weightedAverageCommand weightedAverage= new weightedAverageCommand();
        weightedAverage.getAverage(courseTree.tree());
        if(weightedAverage.wasSuccessful()){
            ioInterface.outputString("The weighted average is:  "+weightedAverage.weightAvg+"%");}
        else{
            ioInterface.outputString(weightedAverage.getErrorMessage());
        }
    }
    public void outputCourse() {

        outputCourseCommand outputCourse = new outputCourseCommand();//create new outputCourseCommand object

        //decide target path. default is current path of GradeKeeper
        String finalPath="";
        boolean ask =true;
        do{
        String answer = ioInterface.readString("Would you like write to default path? Y/N: ");
        if(answer!=null){
        if (answer.equals("Y")||answer.equals("y")){   //check the answer
            outputCourse.outputCourse("output.txt");
            finalPath="GradeKeeper\\output.txt";
            ask=false;
        }
        else if (answer.equals("N")||answer.equals("n")){
            String path = ioInterface.readString("Enter the path: ");
            outputCourse.outputCourse(path);
            finalPath=path;
            ask=false;
        }
        else{ ioInterface.outputString("You entered invalid path, please try again"); }
        }
        else{
            ask=false;
        }}

        while(ask);


        if (!outputCourse.wasSuccessful()){
            ioInterface.outputString(outputCourse.getErrorMessage() + "\n");
        }
        else{
            ioInterface.outputString("courses are successfully saved to: "+finalPath);
        }

    }

    public void readInput(){
        readInputCourseCommand read = new readInputCourseCommand();
        boolean ask =true;

        do{
            String answer = ioInterface.readString("Would you like read from default path? Y/N: ");
            if(answer!=null){
            if (answer.equals("Y")||answer.equals("y")){   //check the answer
                read.readinputCourse("output.txt");
                ask=false;
            }
            else if (answer.equals("N")||answer.equals("n")){
                String path = ioInterface.readString("Enter the path: ");
                read.readinputCourse(path);
                ask=false;
            }
            else{ioInterface.outputString("You entered invalid path, please try again");}
            }
            else{
                ask=false;
            }
        }
        while(ask);
        if(!read.wasSuccessful()){
            ioInterface.outputString(read.getErrorMessage() + "\n");
        }
        else{
            ioInterface.outputString( "course were added successfully!\n");
        }
    }

    /**
     * Return a String that contains all the patients and doctors in the system.
     *
     * @return a String that contains all the patients and doctors in the system.
     */
    public String toString() {
        currentStateCommand state = new currentStateCommand();
        state.traversal(courseTree.tree());
        return "The system is as follows: " + state.getCurState() + "\n";
    }

    /**
     * Run the hospital system.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        gradeKeeper sys = new gradeKeeper();
        sys.run();
    }
}
