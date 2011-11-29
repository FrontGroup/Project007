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

        if (msg.startsWith("LOGIN")) {
            return new login();
        }
        if (msg.startsWith("INFO")) {
            return new info();
        }
        if (msg.startsWith("ADD")) {
            return new add();
        }
        if (msg.startsWith("DEL")) {
            return new del();
        }
        if (msg.startsWith("CHANGE_PASS")) {
            return new changePass();
        }
        if (msg.startsWith("ADMIN_CHANGE_PASS")) {
            return new adminChangePass();
        }
        if (msg.startsWith("GET_GROUPS")) {
            return new getGroups();
        }
        if (msg.startsWith("GET_ITEMS")) {
            return new getItems();
        }
        if (msg.startsWith("ADD_ITEM")) {
            return new addItem();
        }
        if (msg.startsWith("UPDATE")) {
            return new update();
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

    public static class info extends Worker {

        @Override
        public String process() {
            if (split.length != 2) {
                return "KO Wrong requirement!";
            }
            return dbc.getInfo(split[1]);
        }
    }

    public static class add extends Worker {

        @Override
        public String process() {
            if (split.length != 4) {
                return "KO Wrong requirement!";
            }
            return dbc.addUser(Integer.valueOf(split[2]), split[3], null, null, null, null, null, null, Integer.valueOf(split[1]));
        }
    }

    public static class del extends Worker {

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

    public static class update extends Worker {

        @Override
        public String process() {
            if (split.length != 9) {
                return "KO Wrong requirement!";
            }
            return dbc.updateUser(split[1], split[2], split[3], split[4], split[5], split[6], split[7], split[8]);
        }
    }
}