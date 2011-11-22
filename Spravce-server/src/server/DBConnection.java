/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
class DBConnection
{

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    String db_address;
    String db_username;
    String db_psswd;
    String[] arguments = null;

    public DBConnection(String db_address, String db_username, String db_psswd)
    {
        this.db_address = db_address;
        this.db_username = db_username;
        this.db_psswd = db_psswd;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e)
        {
            System.out.println("Nepodarilo sa nacitat MySQL driver");
        }
        try
        {
            connect = DriverManager.getConnection(db_address, db_username, db_psswd);
        }
        catch (Exception e)
        {
            System.out.println("Pripojenie zlyhalo");
        }

    }

    public DBConnection()
    {
    }

    public String login(String id, String pass)
    {
        try
        {
            //metoda vraci ADMIN/MANAGER/HR/EMPLOYEE/KO
            int role = 0;
            String query = "SELECT role FROM Users WHERE id='" + id + "' and pass='" + pass + "'"; // vytviry SQL dotaz
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) //Vypise vysledok dotazu
            {
                role = resultSet.getInt("role");
            }
            if (role == 0) //jestli je role prazdna, v tabulke neexistuje uzivatel so zadanym id a heslom
            {
                System.out.println("Neplatne id nebo heslo");
            }
            switch (role)
            {
                case 0:
                {
                    return "KO";
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
                default:
                    return "KO";
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Chyba v metode login v DBConnect");
            return "";
        }
    }

    public String getInfo(String id) //1. verzia bez filtru vracia vsetky podstatne hodnoty
    {
        //metoda vraci atributy profilu KLIC HODNOTA;KLIC HODNOTA;...
        try
        {
            String result = "";
            String SQL = "SELECT * FROM Users WHERE id='" + id + "'";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next())
            {
                result = resultSet.getString("meno") + " : " + resultSet.getString("rasa");
            }
            if (result.equals(""))
            {
                System.out.println("Uzivatel so zadanym id neexistuje");
                return result;
            }
            return result;
        }
        catch (Exception e)
        {
            System.out.println("SQL Exception: " + e.toString());
            return "";
        }

    }
// metoda na pridavanie uzivatela ktore je pretazena, je mozne ju volat s menej parametrami

    public boolean addUser(String name, String pass, int role, String lastname,
            String address, String city, String email, String phone, int group_id)
    {
        try
        {
            String query = "INSERT into Users (name, pass, role, lastname, address, city, email, phone, group_id)  VALUES ('" + name + "', '" + role + "', '" + lastname
                    + "', '" + address + "', '" + city + "', '" + email + "', '" + phone
                    + "', '" + group_id + "')";
            statement = connect.createStatement();
            int rows_effected = statement.executeUpdate(query);
            if (rows_effected != 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            System.out.println("Chyba v metode addUser");
            return false;
        }
    }


// metoda ktora meni heslo uzivatela identifikovaneho pomocou ID

    public boolean changePass(int id, String pass)
    {
        String query = "UPDATE Users SET pass = '" + pass + "' WHERE id = '" + id + "'";
        try
        {
            statement = connect.createStatement();
            int rows_effected = statement.executeUpdate(query);
            if (rows_effected != 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            System.out.println("Chyba v metode addUser");
            return false;
        }

    }
// metoda ktora uzavre resultset

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
        catch (Exception e)
        {
        }
    }
// potrebujem si vyjasnit niektore veci v tabulke

    public void deleteUser(int id, String jmeno)
    {
        try
        {
            String query = "DELETE from Users WHERE id = '" + id + "'";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            query = "DELETE from Teams_has_Users WHERE Users_id = '" + id + "'";
            resultSet = statement.executeQuery(query);
            query = "DELETE from Users_have_Items WHERE Users_id = '" + id + "'";
            resultSet = statement.executeQuery(query);
        }
        catch (Exception ex)
        {
            System.out.println("chyba v metode deleteUser v triede DBConnection");        }
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

    public String getPsswd()
    {
        return db_psswd;
    }

    public ResultSet getResultSet()
    {
        return resultSet;
    }

    public Statement getStatement()
    {
        return statement;
    }

    public String getUsername()
    {
        return db_username;
    }

    public void setArguments(String[] arguments)
    {
        this.arguments = arguments;
    }

    public void setDb_address(String db_address)
    {
        this.db_address = db_address;
    }

    public void setPsswd(String psswd)
    {
        this.db_psswd = psswd;
    }

    public void setUsername(String username)
    {
        this.db_username = username;
    }

    String changePass(String id, String oldPass, String newPass) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    String adminChangePass(String id, String newPass) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    String addUser(String name, String lastname, int role, int group, String pass) {
        //return id
        throw new UnsupportedOperationException("Not yet implemented");
    }

    String delUser(String id) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
