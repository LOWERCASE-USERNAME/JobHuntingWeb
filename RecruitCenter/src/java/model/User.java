/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.UUID;

/**
 *
 * @author dell
 */
public class User {
    enum AccountType {
        EMPLOYER,
        EMPLOYEE
    }
    private UUID userID;
    private String fname, lname, email, phonenum, address;
    private AccountType accType = AccountType.EMPLOYEE;

    public User() {
    }
    
    public User(UUID userID, String fname, String lname, String email, String address, int accType) {
        this.userID = userID;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.address = address;
        setAccType(accType);
    }
    
    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccType() {
        int result = 0;
        switch(this.accType){
            case EMPLOYEE: 
                result = 0;
            case EMPLOYER:
                result = 1;
            default:
                result = 0;
        }
        return result;
    }

    public void setAccType(int accType) {
        switch(accType){
            case 0: 
                this.accType = AccountType.EMPLOYEE;
                break;
            case 1:
                this.accType = AccountType.EMPLOYER;
                break;
            default:
                
        }
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", phonenum=" + phonenum + ", address=" + address + ", accType=" + accType + '}';
    }
}
