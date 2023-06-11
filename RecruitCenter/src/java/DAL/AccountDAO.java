/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author dell
 */
public class AccountDAO {

    Connection conn = null;
    public ArrayList<Account> getListAccount() {
        try {
            String query = "SELECT * FROM Accounts";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Account> list = new ArrayList<>();
            while (rs.next()) {
                Account a = new Account(rs.getString(1), rs.getString(2), rs.getString(3));
                list.add(a);
            }
            
            rs.close();
            ps.close();
            conn.close();
            return list;

        } catch (Exception e) {
        }
        return null;
    }
    
    public boolean initUserWithEmail(String email) {
        try {
            String insertQuery = "INSERT INTO Users(Email) VALUES(?)";
            conn = new DBContext().getConnection();
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, email);
            if (insertStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
    

    
    public boolean insertAccount(Account acc){
        int lineAffected = 0;
        try {
            //insert
            String insertQuery = "INSERT INTO Accounts VALUES(?, ?, ?)";
            conn = new DBContext().getConnection();
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, acc.getId());
            insertStatement.setString(2, acc.getUsername());
            insertStatement.setString(3, acc.getPassword());
            lineAffected = insertStatement.executeUpdate();
            //release the resource
            insertStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        //return true if suceeded
        if(lineAffected == 1) return true;
        return false;
    }
    
    public String searchID(String email) {
        String userID = null;
        try {
            String selectQuery = "SELECT ID FROM Users WHERE Email = ?";
            conn = new DBContext().getConnection();
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, email);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                userID = rs.getString(1);
            }
            rs.close();
            selectStatement.close();
            conn.close();
        } catch (Exception e) {

        }
        return userID;
    }
    
}