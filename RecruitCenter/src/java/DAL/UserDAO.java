/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author dell
 */
public class UserDAO {

    Connection conn = null;

    public User getUser(String ID) {
        User user = null;
        try {
            String query = "SELECT * FROM Users WHERE ID = ?";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                user = new User(rs.getString("ID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("Address"), rs.getInt("AccountType"));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return user;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE Users SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Address = ?, AccountType = ? WHERE ID = ?";
        int line = 0;
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getFname());
            ps.setString(2, user.getLname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhonenum());
            ps.setString(5, user.getAddress());
            ps.setInt(6, user.getAccType());
            ps.setString(7, user.getUserID());
            line = ps.executeUpdate(); //rows affected
            ps.close();
            conn.close();
        } catch (Exception e) {
            
        }
        return line == 1; //succeded or not
    }
}
