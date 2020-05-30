package com.example.team.pojo;

import javax.persistence.*;

@Entity
@Table(name = "typerecord")
public class TypeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_record_id")
    private int typeRecordId;
    @Column(name = "success_count")
    private int successCount;
    @Column(name = "fail_count")
    private int failCount;
    private long acctime;
    @Column(name = "type")
    private int typeId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type", insertable = false, updatable = false)
    private Type type;
    @Column(name = "user")
    private int userId;

    public int getTypeRecordId() {
        return typeRecordId;
    }

    public void setTypeRecordId(int typeRecordId) {
        this.typeRecordId = typeRecordId;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
