/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class SkillAndTitleDAO {
    Connection conn = null;
    
    
    public ArrayList<String> searchJobwithTerm(String term){
        ArrayList<String> sList = new ArrayList<>();
        //chinh sua thanh JobTitle
        String query = "SELECT DISTINCT SkillandTitle FROM SkillandTitle WHERE SkillandTitle LIKE ?";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + term + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String title = rs.getString(1);
                sList.add(title);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(SkillAndTitleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sList;
    }
    
    public String getSkillIDwithName(String skillName){
        String id = "";
        String query = "SELECT DISTINCT ID FROM SkillandTitle WHERE SkillandTitle LIKE ?";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, skillName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getString(1);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(SkillAndTitleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}