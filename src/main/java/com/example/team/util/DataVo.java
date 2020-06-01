package com.example.team.util;

/**
 * 读取Excel时，封装读取的每一行的数据
 */
public class DataVo {
    /**
     * 待办名
     */
    private String name;

    /**
     * 待办集名
     */
    private String sName;

    /**
     * 待办情况记录（0未完成，1完成）
     */
    private Integer record;

    /**
     * 待办完成度
     */
    private String completion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public String getCompletion(){
        return completion;
    }

    public void setCompletion(String completion){
        this.completion = completion;
    }

}
