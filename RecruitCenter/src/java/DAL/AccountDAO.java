/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class AccountDAO {

    Connection conn = null;
//    public ArrayList<Account> getListAccount() {
//        try {
//            String query = "SELECT * FROM Accounts";
//            conn = new DBContext().getConnection();
//            ps = conn.prepareStatement(query);
//            rs = ps.executeQuery();
//            ArrayList<Account> list = new ArrayList<>();
//            while (rs.next()) {
//                Account a = new Account(rs.getString(2), rs.getString(3));
//                list.add(a);
//                System.out.println(a);
//            }
//            return list;
//
//        } catch (Exception e) {
//        }
//        return null;
//    }
    
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
    
//    public void initAccount(Account acc){
//        try {
//            String insertQuery = "INSERT INTO Accounts(userID, ) VALUES(?)";
//            conn = new DBContext().getConnection();
//            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
//            insertStatement.setString(1, email);
//            if (insertStatement.executeUpdate() == 1) {
//                
//            }
//        } catch (Exception e) {
//
//        }
//    }
    
//    public boolean updateAccount(Account acc){
//        try {
//            String insertQuery = "INSERT INTO Users(Email) VALUES(?)";
//            conn = new DBContext().getConnection();
//            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
//            insertStatement.setString(1, email);
//            if (insertStatement.executeUpdate() == 1) {
//                return true;
//            }
//        } catch (Exception e) {
//
//        }
//        return false;
//    }
    
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
        } catch (Exception e) {

        }
        return userID;
    }
    
}