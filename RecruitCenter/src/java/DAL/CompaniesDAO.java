/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;
import model.Account;
import model.Company;

/**
 *
 * @author dell
 */
public class CompaniesDAO {
    Connection conn = null;
    public ArrayList<Company> getListCompanies() {
        ArrayList<Company> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Companies";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Company a = new Company();
                a.setCompanyID(UUID.fromString(rs.getString("CompanyID")));
                a.setCompanyName(rs.getString("Company"));
                a.setCompanyWebsite(rs.getString("Website"));
                a.setCompanyAddress(rs.getString("Address"));
                a.setCompanyReviews(rs.getString("Reviews"));
                a.setCompanyRatings(rs.getFloat("Ratings"));
                a.setCompanyNumOfRatings(rs.getInt("NumOfRating"));
                list.add(a);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public Company getCompanyWithName(String companyName) {
        Company c = null;
        try {
            String query = "SELECT * FROM Companies WHERE Company = ?";
            conn = new DBContext().getConnection();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, companyName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    c = new Company(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getInt(7));
                }
                rs.close();
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }
    
    
    public boolean insertCompany(Company comp) throws Exception{
        int lineAffected = 0;
        try {
            //insert
            String insertQuery = "INSERT INTO Companies(Company, Website, Address, Reviews, Ratings, NumOfRating) VALUES(?, ?, ?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, comp.getCompanyName());
            insertStatement.setString(2, comp.getCompanyWebsite());
            insertStatement.setString(3, comp.getCompanyAddress());
            insertStatement.setString(4, comp.getCompanyReviews());
            insertStatement.setFloat(5, comp.getCompanyRatings());
            insertStatement.setInt(6, comp.getCompanyNumOfRatings());
            lineAffected = insertStatement.executeUpdate();
            //release the resource
            insertStatement.close();
            conn.close();
        } catch (Exception e) {
            throw(e);
        } 
        //return true if suceeded
        return lineAffected == 1;
    }
}
