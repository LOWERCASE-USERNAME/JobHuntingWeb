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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dell
 */
public class AccountDAO {
    Connection conn = null;
    public List<Account> getListAccount() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Accounts";
            conn = new DBContext().getConnection();
            try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Account a = new Account(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4));
                    list.add(a);
                }
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertAccount(Account acc) throws Exception{
        int lineAffected = 0;
        try {
            //insert
            String insertQuery = "INSERT INTO Accounts VALUES(CAST(? as uniqueidentifier), ?, ?, ?)";
            conn = new DBContext().getConnection();
            try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
                insertStatement.setString(1, acc.getId().toString());
                insertStatement.setString(2, acc.getUsername());
                insertStatement.setString(3, acc.getPassword());
                insertStatement.setString(4, acc.getRole());
                lineAffected = insertStatement.executeUpdate();
                //release the resource
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //return true if suceeded
        return lineAffected == 1;
    }
    
    public boolean updateAccount(Account acc){
        String query = "UPDATE Accounts SET username = ?, password = ? WHERE userid = CAST(? as uniqueidentifier)";
        int line = 0;
        try {
            conn = new DBContext().getConnection();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(3, acc.getId().toString());
                ps.setString(1, acc.getUsername());
                ps.setString(2, acc.getPassword());
                line = ps.executeUpdate();
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return line == 1; //succeded or not
    }
    
    public Account getAccountWithUsername(String username) {
        Account acc = null;
        try {
            String selectQuery = "SELECT * FROM Accounts WHERE username = ?";
            conn = new DBContext().getConnection();
            try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
                selectStatement.setString(1, username);
                try (ResultSet rs = selectStatement.executeQuery()) {
                    while (rs.next()) {
                        acc = new Account(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4)) ;
                    }
                }
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }
    
    public Account getAccountWithID(UUID ID) {
        Account acc = null;
        try {
            String selectQuery = "SELECT * FROM Accounts WHERE userID = CAST (? AS uniqueidentifier)";
            conn = new DBContext().getConnection();
            try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
                selectStatement.setString(1, ID.toString());
                try (ResultSet rs = selectStatement.executeQuery()) {
                    while (rs.next()) {
                        acc = new Account(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getString(3),rs.getString(4)) ;
                    }
                }
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }
    
}