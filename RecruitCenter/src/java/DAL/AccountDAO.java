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
import java.util.UUID;
import model.Account;

/**
 *
 * @author dell
 */
public class AccountDAO {
    Connection conn = null;
    public ArrayList<Account> getListAccount() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Accounts";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getString(3));
                list.add(a);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return list;
    }

    public boolean insertAccount(Account acc) throws Exception{
        int lineAffected = 0;
        try {
            //insert
            String insertQuery = "INSERT INTO Accounts VALUES(CAST(? as uniqueidentifier), ?, ?)";
            conn = new DBContext().getConnection();
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, acc.getId().toString());
            insertStatement.setString(2, acc.getUsername());
            insertStatement.setString(3, acc.getPassword());
            lineAffected = insertStatement.executeUpdate();
            //release the resource
            insertStatement.close();
            conn.close();
        } catch (Exception e) {
            throw(e);
        } 
        //return true if suceeded
        if(lineAffected == 1) return true;
        return false;
    }
    
    public boolean updateAccount(Account acc){
        String query = "UPDATE Accounts SET username = ?, password = ? WHERE userid = CAST(? as uniqueidentifier)";
        int line = 0;
        try {
        conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(3, acc.getId().toString());
        ps.setString(1, acc.getUsername());
        ps.setString(2, acc.getPassword());
        line = ps.executeUpdate();
        ps.close();
        conn.close();
        } catch (Exception e) {
////            System.out.println(e);
        }
        return line == 1; //succeded or not
    }
    
    public Account getAccountWithUsername(String username) {
        Account acc = null;
        try {
            String selectQuery = "SELECT * FROM Accounts WHERE username = ?";
            conn = new DBContext().getConnection();
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, username);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                acc = new Account(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getString(3)) ;
            }
            rs.close();
            selectStatement.close();
            conn.close();
        } catch (Exception e) {

        }
        return acc;
    }
    
    public Account getAccountWithID(UUID ID) {
        Account acc = null;
        try {
            String selectQuery = "SELECT * FROM Accounts WHERE userID = CAST (? AS uniqueidentifier)";
            conn = new DBContext().getConnection();
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, ID.toString());
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                acc = new Account(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getString(3)) ;
            }
            rs.close();
            selectStatement.close();
            conn.close();
        } catch (Exception e) {

        }
        return acc;
    }
    
}