package nthu.courses.softwareengineering.gradesystem;

public class Profile {
	
	private int id = -1;
	
	private String name = "";
	
	private int rank = -1;
	
	private final static int NUM_OF_GRADES = 5;
	private int[] grades = {-1, -1, -1, -1, -1};
	
	private int total_grade = 0;
	
	/** method getNumOfProfile()----------------------------------------------------
	 * Get number of grades in a profile
	 * 
	 * @param void
	 * @return int, 6
	 *    
	 * Pesudo code:
	 * return NUM_OF_GRADES;
	 * 
	 * Time estimation: O(1)
	 * 
	 * Example:
	 * Profile p = new Profile();
	 * p.getNumOfProfile will be 6
	 */
	static public int getNumOfGrades() {
		return NUM_OF_GRADES;
	}
	
	/** method getId()----------------------------------------------------
	 * Get ID of a profile
	 * 
	 * @param void
	 * @return int, -1 represent ID of profile remained unset,
	 *              valid ID is a non-negative integer
	 *    
	 * Pesudo code:
	 * return this.id;
	 * 
	 * Time estimation: O(1)
	 * 
	 * Example:
	 * Profile p = new Profile();
	 * p.setId(120598)
	 * int id = p.getId();
	 * id will be 120598
	 */
	public int getId() {
	    return this.id;	
	}
	
	/** method getName()----------------------------------------------------
	 * Get Name of a profile
	 * 
	 * @param void
	 * @return String, null represent Name of profile remained unset,
	 *                 name is a string
	 *    
	 * Pesudo code:
	 * return this.name;
	 * 
	 * Time estimation: O(1)
	 * 
	 * Example:
	 * Profile p = new Profile();
	 * p.setName("Simon");
	 * String name = p.getName();
	 * name will be "Simon"
	 */
	public String getName() {
		return this.name;
	}
	
    /** method getRank()----------------------------------------------------
	 * Get rank of a profile
	 * 
	 * @param void
	 * @return int, -1 represent rank of profile remained unset,
	 *              correct rank is a positive integer
	 *    
	 * Pesudo code:
	 * return this.rank;
	 * 
	 * Time estimation: O(1)
	 * 
	 * Example:
	 * Profile p = new Profile();
	 * p.setRank(20);
	 * int rank = p.getRank();
	 * rank will be 20
	 */
    public int getRank() {
    	return this.rank;
    }
	
	/** method getGrades()----------------------------------------------------
	 * Get grades of a profile
	 * 
	 * @param void
	 * @return int[], list of grades,
	 *         each grade is -1 if it remains unset,
	 *         correct grade number is a non-negative integer ranging from 0-100.     
	 *    
	 * Pesudo code:
	 * return this.grades;
	 * 
	 * Time estimation: O(1)
	 * 
	 * Example:
	 * number of grades is 5
	 * Profile p = new Profile();
	 * int[] grades = {20, 98, 77, 87, 52};
	 * p.setGrades(grades);
	 * int[] grades = p.getGrades();
	 * grades will be {20, 98, 77, 87, 52}
	 */
    public int[] getGrades() {
    	return this.grades;
    }
   
    /** method getTotalGrade()----------------------------------------------------
	 * Get total grade of a profile
	 * 
	 * @param void
	 * @return int, 0 represent rank of profile remained unset,
	 *              correct grade is a non-negative integer ranging from 0-100
	 *    
	 * Pesudo code:
	 * return this.total_grade;
	 * 
	 * Time estimation: O(1)
	 * 
	 * Example:       
	 * Profile p = new Profile();       
	 * 
	 * int[] grades = {20, 98, 77, 87, 52};         
	 * p.setGrades(grades);         
	 * 
	 * double[] weights = {0.1, 0.1, 0.1, 0.3, 0.4};        
	 * p.calculateTotalGrades(weights)         
	 * 
	 * int total_grade = p.getTotalGrades();        
	 * total_grade will be 66         
	 */
    public int getTotalGrade() {
    	return this.total_grade;
    }
    
    /** method setId()----------------------------------------------------
	 * Set ID of a profile
	 * 
	 * @param int, representing ID of a profile
	 * @return void
	 *    
	 * Pesudo code:
	 * if (checkValidity(id))
	 *     this.id = id;
	 * 
	 * Time estimation: O(1)
	 * 
	 * Example:
	 * Profile p = new Profile();
	 * p.setId(10306545);
	 * id of p is then 10306545
	 */
    public void setId(int id) {
    	if (this.checkId(id))
    		this.id = id;
    }
    
