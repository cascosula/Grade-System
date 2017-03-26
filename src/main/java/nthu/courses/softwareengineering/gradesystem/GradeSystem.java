package nthu.courses.softwareengineering.gradesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.lang.ClassLoader;


public class GradeSystem implements GradeSystemUi{

	private int num_of_profile = 0;
	
	private HashMap<Integer, Profile> pf_map;

	private double[] weights = {0.1, 0.1, 0.1, 0.3, 0.4};
	
	private double[] average = null;

	private final static int CMD_SHOW_GRADES = 1;
	private final static int CMD_SHOW_RANK = 2;
	private final static int CMD_SHOW_AVERAGE = 3;
	private final static int CMD_UPDATE_WEIGHTS = 4;
	
	public GradeSystem(String file_name) {
		
		BufferedReader reader = null;
	    try {
	    	/* set file reader */
	    	reader = 
	    		new BufferedReader(
	    				new InputStreamReader (
	    						ClassLoader.getSystemResourceAsStream(file_name),
	    	    				"UTF8"
	    				)
	    		);

            this.pf_map = new HashMap<Integer, Profile>();
            
            /* parse all profiles */
	    	String raw_data;
	    	int buff_size = 1024;
	    	
	    	reader.mark(buff_size);
	    	if (reader.read() != 0xFEFF)
	    		  reader.reset();
	    	while ((raw_data = reader.readLine()) != null) {
	    		//System.out.println(raw_data);
	    		Profile p = this.parseProfile(raw_data);
	    		
	    		if (p != null) {
		    		this.pf_map.put(new Integer(p.getId()), p);
		    		num_of_profile++;
	    		}
	    		reader.mark(buff_size);
	    		if (reader.read() != 0xFEFF)
		    		  reader.reset();
	    	}
	    	
	    	/* calculate average */
	    	this.calculateAverage();
	    	
	    	/* ranking by total grades */
	    	this.ranking();
	    } catch (FileNotFoundException file_not_found_err) {
	    	file_not_found_err.printStackTrace();
	    } catch (IOException io_err) {
	    	io_err.printStackTrace();
	    }   finally {
    		if (reader != null) {
    			try {
    				reader.close();
    			} catch (IOException IOErr) {
    				IOErr.printStackTrace();
    			}
    		}
    	}
	}
    
	/** method run()-----------------------------------------------------
	 * The main running progress of grade system
	 * 
	 * @param void
	 * @return void
	 * 
	 * @throws GradeSystemRuntimeException
	 * 
	 * Pesudo code:
	 * while(true) {
	 *     id = this.scanId();
	 *     if (valid id) {
	 *         Profile p = this.searchProfile(id);
	 *         if (p exists) {
	 *             this.showWelcomeMsg(p);
	 *             while(true) {
	 *                 cmd = this.scanCmd();
	 *                 if (cmd is show grades of p) this.showGrades();
	 *                 else if (cmd is show rank of p) this.showRank();
	 *                 else if (cmd is show average) this.showAverage();
	 *                 else if (cmd is modify weights) this.updateWeights();;
	 *                 else if (cmd is exit menu) break;
	 *             }
	 *         } else {
	 *             show id not existed
	 *         }
	 *     } else if (invalid id) {
	 *         show invalid id
	 *     } else if (quit) {
	 *         show termination message
	 *         break;
	 *     } else throw GradeSystemRuntimeException
	 * }
	 * 
	 * Time estimation:
	 * no estimation due to blocking read from system standard in
	 * 
	 * Example:
	 * input 962001051
	 * screen shows "Welcome 李威廷"
	 * input G 
	 * screen shows "81 32 50 90 93"
	 * input Q
	 * screen shows "Goodbye"
	 --------------------------------------------------------------------*/
    public void run() throws GradeSystemRuntimeException {
    	while(true) {
    		int id = this.scanId();
    		if (id > 0) {
    			Profile p = this.searchProfile(id);
    			
    			if (p != null) {
    				StringBuffer welcome_msg = new StringBuffer();
    				welcome_msg.append("Welcome ");
    				welcome_msg.append(p.getName());
    				System.out.println(welcome_msg.toString());
    				while (true) {
    					int cmd = this.scanCmd();
    					
    					if (cmd == CMD_SHOW_GRADES) this.showGrades(p);
    					else if (cmd == CMD_SHOW_RANK) this.showRank(p);
    					else if (cmd == CMD_SHOW_AVERAGE) this.showAverage();
    					else if (cmd == CMD_UPDATE_WEIGHTS) this.updateWeights();
    					else if (cmd == 0) break;
    					else {
    						//this.showErrorCmdMsg();
    						//this.showCmdList();
    					}
    				}
    			} else {
    				System.out.println("ID錯了!");
    			}
    		} else if (id == 0) break;
    		else if (id == -1) System.out.println("ID錯了!");
    		else if (id == -2) throw new GradeSystemRuntimeException();
    	}
    	System.out.println("結束了");
    }
    
