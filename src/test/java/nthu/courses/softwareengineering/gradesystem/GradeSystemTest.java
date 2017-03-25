package nthu.courses.softwareengineering.gradesystem;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nthu.courses.softwareengineering.gradesystem.GradeSystem;

public class GradeSystemTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test run()
	 * case1 : Q -> exit system
	 * case2 : 962001051 -> G -> R -> E -> Q
	 * case3 : 962001051 -> R -> A -> E -> Q
	 * case4 : 962001051 -> A -> W -> E -> Q
	 * case5 : 962001051 -> W -> G -> E -> Q
	 * case6 : 962001051 -> E -> Q
	 * case7 : -62001051 -> Q
	 * case8 : 962001051 -> H -> G -> E -> Q
	 */
	@Test
	public void testRun() {
		
		try {
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			
			byte s[] = {'Q', '\n'};
			InputStream in = new ByteArrayInputStream(s);
			System.setIn(in);
            
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			System.getProperty("line.separator").toCharArray();
			byte s[] = {'9', '6', '2', '0', '0', '1', '0', '5', '1', 13,
					    'G', 13, 
					    'R', 13, 
					    'E', 13, 
					    'Q', 13};
			InputStream in = new ByteArrayInputStream(s);
			System.setIn(in);
            
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			
			byte s[] = {'9', '6', '2', '0', '0', '1', '0', '5', '1', '\n',
					    'R', '\n', 'A', '\n', 'E', '\n', 'Q', '\n'};
			InputStream in = new ByteArrayInputStream(s);
			System.setIn(in);
            
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			
			byte s[] = {'9', '6', '2', '0', '0', '1', '0', '5', '1', '\n',
					    'A', '\n', 'W', '\n', 'E', '\n', 'Q', '\n'};
			InputStream in = new ByteArrayInputStream(s);
			System.setIn(in);
            
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			
			byte s[] = {'9', '6', '2', '0', '0', '1', '0', '5', '1', '\n',
					    'W', '\n', 'G', '\n', 'E', '\n', 'Q', '\n'};
			InputStream in = new ByteArrayInputStream(s);
			System.setIn(in);
            
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			
			byte s[] = {'9', '6', '2', '0', '0', '1', '0', '5', '1', '\n',
					    'E', '\n', 'Q', '\n'};
			InputStream in = new ByteArrayInputStream(s);
			System.setIn(in);
            
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			
			byte s[] = {'-', '6', '2', '0', '0', '1', '0', '5', '1', '\n',
					    'Q', '\n'};
			InputStream in = new ByteArrayInputStream(s);
			System.setIn(in);
            
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			
			byte s[] = {'9', '6', '2', '0', '0', '1', '0', '5', '1', '\n',
					    'H', '\n', 'G', '\n', 'E', '\n', 'Q', '\n'};
			InputStream in = new ByteArrayInputStream(s);
			System.setIn(in);
            
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
