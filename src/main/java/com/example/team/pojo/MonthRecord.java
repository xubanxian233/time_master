package com.example.team.pojo;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "monthrecord")
public class MonthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "month_record_id")
    private int monthRecordId;
    @Column(name = "success_count")
    private int successCount;
    @Column(name = "fail_count")
    private int failCount;
    private long acctime;
    @Column(name = "month_date")
    private Date monthDate;
    @Column(name = "user")
    private int userId;

    public int getMonthRecordId() {
        return monthRecordId;
    }

    public void setMonthRecordId(int monthRecordId) {
        this.monthRecordId = monthRecordId;
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

    public Date getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(Date monthDate) {
        this.monthDate = monthDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
