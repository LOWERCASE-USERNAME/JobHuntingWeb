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
public class Applied {
    
    UUID userid, recruitmentid;
    Date applyDate;

    public Applied() {
    }

    public Applied(UUID userid, UUID recruitmentid, Date applyDate) {
        this.userid = userid;
        this.recruitmentid = recruitmentid;
        this.applyDate = applyDate;
    }

    public UUID getUserid() {
        return userid;
    }

    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    public UUID getRecruitmentid() {
        return recruitmentid;
    }

    public void setRecruitmentid(UUID recruitmentid) {
        this.recruitmentid = recruitmentid;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    
}
