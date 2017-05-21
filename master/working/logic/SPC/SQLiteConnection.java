/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.logic;

import static java.lang.Class.forName;
import java.sql.*;

import java.sql.Connection;
public class SQLiteConnection {

    private static final SQLiteConnection INSTANCE = new SQLiteConnection();
private Connection con = null;

private SQLiteConnection() { connect();}

public static SQLiteConnection getInstance() { return INSTANCE;}

private Connection connect()
{
    if(con !=null)
        return con;
    try
    {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("JDBC:sqlite:src/common/logic/GM_SIS.db");
    } catch (ClassNotFoundException e)
    {
        System.out.println("Driver not found!");
        e.getException();
    } catch (SQLException se){
        System.out.println("NO CONNECTION");
        se.getMessage();
    }
    return con;
}
public ResultSet query(String sql)
{
    ResultSet rs = null;
    try
    {
        Statement stmt = con
                .createStatement();
        rs = stmt.executeQuery(sql);
    }
    catch (SQLException se)
    {
        se.printStackTrace();
    }
    return rs;
}

    public void update(String sql){
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException se){
            se.printStackTrace();
        } catch (NullPointerException ne){
        }
    }

}
