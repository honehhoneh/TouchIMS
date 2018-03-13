package com.mendoza.touchims.models;

public class RoomRequest {

    private String department, dateOfNotif, subject, sch_time, sch_days, room, classActivities, actDate, actTime, actVenue, instructor,adminRemark, adminName;

    public RoomRequest(){

    }

    public RoomRequest(String classActivities, String dateOfNotif){
        this.classActivities = classActivities;
        this.dateOfNotif = dateOfNotif;
    }

    public RoomRequest(String department, String dateOfNotif, String subject, String sch_time, String sch_days, String room, String classActivities, String actDate, String actTime, String actVenue, String instructor, String adminRemark, String adminName) {
        this.department = department;
        this.dateOfNotif = dateOfNotif;
        this.subject = subject;
        this.sch_time = sch_time;
        this.sch_days = sch_days;
        this.room = room;
        this.classActivities = classActivities;
        this.actDate = actDate;
        this.actTime = actTime;
        this.actVenue = actVenue;
        this.instructor = instructor;
        this.adminRemark = adminRemark;
        this.adminName = adminName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDateOfNotif() {
        return dateOfNotif;
    }

    public void setDateOfNotif(String dateOfNotif) {
        this.dateOfNotif = dateOfNotif;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSch_time() {
        return sch_time;
    }

    public void setSch_time(String sch_time) {
        this.sch_time = sch_time;
    }

    public String getSch_days() {
        return sch_days;
    }

    public void setSch_days(String sch_days) {
        this.sch_days = sch_days;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getClassActivities() {
        return classActivities;
    }

    public void setClassActivities(String classActivities) {
        this.classActivities = classActivities;
    }

    public String getActDate() {
        return actDate;
    }

    public void setActDate(String actDate) {
        this.actDate = actDate;
    }

    public String getActTime() {
        return actTime;
    }

    public void setActTime(String actTime) {
        this.actTime = actTime;
    }

    public String getActVenue() {
        return actVenue;
    }

    public void setActVenue(String actVenue) {
        this.actVenue = actVenue;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}


