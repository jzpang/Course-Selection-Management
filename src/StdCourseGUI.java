import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * A GUI for student course selection management system.
 * @author jzpang
 */

public class StdCourseGUI {
	
	public static List<FinalData> students = new ArrayList<FinalData>();
	
	public StdCourseGUI() {
        JFrame frame = new JFrame("Student Course Selection Management");
        frame.setSize(680, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();

        //build control buttons and textfields.

        JLabel year = new JLabel("Year");
        JTextField yearArea = new JTextField(8);
        int initYEAR = Calendar.getInstance().get(Calendar.YEAR);
        yearArea.setText(Integer.toString(initYEAR));
        JLabel semester = new JLabel("Semester");
        JTextField semesterArea = new JTextField(8);

        int month = Calendar.getInstance().get(Calendar.MONTH);
        String strMonth=null;
        switch (month){
        case 1 :
        	strMonth = "Spring";
        	break;
        case 2 :
        	strMonth = "Spring";
        	break;
        case 3 :
        	strMonth = "Spring";
        	break;
        case 4 :
        	strMonth = "Spring";
        	break; 	
        case 5:
        	strMonth ="Summer";
        	break;
        case 6:
        	strMonth ="Summer";
        	break;
        case 7:
        	strMonth ="Summer";
        	break;
        case 8:
        	strMonth ="Summer";
        	break;
        case 9:
        	strMonth = "Fall";
        	break;
        case 10:
        	strMonth = "Fall";
        	break;
        case 11:
        	strMonth = "Fall";
        	break;
        case 12:
        	strMonth = "Fall";
        	break;
        }
        semesterArea.setText(strMonth);
        
        JLabel courseName = new JLabel("Course Name");
        JTextField courseNameArea = new JTextField(20);
        JLabel units = new JLabel("Units");
        JTextField unitsArea = new JTextField(8);
        JLabel grade = new JLabel("Grade");
        JTextField gradeArea = new JTextField(8);
        
        JButton addGrade = new JButton("Add Grade");
        JLabel avgGrade = new JLabel("Grade Point Average");
        JTextField avgGradeArea = new JTextField(8);
        avgGradeArea.setEditable(false);
        
        JTextArea errorMsgArea = new JTextArea(1,40);
        errorMsgArea.setEditable(false);
        
        JTextArea resultArea = new JTextArea(15, 50);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);

        
        addGrade.addActionListener(new GradeActionListener(
               yearArea, semesterArea, courseNameArea, unitsArea, gradeArea,avgGradeArea, errorMsgArea, resultArea ));
        JScrollPane scroller = new JScrollPane(resultArea);
        pane.add(year);
        pane.add(yearArea);
        pane.add(semester);
        pane.add(semesterArea);
        pane.add(courseName);
        pane.add(courseNameArea);
        pane.add(units);
        pane.add(unitsArea);
        pane.add(addGrade);
        pane.add(grade);
        pane.add(gradeArea);
        pane.add(avgGrade);
        pane.add(avgGradeArea);
        pane.add(errorMsgArea);
        pane.add(scroller);

        
        frame.setContentPane(pane);
        frame.setVisible(true);
    }
	
	
	private class GradeActionListener implements ActionListener {
		private JTextField yearArea;
		private JTextField semesterArea;
		private JTextField courseNameArea;
		private JTextField unitsArea;
		private JTextField gradeArea;
		private JTextField avgArea;
		private JTextArea errorArea;
		private JTextArea resultArea;
		private int year;
		private String semester;
		private String courseName;
		private int units;
		private String grade;

		
		GradeActionListener(JTextField t1, JTextField t2, JTextField t3, JTextField t4, JTextField t5, JTextField t6,JTextArea t7, JTextArea t8){
			//System.out.println(last.getText());
			yearArea =t1;
			semesterArea =t2;
			courseNameArea =t3;
			unitsArea =t4;
			gradeArea =t5;
			avgArea =t6;
			errorArea =t7;
			resultArea =t8;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			errorArea.setText("");

			if (yearArea.getText().matches("^\\d\\d\\d\\d$")){
				if ((Integer.parseInt(yearArea.getText())< 2000) || (Integer.parseInt(yearArea.getText()) > Calendar.getInstance().get(Calendar.YEAR))){
					errorArea.append("Invalid Year;");
				} else{
					year = Integer.parseInt(yearArea.getText());
				}
			} else{
				errorArea.append("Invalid Year; ");
			}
			
			if (semesterArea.getText().equals("Spring") || semesterArea.getText().equals("Summer")|| semesterArea.getText().equals("Fall")){
				semester = semesterArea.getText();
			} else{
				errorArea.append("Invalid semester; ");
			}
			if (courseNameArea.getText().equals("")){
				errorArea.append("Course Name is required; ");
			} else {
				if (courseNameArea.getText().matches("^\\d\\d-\\d\\d\\d.*")){
			    	courseName = courseNameArea.getText();
			    }else {
			    	errorArea.append("Course Name is not valid; ");
			    }
			}
			if (unitsArea.getText().equals("")){
				errorArea.append("Units is required; ");
			} else {
				if ((unitsArea.getText().matches("^\\d\\d?$")) &&( 1<Integer.parseInt(unitsArea.getText())) && ( Integer.parseInt(unitsArea.getText()) <99 )){
					units= Integer.parseInt(unitsArea.getText());
				} else{
					errorArea.append("Units is invalid; ");
				}
			}
			if (gradeArea.getText().equals("A") || gradeArea.getText().equals("B")|| gradeArea.getText().equals("C") || gradeArea.getText().equals("D") || gradeArea.getText().equals("F")){
				grade = gradeArea.getText();
			} else{
				errorArea.append("Invalid grade; ");
			}
			

			if (errorArea.getText().length()>0){
				//errorArea.setText(errorMsg.toString());
			} else{
				errorArea.setText("");
				FinalData data = new FinalData(year, semester, courseName, units, grade);
				students.add(data);
			}
			
			resultArea.setText("");
			
			float sumGrades=0f;
			int sumUnits =0;
			
			
			resultArea.append(String.format("%-15s %-15s %-40s %4s %10s %10s\n", "Year", "Semester", "Course", "Units", "Grade", "Points"));
			for (FinalData d: students){
				resultArea.append(d.toString());
				sumGrades += d.getPoints();
				sumUnits +=d.getUnits();
			}
			float avgPoints=0f;
			if (sumUnits !=0){
				avgPoints = sumGrades/ sumUnits; 
			}
			
			avgArea.setText(String.valueOf(avgPoints));
			courseNameArea.setText("");
			unitsArea.setText("");
			gradeArea.setText("");		
			
		}
	}
	
	
	public static void main(String[] args) {
		new StdCourseGUI();
	}
}
