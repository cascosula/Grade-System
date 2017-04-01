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
	 * case4 : 962001051 -> W -> 20 -> 20 -> 20 -> 20 -> 20 -> Y -> E -> Q
	 */
	@Test
	public void testRun() {
		
		try {
			System.setIn(ClassLoader.getSystemResourceAsStream("gradesystem_test0.txt"));
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			System.setIn(ClassLoader.getSystemResourceAsStream("gradesystem_test1.txt"));
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			System.setIn(ClassLoader.getSystemResourceAsStream("gradesystem_test2.txt"));
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try {
			System.setIn(ClassLoader.getSystemResourceAsStream("gradesystem_test3.txt"));
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
}
