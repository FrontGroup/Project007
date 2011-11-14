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
    String username;
    String psswd;
    String[] arguments = null;

    public DBConnection(String db_address, String username, String psswd)
    {
        this.db_address = db_address;
        this.username = username;
        this.psswd = psswd;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
            System.out.println("Nepodarilo sa nacitat MySQL driver");
        }
        try
        {
            connect = DriverManager.getConnection(db_address, username, psswd);
        } catch (Exception e)
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
        } catch (SQLException ex)
        {
            System.out.println("Chyba v metode login v DBConnect");
            return "";
        }
    }

    String getInfo(String id) //1. verzia bez filtru vracia vsetky podstatne hodnoty
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
                result =resultSet.getString("meno") + " : " + resultSet.getString("rasa");
            }
            if(result.equals(""))
            {
                System.out.println("Uzivatel so zadanym id neexistuje");
                return result;
            }
            return result;
        } catch (Exception e)
        {
            System.out.println("SQL Exception: " + e.toString());
            return "";
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

    public String getPsswd()
    {
        return psswd;
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
        return username;
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
        this.psswd = psswd;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    
}
