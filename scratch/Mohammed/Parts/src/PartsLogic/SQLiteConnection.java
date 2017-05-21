package PartsLogic;

import java.sql.*;

/**
 * Created by MohammedKhan on 16/03/2017.
 */
public class SQLiteConnection {

    private static final SQLiteConnection INSTANCE = new SQLiteConnection();
    public Connection con = null;

    private SQLiteConnection() { connect();}

    public static SQLiteConnection getInstance() { return INSTANCE;}

    public  Connection connect()
    {
        if(con !=null)
            return con;
        try
        {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("JDBC:sqlite:/Users/MohammedKhan/IdeaProjects/Test/src/sample/GM_SIS.db");
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
            Statement stmt = con.createStatement();
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

