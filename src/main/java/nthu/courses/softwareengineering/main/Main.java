package nthu.courses.softwareengineering.main;

import nthu.courses.softwareengineering.gradesystem.GradeSystem;
import nthu.courses.softwareengineering.gradesystem.GradeSystemRuntimeException;

public class Main {
    public static void main(String args[]) {
    	try {
			GradeSystem gs = new GradeSystem("gradeinput.txt");
            
			gs.run();
			
		} catch (GradeSystemRuntimeException e) {
			e.printStackTrace();
		}
		
    }
}
