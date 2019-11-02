package unsw.dungeon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * public class Goal
 * 
 * reads from a .txt file, and assigns the goals from it.
 * 
 */
public class GoalReader {
	private String goalText;
	
	String line = null; // the line used to iterate through the goal file.
	
	public GoalReader(String goalText) {
		this.setGoalText(goalText);
	}

	public String getGoalText() {
		return goalText;
	}

	public void setGoalText(String goalText) {
		this.goalText = goalText;
	}
	
	public void readGoal() {
		try {
	        // FileReader reads text files in the default encoding.
			// System.out.println(System.getProperty("user.dir"));
	        FileReader fileReader = new FileReader("./goals/" + goalText);
	     // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	
	        while((line = bufferedReader.readLine()) != null) {
	            // System.out.println(line); (bug checker)
	        	// goals will always be 4 things
	        	// first, a "command"
	        	// then, a number, for how many times.
	        	// then, "or" or "and" to denote ^ or v (the first one is always and).
	        	// then, either a '[', ']' or '-', where the bracket denotes brackets start and end
	        	String[] arguments = line.split(" ");
	        	switch(arguments[0]) {
	        	case "exit":
	        		// exit
	        		System.out.println("The goal is to exit!");
	        		break;
	        	case "switch":
	        		System.out.println("The goal is to activate " + arguments[1] + " floorswitches!");
	        		break;
	        	case "treasure":
	        		System.out.println("The goal is to get " + arguments[1] + " treasures!");
	        		break;
	        	case "kill":
	        		System.out.println("The goal is to kill " + arguments[1] + " enemies!");
	        		break;
	        	
	        	}
	        }   
	
	        // Always close files.
	        bufferedReader.close();         
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println(
	            "Unable to open file '" + 
	            goalText + "'");                
	    }
	    catch(IOException e) {
	        System.out.println("Error reading file '" + goalText + "'");                  
	    }
	}
}
