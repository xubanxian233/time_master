package com.example.team.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "dailyrecord")
public class DailyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_record_id")
    private int dailyRecordId;
    @Column(name = "success_count")
    private int successCount;
    @Column(name = "fail_count")
    private int failCount;
    private long acctime;
    @Column(name = "daily_date")
    private Date dailyDate;
    @Column(name = "user")
    @JsonIgnore
    private int userId;

    public int getDailyRecordId() {
        return dailyRecordId;
    }

    public void setDailyRecordId(int dailyRecordId) {
        this.dailyRecordId = dailyRecordId;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public long getAcctime() {
        return acctime;
    }

    public void setAcctime(long acctime) {
        this.acctime = acctime;
    }

    public Date getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(Date dailyDate) {
        this.dailyDate = dailyDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
