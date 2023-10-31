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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Applied;

/**
 *
 * @author dell
 */
public class AppliedDAO {
    Connection conn = null;
    public ArrayList<Applied> getListApplied() {
        ArrayList<Applied> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM AppliedHistory";
            conn = new DBContext().getConnection();
            try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Applied acc = new Applied(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), rs.getDate(3)) ;
                    list.add(acc);
                }
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AppliedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertApplied(Applied acc) throws Exception{
        int lineAffected = 0;
        try {
            //insert
            String insertQuery = "INSERT INTO AppliedHistory VALUES(CAST(? as uniqueidentifier), CAST(? as uniqueidentifier), ?)";
            conn = new DBContext().getConnection();
            try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
                insertStatement.setString(1, acc.getUserid().toString());
                insertStatement.setString(2, acc.getRecruitmentid().toString());
                insertStatement.setDate(3, acc.getApplyDate());
                lineAffected = insertStatement.executeUpdate();
                //release the resource
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AppliedDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //return true if suceeded
        return lineAffected == 1;
    }
    
    public Applied getAppliedWithUserID(UUID ID) {
        Applied acc = null;
        try {
            String selectQuery = "SELECT * FROM AppliedHistory WHERE userID = CAST (? AS uniqueidentifier)";
            conn = new DBContext().getConnection();
            try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
                selectStatement.setString(1, ID.toString());
                try (ResultSet rs = selectStatement.executeQuery()) {
                    while (rs.next()) {
                        acc = new Applied(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), rs.getDate(3)) ;
                    }
                }
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AppliedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }
    public Applied getAppliedWithCompanyID(UUID ID) {
        Applied acc = null;
        try {
            String selectQuery = "SELECT * FROM AppliedHistory WHERE recruitmentID IN (SELECT id FROM Recruitments WHERE companyID = CAST (? AS uniqueidentifier))";
            conn = new DBContext().getConnection();
            try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
                selectStatement.setString(1, ID.toString());
                try (ResultSet rs = selectStatement.executeQuery()) {
                    while (rs.next()) {
                        acc = new Applied(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), rs.getDate(3));
                    }
                }
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AppliedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }
}