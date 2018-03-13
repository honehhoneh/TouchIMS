package com.mendoza.touchims.utilities;

public class Constants {
    private static final String IP = "192.168.254.104"; //home
//    private static final String IP = "172.17.1.239"; //school
    private static final String ROOT_URL = "http://" + IP + "/touchims/mobile/v1/";
    public static final String URL_REGISTER = ROOT_URL + "registerUser.php";
    public static final String URL_LOGIN = ROOT_URL + "userLogin.php";
    public static final String URL_CHANGE_ROOM_REQUEST = ROOT_URL + "changeRoomRequest.php";
    public static final String URL_GET_REPORTS = ROOT_URL + "getReports.php";
    public static final String URL_GET_REQUESTS = ROOT_URL + "getRequests.php";



}
