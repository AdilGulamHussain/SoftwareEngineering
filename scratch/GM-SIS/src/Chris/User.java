package Chris;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Christopher Chrysostomou on 24/02/2017.
 */
public class User {
    private IntegerProperty userID;
    private IntegerProperty username;
    private StringProperty password;
    private StringProperty adminStatus;

    public User(ResultSet rs) {
        try {
            this.userID = new SimpleIntegerProperty(rs.getInt("userID"));
            this.username = new SimpleIntegerProperty(rs.getInt("UserName"));
            this.password = new SimpleStringProperty(rs.getString("Password"));
            this.adminStatus = new SimpleStringProperty(rs.getString("adminStatus"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Integer getUserID() {
        return userID.get();
    }

    public IntegerProperty userIDProperty() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID.set(userID);
    }

    public Integer getUsername() {
        return username.get();
    }

    public IntegerProperty usernameProperty() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAdminStatus() {
        return adminStatus.get();
    }

    public StringProperty adminStatusProperty() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {

        this.adminStatus.set(adminStatus);
    }

    
}
