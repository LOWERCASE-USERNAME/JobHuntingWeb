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
            try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
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
            }
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
            try ( PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, companyName);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        c = new Company(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getInt(7));
                    }
                }
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }

    public boolean insertCompany(Company comp) throws Exception {
        int lineAffected = 0;
        try {
            //insert
            String insertQuery = "INSERT INTO Companies(Company, Website, Address, Reviews, Ratings, NumOfRating) VALUES(?, ?, ?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
                insertStatement.setString(1, comp.getCompanyName());
                insertStatement.setString(2, comp.getCompanyWebsite());
                insertStatement.setString(3, comp.getCompanyAddress());
                insertStatement.setString(4, comp.getCompanyReviews());
                insertStatement.setFloat(5, comp.getCompanyRatings());
                insertStatement.setInt(6, comp.getCompanyNumOfRatings());
                lineAffected = insertStatement.executeUpdate();
                //release the resource
            }
            conn.close();
        } catch (Exception e) {
            throw (e);
        }
        //return true if suceeded
        return lineAffected == 1;
    }

    public boolean updateCompany(Company comp) throws Exception {
        int lineAffected = 0;
        try {
            String updateQuery = "UPDATE Companies SET "
                    + "Company = ?, "
                    + "Website = ?, "
                    + "Address = ?, "
                    + "Reviews = ?, "
                    + "Ratings = ?, "
                    + "NumOfRating = ? "
                    + "WHERE companyid LIKE ?"; // Specify the condition for the update

            conn = new DBContext().getConnection();
            try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
                updateStatement.setString(1, comp.getCompanyName());
                updateStatement.setString(2, comp.getCompanyWebsite());
                updateStatement.setString(3, comp.getCompanyAddress());
                updateStatement.setString(4, comp.getCompanyReviews());
                updateStatement.setFloat(5, comp.getCompanyRatings());
                updateStatement.setInt(6, comp.getCompanyNumOfRatings());
                updateStatement.setString(7, comp.getCompanyID().toString());
                // Set the condition parameter(s) for the update
                // For example: updateStatement.setInt(7, comp.getId());
                lineAffected = updateStatement.executeUpdate();
                // Release the resource
            }
            conn.close();
        } catch (Exception e) {
            throw (e);
        }
        // Return true if succeeded
        return lineAffected == 1;
    }

}
