package com.mendoza.touchims.views;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mendoza.touchims.R;
import com.mendoza.touchims.models.Course;
import com.mendoza.touchims.models.Report;
import com.mendoza.touchims.models.Term;
import com.mendoza.touchims.models.User;
import com.mendoza.touchims.utilities.Constants;
import com.mendoza.touchims.utilities.SharedPrefManager;
import com.mendoza.touchims.utilities.TouchimsSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.mendoza.touchims.utilities.Constants.CURRENT_DATE;
import static com.mendoza.touchims.utilities.Constants.CURRENT_DAY;
import static com.mendoza.touchims.utilities.Constants.CURRENT_TIME;

public class AttendanceActivity extends AppCompatActivity {

    private String roomName;
    private TextView tvRoomName, tvFacName, tvSchedule, tvHeader, tvSubj, tvRemarksTitle;
    private ConstraintLayout cl, cl2;
    private Spinner spnRemarks, spnDismissal;
    private Button btnSubmit;
    private ProgressDialog progressDialog;
    private Course course = new Course();
    private Report report = new Report();
    private User user;
    private Term term;
    private String indicator = "";
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnSubmit:
                    if (indicator.equals("create")) {
                        if (spnRemarks.getSelectedItem().toString().equals("Please select...")) {
                            Toast.makeText(getApplicationContext(), "Please choose a remark", Toast.LENGTH_LONG).show();
                        } else {
                            showAlertDialog("First Bell Remark", "Continue?");
                        }
                    } else {
                        if (spnDismissal.getSelectedItem().toString().equals("Please select...")) {
                            Toast.makeText(getApplicationContext(), "Please choose a remark", Toast.LENGTH_LONG).show();
                        } else {
                            showAlertDialog("Early Dismissal?", "Continue?");
                        }
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = SharedPrefManager.getInstance(this).getUser();
        term = SharedPrefManager.getInstance(this).getTerm();

        findViews();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            roomName = extras.getString("ROOM");
        }

        getRoomDetails();
    }

    private void findViews() {
        progressDialog = new ProgressDialog(this);

        tvRoomName = findViewById(R.id.tvRoomName);
        tvFacName = findViewById(R.id.tvFacName);
        tvSchedule = findViewById(R.id.tvSchedule);
        tvHeader = findViewById(R.id.tvHeader);
        tvSubj = findViewById(R.id.tvSubj);
        tvRemarksTitle = findViewById(R.id.tvRemarksTitle);

        spnRemarks = findViewById(R.id.spnRemarks);
        spnDismissal = findViewById(R.id.spnDismissal);

        cl = findViewById(R.id.classInfoLayout);
        cl.setVisibility(View.VISIBLE);
        cl2 = findViewById(R.id.remarksLayout);
        cl2.setVisibility(View.VISIBLE);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(clickListener);

    }

