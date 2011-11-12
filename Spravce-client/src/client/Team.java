package client;

/**
 * 
 * @author petr
 */

public class Team {

	private int id;
	private String name;
	private String goal;
	private String project;
	private String info;
	private String[] members = new String[0];
	private boolean active;

	public boolean isActive() {
		return active;
	}

	public void isActive(boolean active) {
		this.active = active;
	}

	public Team(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String[] getMembers() {
		return members;
	}

	public void addMember(int id) {
		String[] tmpArray = new String[members.length+1];
		for (int i = 0; i < members.length; i++) {
			tmpArray[i] = members[i];
		}
		members = tmpArray.clone();
	}

}
