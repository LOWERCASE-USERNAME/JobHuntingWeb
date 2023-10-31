/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Recruitments;

/**
 *
 * @author dell
 */
public class RecruitmentsDAO {

    Connection conn = null;

    public ArrayList<Recruitments> getListRecruitments() throws Exception {
        ArrayList<Recruitments> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Recruitments";
            conn = new DBContext().getConnection();
            try ( PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
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
                    a.setCreatedBy(UUID.fromString(rs.getString("createdby")));
                    a.setWorkplace(rs.getString("workplace"));
                    a.setNegotiable(rs.getBoolean("negotiable"));
                    a.setCompanySize("company_size");
                    list.add(a);
                }
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RecruitmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Recruitments getListRecruitments(UUID id) throws Exception {
//        ArrayList<Recruitments> list = new ArrayList<>();
        Recruitments a = new Recruitments();
        try {
            String query = "SELECT * FROM Recruitments WHERE id LIKE ?";
            conn = new DBContext().getConnection();
            try ( PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, id.toString());
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
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
                        a.setCreatedBy(UUID.fromString(rs.getString("createdby")));
                        a.setWorkplace(rs.getString("workplace"));
                        a.setNegotiable(rs.getBoolean("negotiable"));
                        a.setCompanySize("company_size");
                    }
                }
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RecruitmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public static int count() {
        String sql = "SELECT COUNT(*) as totalrow FROM Recruitments";
        try {
            Connection conn = new DBContext().getConnection();
            try ( PreparedStatement statement = conn.prepareStatement(sql);  ResultSet rs = statement.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt("totalrow");
                }
            };

        } catch (Exception ex) {
            Logger.getLogger(RecruitmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    //        int totalrow = count();
//        int totalpage = (totalrow%PAGE_SIZE == 0) ? totalrow/PAGE_SIZE : totalrow/PAGE_SIZE + 1;

    public ArrayList<Recruitments> getListRecruitmentsWithPagination(int pageNumber) {
        ArrayList<Recruitments> list = new ArrayList<>();
        int PAGE_SIZE = 5; // Number of records per page
        int PAGE_NUMBER = pageNumber; // Desired page number
        try {
            conn = new DBContext().getConnection();
            String sql = "SELECT * FROM Recruitments ORDER BY PostedDate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            // Calculate the offset based on the page size and page number
            int offset = (PAGE_NUMBER - 1) * PAGE_SIZE;

            // Create the prepared statement with the pagination parameters
            try ( PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, offset);
                preparedStatement.setInt(2, PAGE_SIZE);
                try ( ResultSet rs = preparedStatement.executeQuery()) {
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
                        a.setCreatedBy(UUID.fromString(rs.getString("createdby")));
                        a.setWorkplace(rs.getString("workplace"));
                        a.setNegotiable(rs.getBoolean("negotiable"));
                        a.setCompanySize("company_size");
                        list.add(a);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RecruitmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public <T> ArrayList<String> getRecruitmentsWithFilter(Map<String, T> conditionMap) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        try {
            conn = new DBContext().getConnection();
            if (conditionMap.isEmpty()) {
                ArrayList<Recruitments> totalList = getListRecruitments();
                for (Recruitments recruitment : totalList) {
                    list.add(recruitment.getRecruitmentID().toString().toUpperCase());
                }
                return list;
            }

            query.append("SELECT * FROM Recruitments WHERE");
            int conditionCount = 0;

            for (Map.Entry<String, T> entry : conditionMap.entrySet()) {
                String column = entry.getKey();
                T value = entry.getValue();

                if (conditionCount > 0) {
                    query.append(" AND");
                }

                query.append(" ");

                if (value.getClass().isArray()) {
                    query.append("(");
                    int length = Array.getLength(value);
                    for (int i = 0; i < length; i++) {
                        if (i > 0) {
                            query.append(" OR");
                        }
                        query.append(" ")
                                .append(column)
                                .append(" LIKE ?");
                    }
                    query.append(")");
                } else {
                    query.append(column)
                            .append(" LIKE ?");

                }
                conditionCount++;
            }

            try ( PreparedStatement ps = conn.prepareStatement(query.toString())) {
                int paramIndex = 1;
                for (Map.Entry<String, T> entry : conditionMap.entrySet()) {
                    String column = entry.getKey();
                    T value = entry.getValue();
                    
                    if (value.getClass().isArray()) {
                        int length = Array.getLength(value);
                        for (int i = 0; i < length; i++) {
                            Object element = Array.get(value, i);
                            ps.setObject(paramIndex, "%" + element + "%");
                            paramIndex++;
                        }
                    } else {
                        ps.setObject(paramIndex, "%" + value + "%");
                        paramIndex++;
                    }
                }
                //            // Process the ResultSet as needed
                try ( ResultSet rs = ps.executeQuery()) {
                    //            // Process the ResultSet as needed
                    while (rs.next()) {
                        list.add(rs.getString(1));
                    }
                }
            }
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(RecruitmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Handle the exception appropriately
        }
        return list;
    }

    public ArrayList<String> getRecruitmentsWithJobTerm(String searchTerm) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        try {
            String query = "SELECT ID FROM Recruitments R WHERE JobTitle LIKE ? OR JobDescription LIKE ? OR R.SkillandTitleID IN (SELECT id FROM SkillandTitle WHERE SkillandTitle LIKE ?)";
            conn = new DBContext().getConnection();
            try ( PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, "%" + searchTerm + "%");
                ps.setString(2, "%" + searchTerm + "%");
                ps.setString(3, "%" + searchTerm + "%");
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String id = rs.getString(1);
                        list.add(id);
                    }
                }
            }
            conn.close();
        } catch (Exception e) {
            throw (e);
        }
        return list;
    }

    public ArrayList<String> getRecruitmentsWithLocationTerm(String cityTerm, String distTerm, String wardTerm) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        try {
            String query = "SELECT ID FROM Recruitments WHERE Location LIKE ?";
            conn = new DBContext().getConnection();
            try ( PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, "%" + wardTerm + "%" + distTerm + "%" + cityTerm + "%");
                //            ps.setString(2, "%" + distTerm);
                //            ps.setString(3, "%" + cityTerm + "%");
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String id = rs.getString(1);
                        list.add(id);
                    }
                }
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RecruitmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertRecruitment(Recruitments rc) throws Exception {
        int lineAffected = 0;
        try {
            String insertQuery = "INSERT INTO Recruitments(ContactName, ContactEmail, ContactPhoneNumber, JobTitle, JobTypeID, CompanyID, FieldID, Location, Salaries, PostedDate, ExpirationDate, SkillandTitleID, Gender, Degree, JobDescription, negotiable, workplace, company_size, createdby)\n"
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
                    + "?,\n"//JobDescription 16    
                    + "?,\n"//nego 16  
                    + "?,\n"//workplace 17    
                    + "?,\n"//size 18    
                    + "CAST(? as uniqueidentifier)\n"//create_by 19    
                    + ")";
            conn = new DBContext().getConnection();
            try ( PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
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
                insertStatement.setBoolean(16, rc.isNegotiable());
                insertStatement.setString(17, rc.getWorkplace());
                insertStatement.setString(18, rc.getCompanySize());
                insertStatement.setString(19, rc.getCreatedBy().toString());
                lineAffected = insertStatement.executeUpdate();
                //release the resource
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RecruitmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return true if suceeded
        return lineAffected == 1;
    }

    public boolean updateRecruitment(Recruitments rc) throws Exception {
        int lineAffected = 0;
        try {
            String updateQuery = "UPDATE Recruitments SET "
                    + "ContactName = ?, "
                    + "ContactEmail = ?, "
                    + "ContactPhoneNumber = ?, "
                    + "JobTitle = ?, "
                    + "JobTypeID = ?, "
                    + "FieldID = ?, "
                    + "Location = ?, "
                    + "Salaries = ?, "
                    + "ExpirationDate = ?, "
                    + "SkillandTitleID = CAST(? as uniqueidentifier), "
                    + "Gender = ?, "
                    + "Degree = ?, "
                    + "JobDescription = ?, "
                    + "negotiable = ?, "
                    + "workplace = ?, "
                    + "company_size = ? "
                    + "WHERE ID like ?"; // Specify the condition for the update

            conn = new DBContext().getConnection();
            try ( PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
                updateStatement.setString(1, rc.getContactName());
                updateStatement.setString(2, rc.getContactEmail());
                updateStatement.setString(3, rc.getContactPhoneNumber());
                updateStatement.setString(4, rc.getJobTitle());
                updateStatement.setInt(5, rc.getJobTypeID());
                //            updateStatement.setString(6, rc.getCompanyID().toString());
                updateStatement.setInt(6, rc.getFieldID());
                updateStatement.setString(7, rc.getLocation());
                updateStatement.setString(8, rc.getSalaries());
                //            updateStatement.setDate(10, rc.getPostedDate());
                updateStatement.setDate(9, rc.getExpDate());
                updateStatement.setString(10, rc.getSkillAndTitleID().toString());
                updateStatement.setString(11, rc.getGender());
                updateStatement.setString(12, rc.getDegree());
                updateStatement.setString(13, rc.getJobDescription());
                updateStatement.setBoolean(14, rc.isNegotiable());
                updateStatement.setString(15, rc.getWorkplace());
                updateStatement.setString(16, rc.getCompanySize());
                //            updateStatement.setString(19, rc.getCreatedBy().toString());
                updateStatement.setString(17, rc.getRecruitmentID().toString());
                lineAffected = updateStatement.executeUpdate();
                // Release the resource
            }
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(RecruitmentsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return true if succeeded
        return lineAffected == 1;
    }
}
