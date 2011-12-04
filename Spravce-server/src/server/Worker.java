/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author lucas
 */
public abstract class Worker {

    private static String[] split = null;
    private static DBConnection dbc = null;

    public abstract String process();

    public static Worker getWorker(String msg, DBConnection dbc) {
        Worker.split = msg.split(" ");
        Worker.dbc = dbc;
        if (split[0].contains("LOGIN")) {
            return new login();
        }
        if (split[0].contains("GET_USERS")) {
            return new getUsers();
        }
        if (split[0].contains("GET_INFO")) {
            return new getInfo();
        }
        if (split[0].contains("ADD_USER")) {
            return new addUser();
        }
        if (split[0].contains("DEL_USER")) {
            return new delUser();
        }
        if (split[0].contains("CHANGE_PASS")) {
            return new changePass();
        }
        if (split[0].contains("ADMIN_CHANGE_PASS")) {
            return new adminChangePass();
        }
        if (split[0].contains("GET_GROUPS")) {
            return new getGroups();
        }
        if (split[0].contains("GET_ITEMS")) {
            return new getItems();
        }
        if (split[0].contains("GET_TEAMS")) {
            return new getTeams();
        }
        if (split[0].contains("ADD_ITEM")) {
            return new addItem();
        }
        if (split[0].contains("DEL_ITEM")) {
            return new delItem();
        }
        if (split[0].contains("UPDATE_ITEM")) {
            return new updateItem();
        }
        if (split[0].contains("ADD_TEAM")) {
            return new addTeam();
        }
        if (split[0].contains("DEL_TEAM")) {
            return new delTeam();
        }
        if (split[0].contains("UPDATE_USER")) {
            return new updateUser();
        }
        if (split[0].contains("ADD_GROUP")) {
            return new addGroup();
        }
        if (split[0].contains("DEL_GROUP")) {
            return new delGroup();
        }
        if (split[0].contains("UPDATE_GROUP")) {
            return new updateGroup();
        }
        if (split[0].contains("GET_USER_ITEMS")) {
            return new getUserItems();
        }
        if (split[0].contains("GET_USER_TEAMS")) {
            return new getUserTeams();
        }
        if (split[0].contains("GET_PM_TEAMS")) {
            return new getPMTeams();
        }
        if (split[0].contains("USER_IN_TEAM")) {
            return new userInTeam();
        }
        if (split[0].contains("USER_OUT_TEAM")) {
            return new userOutTeam();
        }
        if (split[0].contains("SET_TEAM_CONFIRMED")) {
            return new setTeamConfirmed();
        }
        if (split[0].contains("UPDATE_TEAM")) {
            return new updateTeam();
        }
        if (split[0].contains("SET_ITEM_STATE")) {
            return new setItemState();
        }
        return null;
    }

    public static class login extends Worker {

        @Override
        public String process() {
            if (split.length != 3) {
                return "KO Wrong requirement!";
            }
            return dbc.login(split[1], split[2]);
        }
    }

