package com.mendoza.touchims.models;

public class User {

    private String firstName, lastName, classification, college;
    private int idNum;

    public User(){

    }
    public User(String firstName, String lastName, int idNum, String classification, String college){
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNum = idNum;
        this.classification = classification;
        this.college = college;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }
}
