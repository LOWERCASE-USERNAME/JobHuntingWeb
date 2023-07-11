/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.UUID;

/**
 *
 * @author dell
 */
public class Recruitments {
    UUID RecruitmentID, CompanyID, SkillAndTitleID;
    int jobTypeID, fieldID;
    String contactName, contactEmail, contactPhoneNumber, JobTitle, Location, Salaries, Gender, Degree, JobDescription;
    Date postedDate, expDate;

    public Recruitments() {
    }

    public Recruitments(UUID RecruitmentID, UUID CompanyID, UUID SkillAndTitleID, int jobTypeID, int fieldID, String contactName, String contactEmail, String contactPhoneNumber, String JobTitle, String Location, String Salaries, String Gender, String Degree, String JobDescription, Date postedDate, Date expDate) {
        this.RecruitmentID = RecruitmentID;
        this.CompanyID = CompanyID;
        this.SkillAndTitleID = SkillAndTitleID;
        this.jobTypeID = jobTypeID;
        this.fieldID = fieldID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhoneNumber = contactPhoneNumber;
        this.JobTitle = JobTitle;
        this.Location = Location;
        this.Salaries = Salaries;
        this.Gender = Gender;
        this.Degree = Degree;
        this.JobDescription = JobDescription;
        this.postedDate = postedDate;
        this.expDate = expDate;
    }
    
    public UUID getRecruitmentID() {
        return RecruitmentID;
    }

    public void setRecruitmentID(UUID RecruitmentID) {
        this.RecruitmentID = RecruitmentID;
    }

    public UUID getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(UUID CompanyID) {
        this.CompanyID = CompanyID;
    }

    public UUID getSkillAndTitleID() {
        return SkillAndTitleID;
    }

    public void setSkillAndTitleID(UUID SkillAndTitleID) {
        this.SkillAndTitleID = SkillAndTitleID;
    }

    public int getJobTypeID() {
        return jobTypeID;
    }

    public void setJobTypeID(int jobTypeID) {
        this.jobTypeID = jobTypeID;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String JobTitle) {
        this.JobTitle = JobTitle;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getSalaries() {
        return Salaries;
    }

    public void setSalaries(String Salaries) {
        this.Salaries = Salaries;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String Degree) {
        this.Degree = Degree;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String JobDescription) {
        this.JobDescription = JobDescription;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "Recruitments{" + "RecruitmentID=" + RecruitmentID + ", CompanyID=" + CompanyID + ", SkillAndTitleID=" + SkillAndTitleID + ", jobTypeID=" + jobTypeID + ", fieldID=" + fieldID + ", contactName=" + contactName + ", contactEmail=" + contactEmail + ", contactPhoneNumber=" + contactPhoneNumber + ", JobTitle=" + JobTitle + ", Location=" + Location + ", Salaries=" + Salaries + ", Gender=" + Gender + ", Degree=" + Degree + ", JobDescription=" + JobDescription + ", postedDate=" + postedDate + ", expDate=" + expDate + '}';
    }
    
    
}
