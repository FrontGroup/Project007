package client;

/**
 * 
 * @author petr
 */

public class Team {

	private int id;
	private String pm, name, goal, project, info;
	private User[] members = new User[0];
	private boolean active;

	public Team(int id) {
		this.id = id;
	}

	public Team(int id, String name, String goal, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.goal = goal;
		this.active = active;
	}

	public Team(int id, String pm, String name, String goal, String project, String info,
			boolean active) {
		super();
		this.id = id;
		this.pm = pm;
		this.name = name;
		this.goal = goal;
		this.project = project;
		this.info = info;
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void isActive(boolean active) {
		this.active = active;
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

	public User[] getMembers() {
		return members;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public void addMember(User u) {
		User[] tmpArray = new User[members.length + 1];
		for (int i = 0; i < members.length; i++) {
			tmpArray[i] = members[i];
		}
		members = tmpArray.clone();
		members[members.length-1] = u;
	}
	
	public void removeAllMembers() {
		members = new User[0];
	}

}
