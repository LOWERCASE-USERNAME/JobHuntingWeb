/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;

public class Record {
    private String RecordID, UserID, CompanyID, Role, Projects;
    private Date StartDate, EndDate;

    public Record(String UserID, String CompanyID) {
        this.UserID = UserID;
        this.CompanyID = CompanyID;
    }
    
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    
    public String getRecordID() {
        return RecordID;
    }

    public void setRecordID(String RecordID) {
        this.RecordID = RecordID;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String CompanyID) {
        this.CompanyID = CompanyID;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getProjects() {
        return Projects;
    }

    public void setProjects(String Projects) {
        this.Projects = Projects;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    @Override
    public String toString() {
        return "Record{" + "RecordID=" + RecordID + ", CompanyID=" + CompanyID + ", Role=" + Role + ", Projects=" + Projects + ", StartDate=" + StartDate + ", EndDate=" + EndDate + '}';
    }
    
    
}
