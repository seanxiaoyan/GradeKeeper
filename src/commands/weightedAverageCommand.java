package commands;
import entities.Course;
import lib280.tree.OrderedSimpleTree280;
import java.math.*;

/**
 * calculate current weighted average
 */
public class weightedAverageCommand extends commandStatus{
    private double totalWeightedMarks; // total weighted marks used for division
    private double totalCreditUnits; // total credit units used for division
    public double weightAvg; // weighted average, the result of division

    /**
     * In-order traversal to get total weighted marks and total credit units
     * @param T binary search tree that stores course object
     */
    public void obtainTotal(OrderedSimpleTree280<Course> T){

        // apply in-order obtainTotal to obtain the total weighted marks and the total credit units

        if(!T.isEmpty()){
            if(!T.rootLeftSubtree().isEmpty()){
                obtainTotal(T.rootLeftSubtree());}
            this.totalWeightedMarks+=T.rootItem().getGrade()*T.rootItem().getCredit();//weightedMarks = grade * credit Units
            this.totalCreditUnits+=T.rootItem().getCredit();
            if(!T.rootRightSubtree().isEmpty()){
                obtainTotal(T.rootRightSubtree());
            }
            successful = true;
        }
        else{
            successful=false;
            errorMessage="Cannot obtain weighted average since there is no course in the system";
        }
    }

    /**
     * Calculate weighted average and round the result to 2 decimal places
     * @param T Binary search tree stores the course objects
     */
    public void getAverage(OrderedSimpleTree280<Course>T){
        this.obtainTotal(T); // obtain total weighted average and total weighted marks
        if(successful){ //
            weightAvg= round(this.totalWeightedMarks/this.totalCreditUnits,2);}
    }

    /**
     * Method to convert a double value to specific decimal places
     * @param value value to be converted
     * @param places desired decimal places.
     * @return a double value rounded to desired decimal places
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public static void main(String[] args) {
       // simple test
        OrderedSimpleTree280<Course> t = new OrderedSimpleTree280<>();
        Course eng = new Course("Eng");
        eng.setGrade(73);
        eng.setCredit(6);
        Course dram = new Course("dram");
        dram.setGrade(67);
        dram.setCredit(6);
        Course phy = new Course("phy");
        phy.setGrade(68);
        phy.setCredit(6);
        Course chem = new Course("chem");
        chem.setGrade(73);
        chem.setCredit(3);
        Course mus = new Course("mus");
        mus.setGrade(71);
        mus.setCredit(3);
        Course hist = new Course("hist");
        hist.setGrade(69);
        hist.setCredit(3);
        Course geog = new Course("geog");
        geog.setGrade(74);
        geog.setCredit(3);
        t.insert(eng);
        t.insert(dram);
        t.insert(phy);
        t.insert(chem);
        t.insert(mus);
        t.insert(hist);
        t.insert(geog);
        weightedAverageCommand c = new weightedAverageCommand();
        c.getAverage(t);
    }
}
