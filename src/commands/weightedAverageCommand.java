package commands;
import entities.Course;
import lib280.tree.OrderedSimpleTree280;
import java.math.*;

/**
 * calculate current weighted average
 */
public class weightedAverageCommand extends commandStatus{
    private double totalWeightedMarks;
    private double totalCreditUnits;
    public double weightAvg;

    /**
     * In-order traversal to get total weighted marks
     * @param T binary search tree that stores course object
     */
    public void traversal(OrderedSimpleTree280<Course> T){
        if(!T.isEmpty()){
            if(!T.rootLeftSubtree().isEmpty()){
                traversal(T.rootLeftSubtree());}
            this.totalWeightedMarks+=T.rootItem().getGrade()*T.rootItem().getCredit();//weightedMarks = grade * credit Units
            this.totalCreditUnits+=T.rootItem().getCredit();
            if(!T.rootRightSubtree().isEmpty()){
                traversal(T.rootRightSubtree());}
        }
        successful = true;
    }
    public void getAverage(OrderedSimpleTree280<Course>T){
        this.traversal(T);
        if(this.totalCreditUnits==0){
            successful=false;
            errorMessage="Cannot get average since there is no course in the system yet";
        }
        else{
            successful=true;
            weightAvg= round(this.totalWeightedMarks/this.totalCreditUnits,2);}
    }

    /**
     * convert a double value to specific decimal places
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