    /** method setName()----------------------------------------------------
	 * Set Name of a profile
	 * 
	 * @param String, representing name of a profile
	 * @return void
	 *    
	 * Pesudo code:
	 * if (checkValidity(name))
	 *     this.name = name;
	 * 
	 * Time estimation: O(1)
	 * 
	 * Example:
	 * Profile p = new Profile();
	 * p.setName("John");
	 * name of p is then "John"
	 */
    public void setName(String name) {
    	if (this.checkName(name))
    		this.name = name;
    }
    
    /** method setGrades()----------------------------------------------------
	 * Set grades of a profile
	 * 
	 * @param int[], representing grades of a profile
	 * @return void
	 *    
	 * Pesudo code:
	 * if (checkValidity(grades))
	 *     this.grades = grades;
	 * 
	 * Time estimation: O(1)
	 * 
	 * Example:
	 * Profile p = new Profile();
	 * int[] grades = {36, 77, 54, 87, 90};
	 * p.setGrades(grades);
	 * grades of p is then {36, 77, 54, 87, 90}
	 */
    public void setGrades(int[] grades) {
    	if (this.checkGrades(grades))
    		this.grades = grades;
    }
    
    /** method setRank()----------------------------------------------------
	 * Set rank of a profile
	 * 
	 * @param int, representing rank of a profile
	 * @return void
	 *    
	 * Pesudo code:
	 * if (checkValidity(rank))
	 *     this.rank = rank;
	 * 
	 * Time estimation: O(1)
	 * 
	 * Example:
	 * Profile p = new Profile();
	 * p.setRank(52);
	 * rank of p is then 52
	 */
    public void setRank(int rank) {
    	if (this.checkRank(rank))
    		this.rank = rank;
    }
    
    /** method calculateTotalGrades()----------------------------------------------------
	 * Calculate total grades a profile
	 * 
	 * @param double[], representing weights of each grade. It's sum must be 1.0,
	 *                  and the number of weights must be the same as Profile.getNumOfGrades().
	 * @return void
	 *    
	 * Pesudo code:
	 * if (checkValidity(weights[]))
	 *     for entry in grade
	 *         total grade += grade * corresponding weight in weights[]
	 * 
	 * Time estimation: O(1).
	 * 
	 * Example:
	 * Profile p = new Profile();       
	 * 
	 * int[] grades = {20, 98, 77, 87, 52};         
	 * p.setGrades(grades);         
	 * 
	 * double[] weights = {0.1, 0.1, 0.1, 0.3, 0.4};        
	 * p.calculateTotalGrades(weights)         
	 *         
	 * total grade will be 66    
	 */
    public void calculateTotalGrades(double[] weights) {
    	if (this.checkWeights(weights)) {
    		double temp = 0.0;
    		for (int i=0; i<NUM_OF_GRADES; i++)
    			temp += ((double)this.grades[i]) * weights[i];
    		
    		this.total_grade = (int)temp;
    	}
    }
    
    private boolean checkId(int id) {
    	if (id > 0)
    		return true;
    	return false;
    }
    
    private boolean checkName(String name) {
    	if (name != null && name.length() > 0)
    		return true;
    	return false;
    }
    
    private boolean checkRank(int rank) {
    	if (rank > 0)
    		return true;
    	return false;
    }
    
    private boolean checkGrades(int[] grades) {
    	boolean check = true;
    	
    	if (grades.length == NUM_OF_GRADES) {
    		for (int i=0; i<NUM_OF_GRADES && check; i++) {
    			if (grades[i]<0 || grades[i] > 100)
    				check = false;
    		}
    	} else
    		check = false;
    	
    	return check;
    }
    
    private boolean checkWeights(double[] weights) {
    	if (weights.length == NUM_OF_GRADES) {
    		double percentage = 0.0;
    		
    		for (int i=0; i<NUM_OF_GRADES; i++)
    			percentage += weights[i];
    		
    		if ((int)(percentage*100)>=95 || (int)(percentage*100)<=105)
    			return true;
    	}
    	return false;
    }
}
