package com.mendoza.touchims.models;


public class Course {
    private int offer_no, term_cd, fac_id, credit_units;
    private String subj_no, sch_days, sch_time, rm, fac_name, department, timeStart, timeEnd;

    public Course() {
    }

    public Course(int offer_no, int term_cd, int fac_id, int credit_units, String subj_no, String sch_days, String sch_time, String rm, String fac_name, String department, String timeStart, String timeEnd) {
        this.offer_no = offer_no;
        this.term_cd = term_cd;
        this.fac_id = fac_id;
        this.credit_units = credit_units;
        this.subj_no = subj_no;
        this.sch_days = sch_days;
        this.sch_time = sch_time;
        this.rm = rm;
        this.fac_name = fac_name;
        this.department = department;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Course(int offer_no, int fac_id, String subj_no, int credit_units, String sch_days, String rm, String fac_name, String dept, String timeStart, String timeEnd) {

    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getOffer_no() {
        return offer_no;
    }

    public void setOffer_no(int offer_no) {
        this.offer_no = offer_no;
    }

    public int getTerm_cd() {
        return term_cd;
    }

    public void setTerm_cd(int term_cd) {
        this.term_cd = term_cd;
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

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
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
}
