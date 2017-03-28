import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Data Structure for user to manage student entries.
 * @author jingzhip
 */
public final class FinalData{
	
	
	private int year=0;
	private String semester;
	private String courseName;
	private int units=0;
	private String grade;
	private float points;

	
	public FinalData(int year, String semester, String courseName, int units, String grade) {
        if ((semester != null) && (courseName != null)) {
            this.year = year;
            this.semester= semester;
            this.courseName=courseName;
            this.units = units;
            this.grade = grade;
            //System.out.println(grade);
            if (grade.equals("A")){
    			this.points = (float) (4.0 * units);
    		}
    		if (grade.equals("B")){
    			this.points = (float) (3.0 * units);
    		}
    		if (grade.equals("C")){
    			this.points = (float) (2.0 * units);
    		}
    		if (grade.equals("D")){
    			this.points = (float) (1.0 * units);
    		}
    		if (grade.equals("E")){
    			this.points = (float) (0.0 * units);
    		}
            
            
        } else {
            throw new IllegalArgumentException("Cannot build up Student: You have to input year, semster and course name");
        }
    }
	
	
    /**
     * Returns andrewId of a Student object.
     * @return String andrewId
     */
    public int getYear() {
        return year;
    }
    
    public String getSemester(){
    	return semester;
    }
    
    public String getCourseName(){
    	return courseName;
    }
    
    public int getUnits(){
    	return units;
    }
    
    public String getGrade(){
    	return grade;
    }
    
    public float getPoints(){
    	return points;
    }

    
    
    
    /**
     * Returns String representation of Student object.
     * @return String representation of Student object.
     */
    @Override
    public String toString() {
    	
    	NumberFormat nf = new DecimalFormat("##.00");
		String sPoints = nf.format(this.points);
		return String.format("%-15d %-15s %-40s %4d %10s %10s\n", year, semester, courseName, units, grade, sPoints);
		}

}
