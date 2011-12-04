package client;

import java.util.HashMap;

/**
 * 
 * @author petr
 */
public class Team {

    private int id, pm;
    private String name, goal, project, info;
    private static HashMap<Integer, User> members = new HashMap();
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

    public Team(int id, int pm, String name, String goal, String project, String info,
            String active) {
        super();
        this.id = id;
        this.pm = pm;
        this.name = name;
        this.goal = goal;
        this.project = project;
        this.info = info;
        if (active.contains("false") || active.contains("0")) {
            this.active = false;
        } else {
            this.active = true;
        }
    }

    public Team(int id, int pm, String name, String goal, String project, String info,
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

    public HashMap<Integer, User> getMembers() {
        return members;
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }

	public void removeAllMembers() {
		members.clear();
		
	}

	public void addMember(User member) {
		members.put(member.getId(), member);
		
	}
}
