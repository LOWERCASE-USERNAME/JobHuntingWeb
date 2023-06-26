/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Account;
import model.User;
import java.lang.Exception;
/**
 *
 * @author dell
 */
public class UserDAO {

    Connection conn = null;

    public ArrayList<User> getListUser() {
	try {
		String query = "SELECT * FROM Users";
		conn = new DBContext().getConnection();
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		ArrayList<User> list = new ArrayList<>();
		while(rs.next()) {
                    User user = new User(UUID.fromString(rs.getString("ID")), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("Address"), rs.getInt("AccountType"));
                    list.add(user);
            }
            return list;

        } catch (Exception e){
            System.out.println(e);
        }
        return null; 
    }
    
    public User getUserWithID(UUID ID) {
        User user = null;
        try {
            String query = "SELECT * FROM Users WHERE ID = CAST(? AS uniqueidentifier)";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ID.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(UUID.fromString(rs.getString("ID")), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("Address"), rs.getInt("AccountType"));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return user;
    }

    public User getUserWithEmail(String email) {
        User user = null;
        try {
            String query = "SELECT * FROM Users WHERE Email = ?";
            conn = new DBContext().getConnection();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    user = new User(UUID.fromString(rs.getString("ID")), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("Address"), rs.getInt("AccountType"));
                }
                rs.close();
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }
    
    public boolean updateUser(User user){
        String query = "UPDATE Users SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Address = ?, CurrentCompany = ?, AccountType = ? WHERE ID = CAST(? AS uniqueidentifier)";
//        String query = "UPDATE Users SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Address = ?, AccountType = ?";
        int line = 0;
        try {
        conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, user.getFname());
        ps.setString(2, user.getLname());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPhonenum());
        ps.setString(5, user.getAddress());
        ps.setString(6, user.getCurrentCompany());
        ps.setInt(7, user.getAccType());
        ps.setString(8, user.getUserID().toString());
        line = ps.executeUpdate();
        ps.close();
        conn.close();
        } catch (Exception e) {
            
        }
        return line == 1; //succeded or not
    }
    
    public boolean initUserWithEmail(String email) {
        String insertQuery = "INSERT INTO Users(Email) VALUES(?)";
        int line = 0;
        if(email == null){
            return false;
        }
        try {
            conn = new DBContext().getConnection();
            try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
                insertStatement.setString(1, email);
                line = insertStatement.executeUpdate();
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line == 1;
    }
    
    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
        ArrayList<User> us = ud.getListUser();
        for (User u : us) {
            System.out.println(u);
        }
    }
}
