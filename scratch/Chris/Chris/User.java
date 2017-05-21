package Chris;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Christopher Chrysostomou on 24/02/2017.
 */
public class User {
    private StringProperty username;
    private StringProperty password;
    private StringProperty adminStatus;
    private IntegerProperty userID;

    public User(ResultSet rs) {
        try {
            this.userID = new SimpleIntegerProperty(rs.getInt("userID"));
            this.username = new SimpleStringProperty(rs.getString("username"));
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

    public IntegerProperty UserIDProperty() {
        return userID;
    }

    public void setSPCID(Integer userID) {
        this.userID.set(userID);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
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
