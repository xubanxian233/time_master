package com.example.team.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "accrecord")
public class AccRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_record_id")
    private int accRecordId;
    @Column(name = "success_count")
    private int successCount;
    @Column(name = "fail_count")
    private int failCount;
    private long acctime;
    private long dailytime;
    @Column(name = "user")
    @JsonIgnore
    private int userId;

    public int getAccRecordId() {
        return accRecordId;
    }

    public void setAccRecordId(int accRecordId) {
        this.accRecordId = accRecordId;
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

    public long getDailytime() {
        return dailytime;
    }

    public void setDailytime(long dailytime) {
        this.dailytime = dailytime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
