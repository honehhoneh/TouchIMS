package com.mendoza.touchims.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Constants {
        public static final String CURRENT_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    public static final String CURRENT_TIME = new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
    public static final String CURRENT_DAY = new SimpleDateFormat("EEEEE").format(new Date());

//    public static final String CURRENT_DATE = "2016-10-03";


        private static final String IP = "192.168.254.105"; //home
//    private static final String IP = "172.30.6.29"; //school

    private static final String ROOT_URL = "http://" + IP + "/touchims/mobile/v1/";

    public static final String URL_REGISTER = ROOT_URL + "registerUser.php";
    public static final String URL_LOGIN = ROOT_URL + "userLogin.php";
    public static final String URL_REPORT = ROOT_URL + "createReport.php";

    public static final String URL_CHANGE_ROOM_REQUEST = ROOT_URL + "changeRoomRequest.php";

    public static final String URL_UPDATE_REPORT = ROOT_URL + "updateReport.php";

    public static final String URL_GET_COURSE_DETAILS = ROOT_URL + "getCourseDetails.php";
    public static final String URL_GET_REPORT_DETAILS = ROOT_URL + "getReports.php";
    public static final String URL_GET_REQUESTS = ROOT_URL + "getRequests.php";
    public static final String URL_GET_SUBJECT_DETAILS = ROOT_URL + "getSubjectDetails.php";
    public static final String URL_GET_FLOOR_DETAILS = ROOT_URL + "getFloorDetails.php";


}
