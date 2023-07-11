/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import model.Account;
import model.Recruitments;

/**
 *
 * @author dell
 */
public class RecruitmentsDAO {

    Connection conn = null;

    public ArrayList<Recruitments> getListRecruitments() {
        ArrayList<Recruitments> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Recruitments";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recruitments a = new Recruitments();
                a.setRecruitmentID(UUID.fromString(rs.getString("ID")));
                a.setContactName(rs.getString("ContactName"));
                a.setContactEmail(rs.getString("ContactEmail"));
                a.setContactPhoneNumber(rs.getString("ContactPhoneNumber"));
                a.setJobTitle(rs.getString("JobTitle"));
                a.setJobTypeID(rs.getInt("JobTypeID"));
                a.setCompanyID(UUID.fromString(rs.getString("CompanyID")));
                a.setFieldID(rs.getInt("FieldID"));
                a.setLocation(rs.getString("Location"));
                a.setSalaries(rs.getString("Salaries"));
                a.setPostedDate(rs.getDate("PostedDate"));
                a.setExpDate(rs.getDate("ExpirationDate"));
                a.setSkillAndTitleID(UUID.fromString(rs.getString("SkillandTitleID")));
                a.setGender(rs.getString("Gender"));
                a.setDegree(rs.getString("Degree"));
                a.setJobDescription(rs.getString("JobDescription"));
                list.add(a);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public ArrayList<String> getRecruitmentsWithSearchFilter(Map<String, String> conditionMap) throws Exception {
        StringBuilder query = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().getConnection();
            if (conditionMap.isEmpty()) {
                ArrayList<Recruitments> totalList = getListRecruitments();
                for (Recruitments recruitment : totalList) {
                    list.add(recruitment.getRecruitmentID().toString().toUpperCase());
                }
                return list;
//                return getListRecruitments();
            }
            query.append("SELECT id FROM Recruitments WHERE");
            int conditionCount = 0;

            for (Map.Entry<String, String> entry : conditionMap.entrySet()) {
                String column = entry.getKey();
                String value = entry.getValue();

                if (conditionCount > 0) {
                    query.append(" AND");
                }

                query.append(" ")
                        .append(column)
                        .append(" LIKE ?");

                conditionCount++;
            }

            ps = conn.prepareStatement(query.toString());

            int paramIndex = 1;
            for (String value : conditionMap.values()) {
                ps.setString(paramIndex, "%" + value + "%");
                paramIndex++;
            }

            ResultSet rs = ps.executeQuery();
            // Process the ResultSet as needed
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            throw(e);
//            e.printStackTrace();
            // Handle the exception appropriately
        }

        return list;
    }

    public boolean insertRecruitment(Recruitments rc) throws Exception {
        int lineAffected = 0;
        try {
            String insertQuery = "INSERT INTO Recruitments(ContactName, ContactEmail, ContactPhoneNumber, JobTitle, JobTypeID, CompanyID, FieldID, Location, Salaries, PostedDate, ExpirationDate, SkillandTitleID, Gender, Degree, JobDescription)\n"
                    + "VALUES(\n"
                    + "?,\n"//ContactName 2
                    + "?,\n"//ContactEmail 3
                    + "?,\n"//ContactPhoneNumber 4
                    + "?,\n"//JobTitle 5
                    + "?,\n"//JobTypeID 6
                    + "CAST(? as uniqueidentifier),\n"//CompanyID 7 uuid
                    + "?,\n"//FieldID 8
                    + "?,\n"//Location 9
                    + "?,\n"//Salaries 10
                    + "?,\n"//PostedDate 11
                    + "?,\n"//ExpirationDate 12
                    + "CAST(? as uniqueidentifier),\n"//SkillandTitleID 13 uuid
                    + "?,\n"//Gender 14
                    + "?,\n"//Degree 15    
                    + "?\n"//JobDescription 16    
                    + ")";
            conn = new DBContext().getConnection();
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, rc.getContactName());
            insertStatement.setString(2, rc.getContactEmail());
            insertStatement.setString(3, rc.getContactPhoneNumber());
            insertStatement.setString(4, rc.getJobTitle());
            insertStatement.setInt(5, rc.getJobTypeID());
            insertStatement.setString(6, rc.getCompanyID().toString());
            insertStatement.setInt(7, rc.getFieldID());
            insertStatement.setString(8, rc.getLocation());
            insertStatement.setString(9, rc.getSalaries());
            insertStatement.setDate(10, rc.getPostedDate());
            insertStatement.setDate(11, rc.getExpDate());
            insertStatement.setString(12, rc.getSkillAndTitleID().toString());
            insertStatement.setString(13, rc.getGender());
            insertStatement.setString(14, rc.getDegree());
            insertStatement.setString(15, rc.getJobDescription());

            lineAffected = insertStatement.executeUpdate();
            //release the resource
            insertStatement.close();
            conn.close();
        } catch (Exception e) {
            throw (e);
        }
        //return true if suceeded
        return lineAffected == 1;
    }
}
