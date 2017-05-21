package common.logic;

import java.sql.DriverManager ;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet ;

public class databconenction {

    private static final databconenction INSTANCE = new databconenction();
    public Connection con = null;

    public databconenction() { connect();}

    public static databconenction getInstance() { return INSTANCE;}

    public Connection connect()
    {
        if(con !=null)
            return con;
        try
        {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("JDBC:sqlite:C:\\Users\\Don\\Documents\\SE25-test\\scratch\\Donovan\\sample\\GM_SIS_02.db");
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
        } catch (NullPointerException ignored){
        }
    }


}

