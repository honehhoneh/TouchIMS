package com.mendoza.touchims.models;

public class Report {
    private int term_cd, offer_no, fac_id, credit_units;
    private String subj_no, sch_days, sch_time, room, fac_name, department, checker_name, date, counter_remark, checker_remark, deans_reco, remark_dept;

    public Report() {
    }

    public Report(int term_cd, int offer_no, int fac_id, int credit_units, String subj_no, String sch_days, String sch_time, String room, String fac_name, String department, String checker_name, String date, String counter_remark, String checker_remark, String deans_reco, String remark_dept) {
        this.term_cd = term_cd;
        this.offer_no = offer_no;
        this.fac_id = fac_id;
        this.credit_units = credit_units;
        this.subj_no = subj_no;
        this.sch_days = sch_days;
        this.sch_time = sch_time;
        this.room = room;
        this.fac_name = fac_name;
        this.department = department;
        this.checker_name = checker_name;
        this.date = date;
        this.counter_remark = counter_remark;
        this.checker_remark = checker_remark;
        this.deans_reco = deans_reco;
        this.remark_dept = remark_dept;
    }

    public int getTerm_cd() {
        return term_cd;
    }

    public void setTerm_cd(int term_cd) {
        this.term_cd = term_cd;
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

    public String getSch_days() {
        return sch_days;
    }

    public void setSch_days(String sch_days) {
        this.sch_days = sch_days;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getChecker_remark() {
        return checker_remark;
    }

    public void setChecker_remark(String checker_remark) {
        this.checker_remark = checker_remark;
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
