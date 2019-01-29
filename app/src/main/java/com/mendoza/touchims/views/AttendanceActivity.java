package com.mendoza.touchims.views;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mendoza.touchims.R;
import com.mendoza.touchims.models.Course;
import com.mendoza.touchims.utilities.Constants;
import com.mendoza.touchims.utilities.TouchimsSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mendoza.touchims.utilities.Constants.CURRENT_DAY;
import static com.mendoza.touchims.utilities.Constants.CURRENT_TIME;

public class AttendanceActivity extends AppCompatActivity{

    private List<Course> courses = new ArrayList<>();

    private String roomName;
    private TextView tvRoomName, tvFacName, tvSchedule, tvHeader, tvSubj;
    private ConstraintLayout cl, cl2;
    private Spinner spnRemarks, spnDismissal;
    private CheckBox cboxFirst, cboxSecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        tvRoomName = findViewById(R.id.tvRoomName);
        tvFacName = findViewById(R.id.tvFacName);
        tvSchedule = findViewById(R.id.tvSchedule);
        tvHeader = findViewById(R.id.tvHeader);
        tvSubj = findViewById(R.id.tvSubj);
        spnRemarks = findViewById(R.id.spnRemarks);
        spnDismissal = findViewById(R.id.spnDismissal);
        cboxFirst = findViewById(R.id.cboxFirst);
        cboxSecond = findViewById(R.id.cboxSecond);

//        spnRemarks.setEnabled(false);
//        spnDismissal.setEnabled(false);


        cl = findViewById(R.id.classInfoLayout);
        cl.setVisibility(View.VISIBLE);
        cl2 = findViewById(R.id.remarksLayout);
        cl2.setVisibility(View.VISIBLE);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            roomName = extras.getString("ROOM");
        }
        getRoomDetails();
        tvRoomName.setText(roomName);


    }


    private void getRoomDetails() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_ATTENDANCE_CHECK,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getBoolean("error")) {

                                cl.setVisibility(View.VISIBLE);
                                cl2.setVisibility(View.VISIBLE);

                                Course course = new Course();
                                course.setOffer_no(jsonObject.getInt("offer_no"));
                                course.setFac_id(jsonObject.getInt("fac_id"));
                                course.setSubj_no(jsonObject.getString("subj_no"));
                                course.setCredit_units(jsonObject.getInt("credit_units"));
                                course.setSch_days(jsonObject.getString("sch_days"));
                                course.setRm(jsonObject.getString("rm"));
                                course.setFac_name(jsonObject.getString("fac_name"));
                                course.setDepartment(jsonObject.getString("dept"));

                                String newStart = jsonObject.getString("timeStart").substring(0, 5);
                                course.setTimeStart(newStart);
                                String newEnd = jsonObject.getString("timeEnd").substring(0, 5);
                                course.setTimeEnd(newEnd);

                                tvFacName.setText(course.getFac_name().toUpperCase());
                                tvSchedule.setText(course.getTimeStart() + " - " + course.getTimeEnd());
                                tvSubj.setText(course.getSubj_no());

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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("room", roomName);
                params.put("day", CURRENT_DAY);
                params.put("time", CURRENT_TIME);
                return params;
            }
        };
        TouchimsSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}