    public static class getInfo extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.getInfo(split[1]);
        }
    }

    public static class getUsers extends Worker {

        @Override
        public String process() {
            if (split.length != 1) {
                return "KO Wrong requirement!";
            }
            return dbc.getUsers();
        }
    }

    public static class addUser extends Worker {

        @Override
        public String process() {
            if (split.length != 4) {
                return "KO Wrong requirement!";
            }
            return dbc.addUser(Integer.valueOf(split[2]), split[3], null, null, null, null, null, null, Integer.valueOf(split[1]));
        }
    }

    public static class delUser extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.deleteUser(split[1]);
        }
    }

    public static class changePass extends Worker {

        @Override
        public String process() {
            if (split.length != 4) {
                return "KO Wrong requirement!";
            }
            return dbc.changePass(split[1], split[2], split[3]);
        }
    }

    public static class adminChangePass extends Worker {

        @Override
        public String process() {
            if (split.length != 3) {
                return "KO Wrong requirement!";
            }
            return dbc.adminChangePass(split[1], split[2]);
        }
    }

    public static class getItems extends Worker {

        @Override
        public String process() {
            if (split.length != 1) {
                return "KO Wrong requirement!";
            }
            return dbc.getItems();
        }
    }

    public static class getTeams extends Worker {

        @Override
        public String process() {
            if (split.length != 1) {
                return "KO Wrong requirement!";
            }
            return dbc.getTeams();
        }
    }

    public static class getUserItems extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.getUserItems(split[1]);
        }
    }

    public static class getUserTeams extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.getUserTeams(split[1]);
        }
    }

    public static class getGroups extends Worker {

        @Override
        public String process() {
            if (split.length != 1) {
                return "KO Wrong requirement!";
            }
            return dbc.getGroups();
        }
    }

    public static class addItem extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.addItem(split[1]);
        }
    }

    public static class delItem extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.delItem(split[1]);
        }
    }

    public static class updateItem extends Worker {

        @Override
        public String process() {
            if (split.length != 3) {
                return "KO Wrong requirement!";
            }
            return dbc.updateItem(split[1], split[2]);
        }
    }

    public static class updateUser extends Worker {

        @Override
        public String process() {
            if (split.length != 9) {
                return "KO Wrong requirement!";
            }
            return dbc.updateUser(split[1], split[2], split[3], split[4], split[5], split[6], split[7], split[8]);
        }
    }

    public static class addTeam extends Worker {

        @Override
        public String process() {
            if (split.length != 6) {
                return "KO Wrong requirement!";
            }
            return dbc.addTeam(split[1], split[2], split[3], split[4], split[5]);
        }
    }

    public static class updateTeam extends Worker {

        @Override
        public String process() {
            if (split.length != 7) {
                return "KO Wrong requirement!";
            }
            return dbc.updateTeam(split[1], split[2], split[3], split[4], split[5], split[6]);
        }
    }

    public static class delTeam extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.delTeam(split[1]);
        }
    }

    public static class userInTeam extends Worker {

        @Override
        public String process() {
            if (split.length != 3) {
                return "KO Wrong requirement!";
            }
            return dbc.userInTeam(split[1], split[2]);
        }
    }

    public static class userOutTeam extends Worker {

        @Override
        public String process() {
            if (split.length != 3) {
                return "KO Wrong requirement!";
            }
            return dbc.userOutTeam(split[1], split[2]);
        }
    }

    public static class setTeamConfirmed extends Worker {

        @Override
        public String process() {
            if (split.length != 4) {
                return "KO Wrong requirement!";
            }
            boolean confirmed = true;
            if (split[3].contains("false") || split[3].contains("0")) {
                confirmed = false;
            }
            return dbc.setTeamConfirmed(split[1], split[2], confirmed);
        }
    }

    public static class setItemState extends Worker {

        @Override
        public String process() {
            if (split.length != 4) {
                return "KO Wrong requirement!";
            }
            boolean state = true;
            if (split[3].contains("false") || split[3].contains("0")) {
                state = false;
            }
            return dbc.setItemState(split[1], split[2], state);
        }
    }

    public static class addGroup extends Worker {

        @Override
        public String process() {
            if (split.length != Integer.valueOf(split[2]) + 3) {
                return "KO Wrong requirement!";
            }
            int[] items = new int[Integer.valueOf(split[2])];
            for (int k = 0; k < items.length; k++) {
                items[k] = Integer.valueOf(split[3 + k]);
            }
            return dbc.addGroup(split[1], items);
        }
    }

    public static class updateGroup extends Worker {

        @Override
        public String process() {
            if (split.length != Integer.valueOf(split[3]) + 3) {
                return "KO Wrong requirement!";
            }
            int[] items = new int[Integer.valueOf(split[3])];
            for (int k = 0; k < items.length; k++) {
                items[k] = Integer.valueOf(split[4 + k]);
            }
            return dbc.updateGroup(split[1], split[2], items);
        }
    }

    public static class delGroup extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.delGroup(split[1]);
        }
    }

    public static class getPMTeams extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.getPMTeams(split[1]);
        }
    }
}