    private int scanId() {
    	/* initialize return value as system runtime error code */
    	int value = -2;
    	
    	/* set standard in scanner */
    	Scanner scanner = new Scanner(System.in);
    	scanner.useDelimiter(System.getProperty("line.separator"));

    	
    	/* determine id, quit or error corresponding to input */
    	String quit_cmd = "Q";
    	
    	String buff = "";
    	if (scanner.hasNextLine() && !((buff=scanner.nextLine()).equals(""))) {
	        System.out.println(buff);
        	int id = -1;
        	if (buff.equals(quit_cmd))
        		value = 0;
        	else if (!buff.equals("") && (id = Integer.parseInt(buff)) > 0)
        		value = id;
        	else if (id < 0)
        		value = -1;
        	
    	}
    	
    	//scanner.close();
    	
    	return value;
    }
    
    private int scanCmd() {
    	/* initialize return value as system runtime error code */
    	int value = -1;
    	
    	/* set standard in scanner */
    	Scanner scanner = new Scanner(System.in);
    	scanner.useDelimiter(System.getProperty("line.separator"));
    	
    	/* determine id, quit or error corresponding to input */
    	String show_grades_cmd = "G";
    	String show_average_cmd = "A";
    	String show_rank_cmd = "R";
    	String upgrade_cmd = "W";
    	String exit_cmd = "E";
    	
    	String buff = "";
    	if (scanner.hasNextLine() && !((buff=scanner.nextLine()).equals(""))) {
        	if (buff.equals(exit_cmd))
        		value = 0;
        	else if (buff.equals(show_grades_cmd))
        		value = CMD_SHOW_GRADES;
        	else if (buff.equals(show_average_cmd))
        		value = CMD_SHOW_AVERAGE;
        	else if (buff.equals(show_rank_cmd))
        		value = CMD_SHOW_RANK;
        	else if (buff.equals(upgrade_cmd))
        		value = CMD_UPDATE_WEIGHTS;
        	
    	}

    	//scanner.close();
    	
    	return value;
    }
    
    private Profile parseProfile(String raw_data) {
    	String[] tokens = raw_data.split(" ");
		
		if (tokens.length >= Profile.getNumOfGrades()+2) {
    		int id_off_index = 0;
    		int name_off_index = 1;
    		int grades_off_index = 2;
    		
    		Profile p = new Profile();
    		
    		/* set id */
			p.setId(Integer.parseInt(tokens[id_off_index]));
			
			/* set name */
			p.setName(tokens[name_off_index]);
    		
			/* set grades*/
    		int[] grades = new int[Profile.getNumOfGrades()];
    		for(int i=grades_off_index, j=0;
    				i<tokens.length && j<grades.length;
    				i++, j++) {
    			grades[j] = Integer.valueOf(tokens[i]);
    		}
    		p.setGrades(grades);
    		
    		/* calculate total */
    		p.calculateTotalGrades(weights);
    		
    		return p;
		}
    	return null;
    }
    
