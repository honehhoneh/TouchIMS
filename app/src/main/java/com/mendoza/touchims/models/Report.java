package com.mendoza.touchims.models;

public class Report {
    private int term_cd, offer_no;
    private String fac_name, checker_name, date, room, counter_remark, checker_remark, deans_reco, remark_dept;

    public Report() {
    }

    public Report(int term_cd, int offer_no, String fac_name, String checker_name, String date, String room, String counter_remark, String checker_remark, String deans_reco, String remark_dept) {
        this.term_cd = term_cd;
        this.offer_no = offer_no;
        this.fac_name = fac_name;
        this.checker_name = checker_name;
        this.date = date;
        this.room = room;
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

    public String getFac_name() {
        return fac_name;
    }

    public void setFac_name(String fac_name) {
        this.fac_name = fac_name;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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
