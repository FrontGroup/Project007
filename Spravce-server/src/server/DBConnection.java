package server;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beretis
 */
public class DBConnection implements DBCInt
{

    private static DBConnection instance = null;
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    String db_address;
    String[] arguments = null;
    ResultSetMetaData meta = null;
    int columncount;
    String sql;

    public DBConnection(String db_address)
    {
        this.db_address = db_address;
    }

    public String connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex)
        {
            //System.out.println(ex.getMessage());
            return "KO MySQL driver failed!";
        }
        try
        {
            connect = DriverManager.getConnection(db_address);
            return "OK";
        }
        catch (Exception ex)
        {
            //System.out.println(ex.getMessage());
            return "KO Connect to DB failed!";
        }
    }

    public void disConnect()
    {
        try
        {
            connect.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String login(String id, String pass)
    {
        try
        {
            int role = 0;
            String query = "SELECT role FROM Users WHERE id='" + id + "' and pass='" + pass + "'"; // vytviry SQL dotaz
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                role = resultSet.getInt("role");
            }
            switch (role)
            {
                case 0:
                {
                    return "KO WRONG ID!";
                }
                case 1:
                {
                    return "ADMIN";
                }
                case 2:
                {
                    return "MANAGER";
                }
                case 3:
                {
                    return "HR";
                }
                case 4:
                {
                    return "EMPLOYEE";
                }
                default:
                    return "KO";
            }
        }
        catch (SQLException ex)
        {
            //System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    public String addUser(String id, int Groups_idGroups, String pass, int role)
    {
        try
        {
            String query = "INSERT into Users (id, Groups_idGroups, pass, role)  VALUES ('" + id + "','" + Groups_idGroups + "', '" + pass + "', '" + role + "')";
            statement = connect.createStatement();
            int rows_effected = statement.executeUpdate(query);
            if (rows_effected == 0)
            {
                return "KO NEW USER didn't save!";
            }
            return id;
        }
        catch (Exception ex)
        {
            //System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String addUser(int Groups_idGroups, String pass, int role)
    {
        try
        {
            String query = "INSERT into Users (Groups_idGroups, pass, role)  VALUES ('" + Groups_idGroups + "', '" + pass + "', '" + role + "')";
            statement = connect.createStatement();
            int rows_effected = statement.executeUpdate(query);
            if (rows_effected == 0)
            {
                return "KO NEW USER didn't save!";
            }
            return lastUsedId();
        }
        catch (Exception ex)
        {
            //System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String changePass(String id, String oldpass, String newpass)
    {
        String query = "UPDATE Users SET pass = '" + newpass + "' WHERE id ='" + id + "' and pass ='" + oldpass + "'";
        try
        {
            statement = connect.createStatement();
            int rows_effected = statement.executeUpdate(query);
            if (rows_effected != 0)
            {
                return "OK";
            }
            else
            {
                return "KO ID/PASS is wrong!";
            }
        }
        catch (Exception ex)
        {
            //System.out.println(ex.getMessage());
            return "KO Error in database!";
        }

    }

    private void close()
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }
            if (statement != null)
            {
                statement.close();
            }
            if (connect != null)
            {
                connect.close();
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String deleteUser(String id)
    {
        try
        {
            String query = "DELETE from Users WHERE id = '" + id + "'";
            statement = connect.createStatement();
            int delete = statement.executeUpdate(query);
            if (delete == 0)
            {
                return "KO User's ID non-existent!";
            }
            query = "DELETE from Teams_has_Users WHERE Users_id = '" + id + "'";
            statement.executeUpdate(query);
            query = "DELETE from Users_has_Items WHERE Users_id = '" + id + "'";
            statement.executeUpdate(query);
            return "OK";
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String adminChangePass(String id, String newpass)
    {
        String query = "UPDATE Users SET pass = '" + newpass + "' WHERE id ='" + id + "'";
        try
        {
            statement = connect.createStatement();
            int rows_effected = statement.executeUpdate(query);
            if (rows_effected != 0)
            {
                return "OK";
            }
            else
            {
                return "KO ID/PASS IS WRONG!";
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String getInfo(String id)
    {
        String query = "Select * from Users where id=" + id;
        try
        {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            String result = "";
            while (resultSet.next())
            {
                result = resultSet.getInt(10) + ";"
                        + resultSet.getInt(2) + ";"
                        + resultSet.getString(4) + ";"
                        + resultSet.getString(5) + ";"
                        + resultSet.getString(6) + ";"
                        + resultSet.getString(7) + ";"
                        + resultSet.getString(8) + ";"
                        + resultSet.getString(9) + ";"
                        + resultSet.getString(11);
            }
            return result;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String getGroups()
    {
        try
        {
            String result = "";
            String sql1 = "Select * from Groups";
            String sql2 = "Select Items_id from Groups_has_Items where Groups_idGroups=";
            Statement statement1 = connect.createStatement();
            Statement statement2 = connect.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(sql1);

            while (resultSet1.next())
            {
                result += resultSet1.getInt(1) + " " + resultSet1.getString(2);
                ResultSet resultSet2 = statement2.executeQuery(sql2 + resultSet1.getInt(1));
                String s = "";
                int size = 0;
                while (resultSet2.next())
                {
                    s += " " + resultSet2.getInt(1);
                    size++;
                }
                result += " " + size + s + ";";
            }
            return result;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    public String simpleShowAllFrom(String from)
    {
        try
        {
            String result = "";
            sql = "Select * from " + from;
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
            meta = resultSet.getMetaData();
            columncount = meta.getColumnCount();
            while (resultSet.next())
            {
                result += resultSet.getInt(1) + " " + resultSet.getString(2) + ";";
            }
            return result;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    public String lastUsedId()
    {
        try
        {
            String query = "SELECT LAST_INSERT_ID()";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            if (!resultSet.next())
            {
                return "KO Can't get last ID!";
            }
            String new_id = resultSet.getString(1);
            return new_id;
        }
        catch (SQLException ex)
        {
            return "KO Error in database!";
        }
    }

    public String executeSql(String sql)
    {
        String low_sql = sql.toLowerCase();
        if (low_sql.startsWith("select"))
        {
            try
            {
                String result = "";
                statement = connect.createStatement();
                resultSet = statement.executeQuery(sql);
                meta = resultSet.getMetaData();
                columncount = meta.getColumnCount();
                while (resultSet.next())
                {
                    for (int i = 0; i < columncount; i++)
                    {
                        result = meta.getColumnName(i) + ":" + resultSet.getNString(i) + ";";
                    }
                }
                return result;
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
                return "KO Error in database!";
            }
        }
        else if (low_sql.startsWith("delete"))
        {
            try
            {
                statement = connect.createStatement();
                int rows_effected = statement.executeUpdate(sql);
                if (rows_effected != 0)
                {
                    return "OK";
                }
                else
                {
                    return "KO element does not exist!";
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
                return "KO Error in database!";
            }
        }
        else if (low_sql.startsWith("insert") || low_sql.startsWith("replace"))
        {
            try
            {
                statement = connect.createStatement();
                int rows_effected = statement.executeUpdate(sql);
                if (rows_effected != 0)
                {
                    return "OK";
                }
                else
                {
                    return "KO Wrong input!";
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
                return "KO Error in database!";
            }
        }
        else if (low_sql.startsWith("update"))
        {
            try
            {
                statement = connect.createStatement();
                int rows_effected = statement.executeUpdate(sql);
                if (rows_effected != 0)
                {
                    return "OK";
                }
                else
                {
                    return "KO Wrong input!";
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
                return "KO Error in database!";
            }
        }
        else
        {
            System.out.println("Wrong sql");
            return "KO Error in sql exception!";
        }
    }

    @Override
    public String getItems()
    {
        try
        {
            String result = "";
            sql = "Select * from Items";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                result += resultSet.getInt(1) + " " + resultSet.getString(2) + ";";
            }
            return result;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String getTeams()
    {
        try
        {
            String result = "";
            sql = "Select * from Teams";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                result += resultSet.getInt(1) + " "
                        + resultSet.getString(7) + " "
                        + resultSet.getString(2) + " "
                        + resultSet.getString(6) + " "
                        + resultSet.getString(3) + " "
                        + resultSet.getString(4) + " "
                        + resultSet.getString(5) + ";";
            }
            return result;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String getUserItems(String idUser)
    {
        try
        {
            String result = "";
            sql = "Select * from Users_has_Items where Users_id=" + idUser;
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                result += resultSet.getInt(2) + " " + resultSet.getInt(3) + ";";
            }
            return result;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String getUserTeams(String idUser)
    {
        try
        {
            String result = "";
            sql = "Select * from Teams_has_Users where Users_id=" + idUser;
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                result += resultSet.getInt(1) + " " + resultSet.getInt(3) + ";";
            }
            return result;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String addItem(String name)
    {
        sql = "Insert into Items (name) values('" + name + "')";
        return executeSql(sql);
    }

    public String getIdOf(String table, String element)
    {
        String result = "";
        if (table.equals("Groups"))
        {
            try
            {
                sql = "select idGroups from " + table + " where name = '" + element + "'";
                statement = connect.createStatement();
                resultSet = statement.executeQuery(sql);
                while(resultSet.next())
                {
                result += resultSet.getInt(1);
                }
                if(result.isEmpty())
                {
                    return "KO element with id does not exist";
                }
                return result;
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
                return "KO";
            }

        }
        else
        {
            try
            {
                sql = "select id from " + table + " where name = '" + element + "'";
                statement = connect.createStatement();
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                result += resultSet.getInt(1);
                return result;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return "KO";
            }
        }
    }

    @Override
    public String addTeam(String pm, String name, String project, String info, String goal)
    {
        sql = "Insert into Teams (pm,name,project,info,goal) values('" + pm + "','" + name + "','" + project + "','" + info + "','" + goal + "')";
        return executeSql(sql);
    }

    @Override
    public String addGroup(String name, int[] idItems)
    {
        try
        {
            sql = "Insert into Groups (name) values('" + name + "')";
            if (!executeSql(sql).equals("OK"))
            {
                return "KO Error while adding group!";
            }
            String idNew = lastUsedId();
            if (!updateGroup(idNew, name, idItems).equals("OK"))
            {
                return updateGroup(idNew, name, idItems);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
        return "OK";
    }

    @Override
    public String delTeam(String id)
    {
        sql = "Delete from Teams where id='" + id + "'";
        if (!executeSql(sql).equals("OK"))
        {
            return "KO Error while deleting team!";
        }
        sql = "Delete from Teams_has_Users where Teams_id='" + id + "'";
        executeSql(sql);

        return "OK";
    }

    @Override
    public String delGroup(String id)
    {
        sql = "Delete from Groups_has_Items where Groups_idGroups='" + id + "'";
        if (!executeSql(sql).equals("OK"))
        {
            return "KO Error while deleting group's binding!";
        }
        sql = "Delete from Groups where idGroups='" + id + "'";
        if (!executeSql(sql).equals("OK"))
        {
            return "KO Error while deleting group!";
        }
        return "OK";
    }

    public String itemToUser(String user_id, String item_id)
    {
        sql = "Replace into Users_has_Items (Users_id, Items_id, state) values('" + user_id + "', '" + item_id + "', '1')";
        return executeSql(sql);
    }

    @Override
    public String delItem(String id)
    {
        sql = "Delete from Items where id='" + id + "'";
        return executeSql(sql);
    }

    @Override
    public String updateUser(String id, String name, String lastname, String address, String city, String email, String phone, String professia)
    {
        String query = "Update Users set name = '" + name + "'," + "lastname = '" + lastname + "'," + "address = '" + address + "',"
                + "city = '" + city + "'," + "email = '" + email + "'," + "phone = '" + phone + "'," + "professia = '" + professia + "' where id = '" + id + "'";
        return executeSql(query);
    }

    public String updateTeam(String id, String pm, String name, String project, String info, String goal)//
    {
        String query = "Update Teams set pm = '" + pm + "'," + "name = '" + name + "'," + "project = '" + project + "',"
                + "info = '" + info + "'," + "goal = '" + goal + "' where id = '" + id + "'";
        return executeSql(query);
    }

    @Override
    public String updateGroup(String id, String name, int[] idItems)
    {
        try
        {
            sql = "Update Groups set name='" + name + "' where idGroups='" + id + "'";
            if (!executeSql(sql).equals("OK"))
            {
                return "KO Error while updating Group's name!";
            }
            sql = "Delete FROM Groups_has_Items where Groups_idGroups='" + id + "'";
            statement = connect.createStatement();
            int delete = statement.executeUpdate(sql);
            for (int i = 0; i < idItems.length; i++)
            {
                sql = "Insert into Groups_has_Items values ('" + id + "','" + idItems[i] + "')";
                if (!executeSql(sql).equals("OK"))
                {
                    return "KO Error while updating Group's Items!";
                }
            }
            return "OK";
        }
        catch (SQLException ex)
        {
            return "KO Error while updating Group's Items!";
        }
    }

    @Override
    public String updateItem(String id, String name)
    {
        sql = "Update Items set name='" + name + "' WHERE id='" + id + "'";
        return executeSql(sql);
    }

    @Override
    public String userInTeam(String idUser, String idTeam)
    {
        sql = "Replace into Teams_has_Users (Teams_id, Users_id) values('" + idTeam + "', '" + idUser + "')";
        //System.out.println(sql);
        return executeSql(sql);
    }

    @Override
    public String userOutTeam(String idUser, String idTeam)
    {
        sql = "Delete from Teams_has_Users where Teams_id = '" + idTeam + "' and Users_id = '" + idUser + "'";
        return executeSql(sql);
    }

    @Override
    public String setTeamConfirmed(String idUser, String idTeam, boolean confirmed)
    {
        int x = confirmed ? 1 : 0;
        sql = "Update Teams_has_Users set confirmed='" + x + "' WHERE Users_id='" + idUser + "' and Teams_id='" + idTeam + "'";
        //System.out.println(sql);
        return executeSql(sql);
    }

    @Override
    public String setItemState(String idUser, String idItem, boolean state)
    {
        int x = state ? 1 : 0;
        sql = "Update Users_has_Items set state='" + x + "' WHERE Users_id='" + idUser + "' and Items_id='" + idItem + "'";
        //System.out.println(sql);
        return executeSql(sql);
    }

    @Override
    public String getUsers()
    {
        try
        {
            String result = "";
            sql = "Select id from Users";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                result += resultSet.getInt(1) + ";";
            }
            return result;
        }
        catch (SQLException ex)
        {
            //System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String getPMTeams(String idPM)
    {
        try
        {
            String result = "";
            sql = "Select * from Teams where pm='" + idPM + "'";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                result += resultSet.getInt(1) + " "
                        + resultSet.getString(7) + " "
                        + resultSet.getString(2) + " "
                        + resultSet.getString(6) + " "
                        + resultSet.getString(3) + " "
                        + resultSet.getString(4) + " "
                        + resultSet.getString(5) + ";";
            }
            return result;
        }
        catch (SQLException ex)
        {
            //System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    @Override
    public String getTeamUsers(String idTeam)
    {
        try
        {
            String result = "";
            sql = "Select * from Teams_has_Users where Teams_id=" + idTeam;
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                result += resultSet.getInt(2) + " " + resultSet.getInt(3) + ";";
            }
            return result;
        }
        catch (SQLException ex)
        {
            //System.out.println(ex.getMessage());
            return "KO Error in database!";
        }
    }

    public String[] getArguments()
    {
        return arguments;
    }

    public Connection getConnect()
    {
        return connect;
    }

    public String getDb_address()
    {
        return db_address;
    }

    public ResultSet getResultSet()
    {
        return resultSet;
    }

    public Statement getStatement()
    {
        return statement;
    }

    public void setArguments(String[] arguments)
    {
        this.arguments = arguments;
    }

    public void setDb_address(String db_address)
    {
        this.db_address = db_address;
    }

    @Override
    public String updateTeam(String id, String pm, String name, String project, String info, String goal, String active) {
        String query = "Update Teams set pm = '" + pm + "'," + "name = '" + name + "'," + "project = '" + project
                + "'," + "info = '" + info + "'," + "goal = '" + goal
                + "'," + "active = '" + active + "' where id = '" + id + "'";
        return executeSql(query);
    }
}