    private void showGrades(Profile p) {
    	StringBuffer buff = new StringBuffer();
    	char[][] label = {
    			{'l', 'a', 'b', '1', ':', ' '},
    			{'l', 'a', 'b', '2', ':', ' '},
    			{'l', 'a', 'b', '3', ':', ' '},
    			{'m', 'i', 'd', '-', 't', 'e', 'r', 'n', ':', ' '},
    			{'f', 'i', 'n', 'a', 'l', ' ', 'e', 'x', 'a', 'm', ':', ' '}
    	};
    	
    	/* append name of profile*/
    	buff.append(p.getName()+"的成績\n");
    	
    	/* append grades */
    	int[] grades = p.getGrades();
    	for (int i=0; i<Profile.getNumOfGrades(); i++) {
    		buff.append(label[i]);
    		buff.append(grades[i]);
    		buff.append('\n');
    	}
    		
    	/* append total grades */
    	char[] total_label = {'t', 'o', 't', 'a', 'l', ' ', 'g', 'r', 'a', 'd', 'e', ':', ' '};
    	buff.append(total_label);
    	buff.append(p.getTotalGrade());
    	buff.append('\n');
    	
    	System.out.println(buff.toString());
    }
    
    private void showRank(Profile p) {
    	StringBuffer buff = new StringBuffer();
    	buff.append(p.getName()+"排名第 ");
    	buff.append(p.getRank());
    	buff.append('\n');
    	
    	System.out.println(buff.toString());
    }
    
    private void showCmdList() {
    	System.out.println("可使用指令為");
    	System.out.println("G 顯示成績 (Grade)");
    	System.out.println("R 顯示排名 (Rank)");
    	System.out.println("A 顯示平均 (Average)");
    	System.out.println("W 更新配分 (Weight)");
    	System.out.println("E 離開選單 (Exit)");
    }
    
    private void showErrorCmdMsg() {
    	System.out.println("指令錯誤！");
    }
    
    private void showAverage() {
    	StringBuffer buff = new StringBuffer();
    	char[][] label = {
    			{'l', 'a', 'b', '1', ':', ' '},
    			{'l', 'a', 'b', '2', ':', ' '},
    			{'l', 'a', 'b', '3', ':', ' '},
    			{'m', 'i', 'd', '-', 't', 'e', 'r', 'n', ':', ' '},
    			{'f', 'i', 'n', 'a', 'l', ' ', 'e', 'x', 'a', 'm', ':', ' '}
    	};
    	
    	buff.append("班平均:\n");
    	for (int i=0; i<Profile.getNumOfGrades(); i++) {
    		buff.append(label[i]);
    		buff.append((int)this.average[i]);
    		buff.append('\n');
    	}
    	
    	System.out.println(buff.toString());
    }
    
    private void updateWeights() {
    	/* initialize reader to standard in */
    	BufferedReader reader = null;
    	
    	try {
    		/* set standard in reader */
        	reader = new BufferedReader(
        				new InputStreamReader(
        						System.in
        				)
        			);
        	
        	/* read from standard in */
        	boolean valid_weights = false;
        	int buff_size = 1024;
        	char[] buff = new char[buff_size];
        	
        	/* weights label */
        	char[][] label = {
        			{'l', 'a', 'b', '1', ' ', ' '},
        			{'l', 'a', 'b', '2', ' ', ' '},
        			{'l', 'a', 'b', '3', ' ', ' '},
        			{'m', 'i', 'd', '-', 't', 'e', 'r', 'n', ' ', ' '},
        			{'f', 'i', 'n', 'a', 'l', ' ', 'e', 'x', 'a', 'm', ' ', ' '}
        	};
        	
        	/* show current weights */
        	System.out.println("舊配分");
        	StringBuffer print_buffer = new StringBuffer();
        	for (int i=0; i<this.weights.length; i++) {
        		print_buffer.append(label[i]);
        		print_buffer.append((int)(weights[i]*100));
        		print_buffer.append('%');
        		print_buffer.append('\n'); 
    		}
        	System.out.print(print_buffer.toString());
            
        	while(!valid_weights) {
        		int[] temp = new int[this.weights.length];
        		
        		System.out.println("輸入新配分");
        		for (int i=0; i<this.weights.length; i++) {
        			
        			System.out.print(label[i]);
        			
        			int read_size = reader.read(buff, 0, buff_size-1);
        			if (read_size < 0) return;
        			
        			temp[i] = Integer.parseInt(new String(buff, 0, read_size-2));
        		}
        		
        		if ((valid_weights = this.check_new_weights(temp))) {
        			System.out.println("以上正確嗎? Y(Yes)或 N(No)");
        			
        			int read_size = reader.read(buff, 0, buff_size-1);
        			if (read_size < 0) return;
        			
        			char correct_and_save_cmd = 'Y';
                	char reinput_cmd = 'N';

                	if (buff[0] == reinput_cmd)
                		valid_weights = false;
                	else if(buff[0] == correct_and_save_cmd) {
            			for (int i=0; i<this.weights.length; i++)
            				this.weights[i] = ((double)temp[i]) / 100.0;
            			
                		Profile[] pf_list = this.getProfileList();
                		
                		for (int i=0; i<pf_list.length; i++)
                			pf_list[i].calculateTotalGrades(this.weights);
                		
                		this.ranking();
                	}
        		} else
        			System.out.println("配分合不為100%! 請重新輸入");
        	}
    	} catch (IOException IOErr) {
    		IOErr.printStackTrace();
    	} finally {
    		/*
    		if (reader != null) {
    			try {
    				reader.close();
    			} catch (IOException IOErr) {
    				IOErr.printStackTrace();
    			}
    		}*/
    	}
    }
    
