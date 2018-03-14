package com.mendoza.touchims.models;

public class Term {

    private int term_cd;
    private String term, periodStart, periodEnd;;


    public Term() {
    }

    public Term(int term_cd, String periodStart, String periodEnd, String term) {
        this.term_cd = term_cd;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.term = term;
    }

    public int getTerm_cd() {
        return term_cd;
    }

    public void setTerm_cd(int term_cd) {
        this.term_cd = term_cd;
    }

    public String getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(String periodEnd) {
        this.periodEnd = periodEnd;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
