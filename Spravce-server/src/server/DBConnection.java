package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beretis
 */
class DBConnection
{

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    String db_address;
    String[] arguments = null;

    public DBConnection(String db_address)
    {
        this.db_address = db_address;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
            System.out.println("Nepodarilo sa nacitat MySQL driver");
        }
        try
        {
            connect = DriverManager.getConnection(db_address);
            System.out.println("PODARILO SA PRIPOJIT");
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

    public String getInfo(String id) //1. verzia bez filtru vracia vsetky podstatne hodnoty
    {
        //metoda vraci atributy profilu KLIC HODNOTA;KLIC HODNOTA;...
        String query = "Select * from komplex where id =" + id;
        try
        {
            ArrayList<String> list = new ArrayList<String>();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            ResultSetMetaData meta = resultSet.getMetaData(); //ziska metadata o vysledku aby bolo mozne
            int columncount = meta.getColumnCount();        // urcit pocet stlpcov
            String show_result = "";
            while (resultSet.next())//pre vsetky radky
            {
                for (int i = 1; i < columncount; i++)//pre vsetky stlpce
                {//redukcia identickych informacii
                    if (!show_result.contains(meta.getColumnName(i) + ":" + resultSet.getString(i)))
                    {
                        show_result += meta.getColumnName(i) + ":" + resultSet.getString(i) + ";";
                    }
                }
            }
            return show_result;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Chyba v metode execute_command");
            return "";
        }
    }
// metoda na pridavanie uzivatela ktore je pretazena, je mozne ju volat s menej parametrami

    public String addUser(int Groups_idGroups, String pass, String name, String lastname, String address, String city, String email, String phone, int role)
    {
        try
        {
            String query = "INSERT into Users (Groups_idGroups, pass, name, lastname, address, city, email, phone, role)  VALUES ('" + Groups_idGroups + "', '" + pass + "', '" + name
                    + "', '" + lastname + "', '" + address + "', '" + city + "', '" + email
                    + "', '" + phone + "', '" + role + "')";
            statement = connect.createStatement();
            int rows_effected = statement.executeUpdate(query);
            if (rows_effected != 0)
            {
                return "OK";
            } else
            {
                return "KO nepodarilo sa pridat uzivatela";
            }
        } catch (Exception ex)
        {
            System.out.println("Chyba v metode addUser");
            return "KO chyba v metode";
        }
    }

    public String addUser(int Groups_idGroups, String pass, String name, String lastname, String address, String city, String email,int role)
    {
        return addUser(Groups_idGroups, pass, name, lastname, address, city, email, null,role);
    }

    public String addUser(int Groups_idGroups, String pass, String name, String lastname, String address, String city, int role)
    {
        return addUser(Groups_idGroups, pass, name, lastname, address, city, null, null,role);
    }

    public String addUser(int Groups_idGroups, String pass, String name, String lastname, String address, int role)
    {
        return addUser(Groups_idGroups, pass, name, lastname, address, null, null, null,role);
    }

    public String addUser(int Groups_idGroups, String pass, String name, String lastname, int role)
    {
        return addUser(Groups_idGroups, pass, name, lastname, null, null, null, null,role);
    }
// metoda ktora meni heslo uzivatela identifikovaneho pomocou ID

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
            } else
            {
                return "KO id/heslo je nespravne";
            }
        } catch (Exception ex)
        {
            System.out.println("Chyba v metode addUser");
            return "KO chyba v metode";
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
        } catch (Exception e)
        {
        }
    }
// potrebujem si vyjasnit niektore veci v tabulke

    public String deleteUser(String id)
    {
        try
        {
            String query = "DELETE from Users WHERE id = '" + id + "'";
            statement = connect.createStatement();
            int delete = statement.executeUpdate(query);
            if (delete == 0)
            {
                return "KO User s id" + id + "neexistuje";
            }
            query = "DELETE from Teams_has_Users WHERE Users_id = '" + id + "'";
            int delete2 = statement.executeUpdate(query);
            if (delete2 == 0)
            {
                System.out.println("User nieje clenom ziadneho teamu");
            }
            query = "DELETE from Users_has_Items WHERE Users_id = '" + id + "'";
            int delete3 = statement.executeUpdate(query);
            if (delete3 == 0)
            {
                System.out.println("User nema ziadne Itemi");
            }
            return "OK";
        } catch (Exception ex)
        {
            System.out.println("chyba v metode deleteUser v triede DBConnection");
            return "KO";
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

    String adminChangePass(String id, String newpass)
    {
        String query = "UPDATE Users SET pass = '" + newpass + "' WHERE id ='" + id + "'";
        try
        {
            statement = connect.createStatement();
            int rows_effected = statement.executeUpdate(query);
            if (rows_effected != 0)
            {
                return "OK";
            } else
            {
                return "KO id/heslo je nespravne";
            }
        } catch (Exception ex)
        {
            System.out.println("Chyba v metode addUser");
            return "KO chyba v metode";
        }
    }
}