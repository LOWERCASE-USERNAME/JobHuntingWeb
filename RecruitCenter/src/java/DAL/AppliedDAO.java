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
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Applied acc = new Applied(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), rs.getDate(3)) ;
                list.add(acc);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return list;
    }

    public boolean insertApplied(Applied acc) throws Exception{
        int lineAffected = 0;
        try {
            //insert
            String insertQuery = "INSERT INTO AppliedHistory VALUES(CAST(? as uniqueidentifier), CAST(? as uniqueidentifier), ?)";
            conn = new DBContext().getConnection();
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, acc.getUserid().toString());
            insertStatement.setString(2, acc.getRecruitmentid().toString());
            insertStatement.setDate(3, acc.getApplyDate());
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
    
    public Applied getAppliedWithUserID(UUID ID) {
        Applied acc = null;
        try {
            String selectQuery = "SELECT * FROM AppliedHistory WHERE userID = CAST (? AS uniqueidentifier)";
            conn = new DBContext().getConnection();
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, ID.toString());
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                acc = new Applied(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), rs.getDate(3)) ;
            }
            rs.close();
            selectStatement.close();
            conn.close();
        } catch (Exception e) {
        }
        return acc;
    }
    public Applied getAppliedWithCompanyID(UUID ID) {
        Applied acc = null;
        try {
            String selectQuery = "SELECT * FROM AppliedHistory WHERE recruitmentID IN (SELECT id FROM Recruitments WHERE companyID = CAST (? AS uniqueidentifier))";
            conn = new DBContext().getConnection();
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, ID.toString());
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                acc = new Applied(UUID.fromString(rs.getString(1)), UUID.fromString(rs.getString(2)), rs.getDate(3));
            }
            rs.close();
            selectStatement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return acc;
    }
}