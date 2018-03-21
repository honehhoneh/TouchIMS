package com.mendoza.touchims.models;

public class Room {

    private String roomName, bldg;
    private int floor, legend;

    public Room() {
    }

    public Room(String roomName, int floor, String bldg, int legend) {
        this.roomName = roomName;
        this.bldg = bldg;
        this.floor = floor;
        this.legend = legend;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBldg() {
        return bldg;
    }

    public void setBldg(String bldg) {
        this.bldg = bldg;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getLegend() {
        return legend;
    }

    public void setLegend(int legend) {
        this.legend = legend;
    }
}
