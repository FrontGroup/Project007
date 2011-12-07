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
    private static DBCInt dbc = null;

    public abstract String process();

    public static Worker getWorker(String msg, DBCInt dbc) {
        Worker.split = msg.split(" ");
        Worker.dbc = dbc;
        if (split[0].contains("LOGIN")) {
            return new Login();
        }
        if (split[0].contains("GET_USERS")) {
            return new GetUsers();
        }
        if (split[0].contains("GET_INFO")) {
            return new GetInfo();
        }
        if (split[0].contains("ADD_USER")) {
            return new AddUser();
        }
        if (split[0].contains("DEL_USER")) {
            return new DelUser();
        }
        if (split[0].contains("CHANGE_PASS")) {
            return new ChangePass();
        }
        if (split[0].contains("ADMIN_CHANGE_PASS")) {
            return new AdminChangePass();
        }
        if (split[0].contains("GET_GROUPS")) {
            return new GetGroups();
        }
        if (split[0].contains("GET_ITEMS")) {
            return new GetItems();
        }
        if (split[0].contains("GET_TEAMS")) {
            return new GetTeams();
        }
        if (split[0].contains("ADD_ITEM")) {
            return new AddItem();
        }
        if (split[0].contains("DEL_ITEM")) {
            return new DelItem();
        }
        if (split[0].contains("UPDATE_ITEM")) {
            return new UpdateItem();
        }
        if (split[0].contains("ADD_TEAM")) {
            return new AddTeam();
        }
        if (split[0].contains("DEL_TEAM")) {
            return new DelTeam();
        }
        if (split[0].contains("UPDATE_USER")) {
            return new UpdateUser();
        }
        if (split[0].contains("ADD_GROUP")) {
            return new AddGroup();
        }
        if (split[0].contains("DEL_GROUP")) {
            return new DelGroup();
        }
        if (split[0].contains("UPDATE_GROUP")) {
            return new UpdateGroup();
        }
        if (split[0].contains("GET_USER_ITEMS")) {
            return new GetUserItems();
        }
        if (split[0].contains("GET_USER_TEAMS")) {
            return new GetUserTeams();
        }
        if (split[0].contains("GET_PM_TEAMS")) {
            return new GetPMTeams();
        }
        if (split[0].contains("GET_TEAM_USERS")) {
            return new GetTeamUsers();
        }
        if (split[0].contains("USER_IN_TEAM")) {
            return new UserInTeam();
        }
        if (split[0].contains("USER_OUT_TEAM")) {
            return new UserOutTeam();
        }
        if (split[0].contains("SET_TEAM_CONFIRMED")) {
            return new SetTeamConfirmed();
        }
        if (split[0].contains("UPDATE_TEAM")) {
            return new UpdateTeam();
        }
        if (split[0].contains("SET_ITEM_STATE")) {
            return new SetItemState();
        }
        return null;
    }

    public static class Login extends Worker {

        @Override
        public String process() {
            if (split.length != 3) {
                return "KO Wrong requirement!";
            }
            return dbc.login(split[1], split[2]);
        }
    }

    public static class GetInfo extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.getInfo(split[1]);
        }
    }

    public static class GetUsers extends Worker {

        @Override
        public String process() {
            if (split.length != 1) {
                return "KO Wrong requirement!";
            }
            return dbc.getUsers();
        }
    }

    public static class AddUser extends Worker {

        @Override
        public String process() {
            if (split.length != 4) {
                return "KO Wrong requirement!";
            }
            return dbc.addUser(Integer.valueOf(split[2]), split[3], null, null, null, null, null, null, Integer.valueOf(split[1]));
        }
    }

    public static class DelUser extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.deleteUser(split[1]);
        }
    }

    public static class ChangePass extends Worker {

        @Override
        public String process() {
            if (split.length != 4) {
                return "KO Wrong requirement!";
            }
            return dbc.changePass(split[1], split[2], split[3]);
        }
    }

    public static class AdminChangePass extends Worker {

        @Override
        public String process() {
            if (split.length != 3) {
                return "KO Wrong requirement!";
            }
            return dbc.adminChangePass(split[1], split[2]);
        }
    }

    public static class GetItems extends Worker {

        @Override
        public String process() {
            if (split.length != 1) {
                return "KO Wrong requirement!";
            }
            return dbc.getItems();
        }
    }

    public static class GetTeams extends Worker {

        @Override
        public String process() {
            if (split.length != 1) {
                return "KO Wrong requirement!";
            }
            return dbc.getTeams();
        }
    }

    public static class GetUserItems extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.getUserItems(split[1]);
        }
    }

    public static class GetUserTeams extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.getUserTeams(split[1]);
        }
    }

    public static class GetGroups extends Worker {

        @Override
        public String process() {
            if (split.length != 1) {
                return "KO Wrong requirement!";
            }
            return dbc.getGroups();
        }
    }

    public static class AddItem extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.addItem(split[1]);
        }
    }

    public static class DelItem extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.delItem(split[1]);
        }
    }

    public static class UpdateItem extends Worker {

        @Override
        public String process() {
            if (split.length != 3) {
                return "KO Wrong requirement!";
            }
            return dbc.updateItem(split[1], split[2]);
        }
    }

    public static class UpdateUser extends Worker {

        @Override
        public String process() {
            if (split.length != 9) {
                return "KO Wrong requirement!";
            }
            return dbc.updateUser(split[1], split[2], split[3], split[4], split[5], split[6], split[7], split[8]);
        }
    }

    public static class AddTeam extends Worker {

        @Override
        public String process() {
            if (split.length != 6) {
                return "KO Wrong requirement!";
            }
            return dbc.addTeam(split[1], split[2], split[3], split[4], split[5]);
        }
    }

    public static class UpdateTeam extends Worker {

        @Override
        public String process() {
            if (split.length != 7) {
                return "KO Wrong requirement!";
            }
            return dbc.updateTeam(split[1], split[2], split[3], split[4], split[5], split[6]);
        }
    }

    public static class DelTeam extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.delTeam(split[1]);
        }
    }

    public static class UserInTeam extends Worker {

        @Override
        public String process() {
            if (split.length != 3) {
                return "KO Wrong requirement!";
            }
            return dbc.userInTeam(split[1], split[2]);
        }
    }

    public static class UserOutTeam extends Worker {

        @Override
        public String process() {
            if (split.length != 3) {
                return "KO Wrong requirement!";
            }
            return dbc.userOutTeam(split[1], split[2]);
        }
    }

    public static class SetTeamConfirmed extends Worker {

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

    public static class SetItemState extends Worker {

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

    public static class AddGroup extends Worker {

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

    public static class UpdateGroup extends Worker {

        @Override
        public String process() {
            if (split.length != Integer.valueOf(split[3]) + 4) {
                return "KO Wrong requirement!";
            }
            int[] items = new int[Integer.valueOf(split[3])];
            for (int k = 0; k < items.length; k++) {
                items[k] = Integer.valueOf(split[4 + k]);
            }
            return dbc.updateGroup(split[1], split[2], items);
        }
    }

    public static class DelGroup extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.delGroup(split[1]);
        }
    }

    public static class GetPMTeams extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.getPMTeams(split[1]);
        }
    }

    public static class GetTeamUsers extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.getTeamUsers(split[1]);
        }
    }
}