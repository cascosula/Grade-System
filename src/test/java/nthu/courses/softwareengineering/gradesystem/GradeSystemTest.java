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
			assertTrue(false);
		}
		
		try {
			GradeSystem gs = new GradeSystem("gradeinput.txt");
			System.setIn(ClassLoader.getSystemResourceAsStream("gradesystem_test0.txt"));
			gs.run();
			
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
}
