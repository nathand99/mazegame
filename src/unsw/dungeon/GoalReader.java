package unsw.dungeon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * public class GoalReader
 * 
 * Grabs goals in the .json file.
 * 
 */
public class GoalReader {
	private PlayerGoal goals;
	private Player player;
	private JSONObject json;
	
	public GoalReader(PlayerGoal goals, Player player, String filename) throws FileNotFoundException { 
		// can attach list of entities later to read how many of each goal is needed.
		this.goals = goals;
		this.player = player;
		// System.out.println(filename);
		json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename + ".json")));
	}
	
	/**
	 * loadGoal: loads the goals from the JSON file into the PlayerGoal.
	 */
	public void loadGoal() {
		JSONObject JSONGoals = json.getJSONObject("goal-condition"); //goal-condition is an object
		String initialGoal = (String) JSONGoals.get("goal");
		System.out.println(initialGoal);
		// and goal
		if (initialGoal.equals("AND")) {
			MultipleGoal andGoal = new MultipleGoal(initialGoal);
	    // or goal
		} else if (initialGoal.equals("OR")) {
			MultipleGoal orGoal = new MultipleGoal(initialGoal);
		// single goal
		} else {
			// currently adds only once, needs to add multiple later.
			SingleGoal onlyGoal = new SingleGoal(initialGoal, 1); // TODO: EDIT
			goals.addGoal(onlyGoal);
		}
		player.addGoals(goals);
		goals.setPlayer(player);
	}
	
	
	public PlayerGoal getGoals() {
		return goals;
	}

	public void setGoals(PlayerGoal goals) {
		this.goals = goals;
	}
	
	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
