package vehicleRecords;

import static java.lang.Class.forName;
import java.sql.*;

import java.sql.Connection;
public class DBConnection {

    private static final DBConnection INSTANCE = new DBConnection();
    private Connection con = null;

    private DBConnection() { connect();}

    public static DBConnection getInstance() { return INSTANCE;}

    private Connection connect()
    {
        if(con !=null)
            return con;
        try
        {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("JDBC:sqlite:GM_SIS.db");
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
            Statement stmt = getInstance().con
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