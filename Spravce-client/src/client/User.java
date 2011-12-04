package client;

import java.util.HashMap;

public class User {

    private int id, group, role;
    private String pass, name, lastName, address, city, email, phone,
            profession;
    private HashMap<Integer, ItemStatus> items;
    private HashMap<Integer, TeamStatus> teams;

    public User(int id, int role) {
        super();
        this.id = id;
        this.role = role;
    }

    public User(int crole, int cgroup, String cpass) {
        group = cgroup;
        role = crole;
        pass = cpass;
    }

    public User(int id, int group, int role, String name,
            String lastName, String address, String city, String email,
            String phone, String profession) {
        super();
        this.id = id;
        this.group = group;
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.profession = profession;
    }

    public User(int id, int role, String pass, String name, String lastName) {
        super();
        this.id = id;
        this.role = role;
        this.pass = pass;
        this.name = name;
        this.lastName = lastName;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<Integer, ItemStatus> getItems() {
        return items;
    }

    public HashMap<Integer, TeamStatus> getTeams() {
        return teams;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItems(HashMap<Integer, ItemStatus> items) {
        this.items = items;
    }

    public void setTeams(HashMap<Integer, TeamStatus> teams) {
        this.teams = teams;
    }
}
