package model;

import java.util.UUID;

public class Company {
    UUID CompanyID;
    String CompanyName, CompanyWebsite, CompanyAddress, CompanyReviews;
    float CompanyRatings;
    int CompanyNumOfRatings;

    public Company() {
    }

    public Company(UUID CompanyID, String CompanyName, String CompanyWebsite, String CompanyAddress, String CompanyReviews, float CompanyRatings, int CompanyNumOfRatings) {
        this.CompanyID = CompanyID;
        this.CompanyName = CompanyName;
        this.CompanyWebsite = CompanyWebsite;
        this.CompanyAddress = CompanyAddress;
        this.CompanyReviews = CompanyReviews;
        this.CompanyRatings = CompanyRatings;
        this.CompanyNumOfRatings = CompanyNumOfRatings;
    }

    public UUID getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(UUID CompanyID) {
        this.CompanyID = CompanyID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getCompanyWebsite() {
        return CompanyWebsite;
    }

    public void setCompanyWebsite(String CompanyWebsite) {
        this.CompanyWebsite = CompanyWebsite;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String CompanyAddress) {
        this.CompanyAddress = CompanyAddress;
    }

    public String getCompanyReviews() {
        return CompanyReviews;
    }

    public void setCompanyReviews(String CompanyReviews) {
        this.CompanyReviews = CompanyReviews;
    }

    public float getCompanyRatings() {
        return CompanyRatings;
    }

    public void setCompanyRatings(float CompanyRatings) {
        this.CompanyRatings = CompanyRatings;
    }

    public int getCompanyNumOfRatings() {
        return CompanyNumOfRatings;
    }

    public void setCompanyNumOfRatings(int CompanyNumOfRatings) {
        this.CompanyNumOfRatings = CompanyNumOfRatings;
    }

    @Override
    public String toString() {
        return "Company{" + "CompanyID=" + CompanyID + ", CompanyName=" + CompanyName + ", CompanyWebsite=" + CompanyWebsite + ", CompanyAddress=" + CompanyAddress + ", CompanyReviews=" + CompanyReviews + ", CompanyRatings=" + CompanyRatings + ", CompanyNumOfRatings=" + CompanyNumOfRatings + '}';
    }
    
    
}