//    private void getReportDetails(){
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_REPORT_DETAILS,
//                new Response.Listener<String>() {
//                    @RequiresApi(api = Build.VERSION_CODES.O)
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            if (!jsonObject.getBoolean("error")) {
//
//
//                                report.setOffer_no(jsonObject.getInt("offer_no"));
//                                report.setSch_time(jsonObject.getString("sch_time"));
//                                report.setFac_name(jsonObject.getString("fac_name"));
//                                report.setChecker_name(jsonObject.getString("checker_name"));
//                                report.setDate(jsonObject.getString("date"));
//                                report.setRoom(jsonObject.getString("room"));
//                                report.setSubj_no(jsonObject.getString("subj_no"));
//                                report.setChecker_firstRemark(jsonObject.getString("checker_firstRemark"));
//                                report.setChecker_secondRemark(jsonObject.getString("checker_secondRemark"));
//                                report.setDept(jsonObject.getString("dept"));
//
//                                spnDismissal.setEnabled(true);
//                                spnRemarks.setEnabled(false);
//
//                                //if naay sud -- update
//
//                                Toast.makeText(getApplicationContext(), jsonObject.getString("message" + CURRENT_DATE + course.getOffer_no()), Toast.LENGTH_LONG).show();
//
//
//                            } else {
//
//
//                                    //if walay sud -- create
//                                Toast.makeText(getApplicationContext(), jsonObject.getString("message" + CURRENT_DATE + course.getOffer_no()), Toast.LENGTH_LONG).show();
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        }
//        ) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("offer_no", String.valueOf(course.getOffer_no()));
//                params.put("reportDate", CURRENT_DATE);
//                return params;
//            }
//        };
//        TouchimsSingleton.getInstance(this).addToRequestQueue(stringRequest);
//    }

    private void getRoomDetails() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_COURSE_DETAILS,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getBoolean("error")) {

                                cl.setVisibility(View.VISIBLE);
                                cl2.setVisibility(View.VISIBLE);

                                course.setOffer_no(jsonObject.getInt("offer_no"));
                                course.setFac_id(jsonObject.getInt("fac_id"));
                                course.setSubj_no(jsonObject.getString("subj_no"));
                                course.setCredit_units(jsonObject.getInt("credit_units"));
                                course.setSch_days(jsonObject.getString("sch_days"));
                                course.setSch_time(jsonObject.getString("sch_time"));
                                course.setRm(jsonObject.getString("rm"));
                                course.setFac_name(jsonObject.getString("fac_name"));
                                course.setDepartment(jsonObject.getString("dept"));

                                tvRoomName.setText(course.getRm());
                                tvFacName.setText(course.getFac_name().toUpperCase());
                                tvSchedule.setText(course.getSch_time());
                                tvSubj.setText(course.getSubj_no());

                                if (!jsonObject.getBoolean("remark_error")) {
                                    indicator = "update";
                                    spnRemarks.setEnabled(false);

                                    if(jsonObject.getString("checker_secondRemark").equals("")){
                                        btnSubmit.setVisibility(View.VISIBLE);
                                    }else{
                                        btnSubmit.setVisibility(View.GONE);
                                    }

                                    switch (jsonObject.getString("checker_firstRemark")) {
                                        case "Present":
                                            spnRemarks.setSelection(1);
                                            break;
                                        case "Absent":
                                            spnRemarks.setSelection(2);
                                            break;
                                        case "Late":
                                            spnRemarks.setSelection(3);
                                            break;
                                        default:
                                            spnRemarks.setSelection(0);
                                            break;

                                    }
                                    switch (jsonObject.getString("checker_secondRemark")) {
                                        case "":
                                            spnDismissal.setEnabled(true);
                                            break;
                                        case "No":
                                            spnDismissal.setEnabled(false);
                                            spnDismissal.setSelection(1);
                                            break;
                                        case "Yes":
                                            spnDismissal.setEnabled(false);
                                            spnDismissal.setSelection(2);
                                            break;
                                        default:
                                            spnDismissal.setSelection(0);
                                            break;
                                    }
                                } else {
                                    //way sud true
//                                    tvRemarksTitle.setText(jsonObject.getString("remark_message") + "way sud" + jsonObject.getBoolean("remark_error"));
                                    spnRemarks.setEnabled(true);
                                    spnDismissal.setEnabled(false);
                                    indicator = "create";
                                }

                            } else {
                                tvHeader.setText(jsonObject.getString("message"));
                                ConstraintLayout cl = findViewById(R.id.classInfoLayout);
                                cl.setVisibility(View.GONE);
                                cl2.setVisibility(View.GONE);
//                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("room", roomName);
                params.put("day", CURRENT_DAY);
                params.put("time", CURRENT_TIME);
                params.put("reportDate", CURRENT_DATE);
                params.put("term_cd", String.valueOf(term.getTerm_cd()));
                return params;
            }
        };
        TouchimsSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void createReport() {

        progressDialog.setMessage("Adding remark...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REPORT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            spnRemarks.setEnabled(false);
                            spnDismissal.setEnabled(true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("offer_no", String.valueOf(course.getOffer_no()));
                params.put("sch_time", course.getSch_time());
                params.put("fac_name", course.getFac_name());
                params.put("checker_name", user.getLastName() + ", " + user.getFirstName());
                params.put("reportDate", CURRENT_DATE);
                params.put("room", course.getRm());
                params.put("subj", course.getSubj_no());
                params.put("checker_firstRemark", spnRemarks.getSelectedItem().toString());
                params.put("dept", course.getDepartment());
                return params;
            }
        };

        TouchimsSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void updateReport() {

        progressDialog.setMessage("Updating remark...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPDATE_REPORT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            spnDismissal.setEnabled(false);
                            btnSubmit.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("offer_no", String.valueOf(course.getOffer_no()));
                params.put("reportDate", CURRENT_DATE);
                params.put("checker_secondRemark", spnDismissal.getSelectedItem().toString());
                return params;
            }
        };

        TouchimsSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void showAlertDialog(String title, String message){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(indicator.equals("create")){
                    createReport();
                    indicator = "update";

                }
                else{
                    updateReport();
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