    private boolean check_new_weights(int[] weights) {
    	boolean check = false;
    	
    	if (weights.length == this.weights.length) {
    		int sum = 0;
    		int expected_sum = 100;
    		
    		for (int i=0; i<weights.length; i++)
    			sum += weights[i];
            
    		if (sum == expected_sum) check = true;
    	}
    	
    	return check;
    }
    
    private Profile searchProfile(int id) {
    	return this.pf_map.get(id);
    }
    
    
    private void calculateAverage() {
    	/* get profile list */
    	Profile[] pf_list = this.getProfileList();
    	
    	/* initialize average columns */
    	if (this.average == null) 
    		this.average = new double[Profile.getNumOfGrades()];
    	for (int i=0; i<this.average.length; i++)
    		this.average[i] = 0.0;
    	
    	/* summation of each grade */
    	for (int i=0; i<pf_list.length; i++) {
    		int[] grades = pf_list[i].getGrades();
    		for (int j=0; j<this.average.length; j++)
    			this.average[j] += grades[j];
    	}
    	
    	/* divide by number of profiles, obtaining average of each grade */
    	for (int i=0; i<this.average.length; i++)
    		this.average[i] /= pf_list.length;
    }
    
    
    private void ranking() {
    	/* get sorted profile list(in decreasing order) */
    	Profile[] sorted_pf_list = this.getProfileList();
    	this.sortByTotalGrades(sorted_pf_list);
    	
    	/* ranking */
    	int rank = 1;
    	sorted_pf_list[0].setRank(rank);
    	for (int i=1; i<sorted_pf_list.length; i++) {
    		rank++;
    		if (sorted_pf_list[i-1].getTotalGrade()
    				> sorted_pf_list[i].getTotalGrade()) {
    		    sorted_pf_list[i].setRank(rank);
    		} else if (sorted_pf_list[i-1].getTotalGrade()
    				== sorted_pf_list[i].getTotalGrade()) {
    			sorted_pf_list[i].setRank(sorted_pf_list[i-1].getRank());
    		}
    	}
    }
    
    private Profile[] getProfileList() {
    	Profile[] pf_list = new Profile[this.num_of_profile];
    	
    	int pf_list_index = 0;
    	for (Map.Entry<Integer, Profile> e : this.pf_map.entrySet()) {
    		pf_list[pf_list_index] = e.getValue();
    		pf_list_index++;
    	}

    	return pf_list;
    }
    
    private Profile[] sortByTotalGrades(Profile[] pf_list) {
       Arrays.sort(pf_list, new Comparator<Profile>() {
		    public int compare(
		    		Profile src, 
		    		Profile tar
		    ) {
		    	return 
		    		tar.getTotalGrade()
		    		- src.getTotalGrade();
		    }
	   });
	    
    	return pf_list;
    }
}
