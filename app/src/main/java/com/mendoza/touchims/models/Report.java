package com.mendoza.touchims.models;

public class Report {
    private int offer_no, fac_id, credit_units;
    private String subj_no, sch_time, room, fac_name, dept, checker_name, date, counter_remark, checker_firstRemark, checker_secondRemark, deans_reco, remark_dept;

    public Report() {
    }

    public Report(int offer_no, int fac_id, int credit_units, String subj_no, String sch_time, String room, String fac_name, String dept, String checker_name, String date, String counter_remark, String checker_firstRemark, String checker_secondRemark, String deans_reco, String remark_dept) {
        this.offer_no = offer_no;
        this.fac_id = fac_id;
        this.credit_units = credit_units;
        this.subj_no = subj_no;
        this.sch_time = sch_time;
        this.room = room;
        this.fac_name = fac_name;
        this.dept = dept;
        this.checker_name = checker_name;
        this.date = date;
        this.counter_remark = counter_remark;
        this.checker_firstRemark = checker_firstRemark;
        this.checker_secondRemark = checker_secondRemark;
        this.deans_reco = deans_reco;
        this.remark_dept = remark_dept;
    }

    public int getOffer_no() {
        return offer_no;
    }

    public void setOffer_no(int offer_no) {
        this.offer_no = offer_no;
    }

    public int getFac_id() {
        return fac_id;
    }

    public void setFac_id(int fac_id) {
        this.fac_id = fac_id;
    }

    public int getCredit_units() {
        return credit_units;
    }

    public void setCredit_units(int credit_units) {
        this.credit_units = credit_units;
    }

    public String getSubj_no() {
        return subj_no;
    }

    public void setSubj_no(String subj_no) {
        this.subj_no = subj_no;
    }

    public String getSch_time() {
        return sch_time;
    }

    public void setSch_time(String sch_time) {
        this.sch_time = sch_time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getFac_name() {
        return fac_name;
    }

    public void setFac_name(String fac_name) {
        this.fac_name = fac_name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getChecker_name() {
        return checker_name;
    }

    public void setChecker_name(String checker_name) {
        this.checker_name = checker_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCounter_remark() {
        return counter_remark;
    }

    public void setCounter_remark(String counter_remark) {
        this.counter_remark = counter_remark;
    }

    public String getChecker_firstRemark() {
        return checker_firstRemark;
    }

    public void setChecker_firstRemark(String checker_firstRemark) {
        this.checker_firstRemark = checker_firstRemark;
    }

    public String getChecker_secondRemark() {
        return checker_secondRemark;
    }

    public void setChecker_secondRemark(String checker_secondRemark) {
        this.checker_secondRemark = checker_secondRemark;
    }

    public String getDeans_reco() {
        return deans_reco;
    }

    public void setDeans_reco(String deans_reco) {
        this.deans_reco = deans_reco;
    }

    public String getRemark_dept() {
        return remark_dept;
    }

    public void setRemark_dept(String remark_dept) {
        this.remark_dept = remark_dept;
    }
}