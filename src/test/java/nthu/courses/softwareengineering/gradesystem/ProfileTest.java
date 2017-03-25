package nthu.courses.softwareengineering.gradesystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nthu.courses.softwareengineering.gradesystem.Profile;

public class ProfileTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test getId()
	 * case1 : 103021010
	 * case2 : -16416
	 */
	@Test
	public void testGetId() {
		Profile p = new Profile();
		
		int id1 = 103021010;
		int result1 = 103021010;
		p.setId(id1);
		assertEquals(result1, p.getId());
		
		int id2 = -16416;
		int result2 = 103021010;
		p.setId(id2);
		assertEquals(result2, p.getId());
	}
	
	/**
	 * Test getName()
	 * case1 : 許文馨
	 * case2 : 凌宗廷
	 */
	@Test
	public void testGetName() {
		Profile p = new Profile();
		
		String name1 = "許文馨";
		String result1 = "許文馨";
		p.setName(name1);
		assertEquals(result1, p.getName());
		
		String name2 = "凌宗廷";
		String result2 = "凌宗廷";
		p.setName(name2);
		assertEquals(result2, p.getName());
	}
	
	/**
	 * Test getGrades()
	 * case1 : 88 92 -95 98 91 93
	 * case2 : 85 90 82 93 85
	 * case3 : 76 45 92
	 * case4 : empty
	 */
	@Test
	public void testGetGrades() {
		Profile p = new Profile();
		
		int[] grades1 = {88, 92, -95, 98, 91, 93};
		int[] result1 = {-1, -1, -1, -1, -1};
		p.setGrades(grades1);
		for (int i=0; i<result1.length; i++)
		    assertEquals(result1[i], p.getGrades()[i]);
		
		int[] grades2 = {85, 90, 82, 93, 85};
		int[] result2 = {85, 90, 82, 93, 85};
		p.setGrades(grades2);
		for (int i=0; i<result2.length; i++)
		    assertEquals(result2[i], p.getGrades()[i]);
		
		int[] grades3 = {76, 45, 92};
		int[] result3 = {85, 90, 82, 93, 85};
		p.setGrades(grades3);
		for (int i=0; i<result3.length; i++)
		    assertEquals(result3[i], p.getGrades()[i]);
	}
	
	/**
	 * Test getRank()
	 * case1 : -10
	 * case2 : 2
	 * case3 : -8
	 */
	@Test
	public void testGetRank() {
		Profile p = new Profile();
		
		int rank1 = -10;
		int result1 = -1;
		p.setRank(rank1);
		assertEquals(result1, p.getRank());
		
		int rank2 = 2;
		int result2 = 2;
		p.setRank(rank2);
		assertEquals(result2, p.getRank());
		
		int rank3 = -8;
		int result3 = 2;
		p.setRank(rank3);
		assertEquals(result3, p.getRank());
	}
	
	/**
	 * Test getTotalGrade()
	 * case1 : 88 94 66 87 75, under weights: 0.1 0.1 0.1 0.3 0.4
	 * case2 : 71 86 73 92 61, under weights: 0.2 0.2 0.2 0.2 0.2
	 * case3 : 91 82 78 65 77, under weights: 0.1 0.1 0.2 0.2 0.4
	 */
	@Test
	public void testGetTotalGrade() {
		Profile p = new Profile();
		
		int[] grades1 = {88, 94, 66, 87, 75};
		double[] weights1 = {0.1, 0.1, 0.1, 0.3, 0.4};
		int result1 = 80;
		p.setGrades(grades1);
		p.calculateTotalGrades(weights1);;
		assertEquals(result1, p.getTotalGrade());
		
		int[] grades2 = {71, 86, 73, 92, 61};
		double[] weights2 = {0.2, 0.2, 0.2, 0.2, 0.2};
		int result2 = 76;
		p.setGrades(grades2);
		p.calculateTotalGrades(weights2);;
		assertEquals(result2, p.getTotalGrade());
		
		int[] grades3 = {91, 82, 78, 65, 77};
		double[] weights3 = {0.1, 0.1, 0.2, 0.2, 0.4};
		int result3 = 76;
		p.setGrades(grades3);
		p.calculateTotalGrades(weights3);;
		assertEquals(result3, p.getTotalGrade());
	}
	
	/**
	 * test setId()
	 * case1 : -178565
	 * case2 : 101078892
	 * case3 : -15146
	 */
	@Test
	public void testSetId() {
        Profile p = new Profile();
		
		int id1 = -178565;
		int result1 = -1;
		p.setId(id1);
		assertEquals(result1, p.getId());
		
		int id2 = 101078892;
		int result2 = 101078892;
		p.setId(id2);
		assertEquals(result2, p.getId());
		
		int id3 = -15146;
		int result3 = 101078892;
		p.setId(id3);
		assertEquals(result3, p.getId());
	}
	
	/**
	 * Test setName()
	 * case1 : 蘇亨玠
	 * case2 : 呂映萱
	 */
	@Test
	public void testSetName() {
		Profile p = new Profile();
		
		String name1 = "蘇亨玠";
		String result1 = "蘇亨玠";
		p.setName(name1);
		assertEquals(result1, p.getName());
		
		String name2 = "呂映萱";
		String result2 = "呂映萱";
		p.setName(name2);
		assertEquals(result2, p.getName());
	}
	
	/**
	 * Test setGrades()
	 * Under default weights : 0.1, 0.1, 0.1, 0.3, 0.4
	 * case1 : empty 
	 * case2 : 85 90 82 93 85
	 * case3 : 88 92 88 98
	 */
	@Test
	public void testSetGrades() {
        Profile p = new Profile();
		
		int[] grades1 = {};
		int[] result1 = {-1, -1, -1, -1, -1};
		p.setGrades(grades1);
		for (int i=0; i<result1.length; i++)
		    assertEquals(result1[i], p.getGrades()[i]);
		
		int[] grades2 = {85, 90, 82, 93, 85};
		int[] result2 = {85, 90, 82, 93, 85};
		p.setGrades(grades2);
		for (int i=0; i<result2.length; i++)
		    assertEquals(result2[i], p.getGrades()[i]);
		
		int[] grades3 = {88, 92, 88, 98};
		int[] result3 = {85, 90, 82, 93, 85};
		p.setGrades(grades3);
		for (int i=0; i<result3.length; i++)
		    assertEquals(result3[i], p.getGrades()[i]);
	}
	
	/**
	 * Test setRank()
	 * case1 : 0
	 * case2 : 23
	 * case3 : -2
	 */
	@Test
	public void testSetRank() {
		Profile p = new Profile();
		
		int rank1 = 0;
		int result1 = -1;
		p.setRank(rank1);
		assertEquals(result1, p.getRank());
		
		int rank2 = 23;
		int result2 = 23;
		p.setRank(rank2);
		assertEquals(result2, p.getRank());
		
		int rank3 = -2;
		int result3 = 23;
		p.setRank(rank3);
		assertEquals(result3, p.getRank());
	}
	
	/**
	 * Test getCalculateTotalGrades()
	 * case1 : 88 94 66 87 75, under weights: 0.1 0.1 0.1 0.3 0.4
	 * case2 : 71 86 73 92 61, under weights: 0.2 0.2 0.2 0.2 0.2
	 * case3 : 91 82 78 65 77, under weights: 0.1 0.1 0.2 0.2 0.4
	 */
	@Test
	public void getCalculateTotalGrades() {
		Profile p = new Profile();
		
		int[] grades1 = {88, 94, 66, 87, 75};
		double[] weights1 = {0.1, 0.1, 0.1, 0.3, 0.4};
		int result1 = 80;
		p.setGrades(grades1);
		p.calculateTotalGrades(weights1);;
		assertEquals(result1, p.getTotalGrade());
		
		int[] grades2 = {71, 86, 73, 92, 61};
		double[] weights2 = {0.2, 0.2, 0.2, 0.2, 0.2};
		int result2 = 76;
		p.setGrades(grades2);
		p.calculateTotalGrades(weights2);;
		assertEquals(result2, p.getTotalGrade());
		
		int[] grades3 = {91, 82, 78, 65, 77};
		double[] weights3 = {0.1, 0.1, 0.2, 0.2, 0.4};
		int result3 = 76;
		p.setGrades(grades3);
		p.calculateTotalGrades(weights3);;
		assertEquals(result3, p.getTotalGrade());
	}
}
