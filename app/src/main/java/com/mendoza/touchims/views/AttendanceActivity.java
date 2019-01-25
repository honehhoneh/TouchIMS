package com.mendoza.touchims.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mendoza.touchims.R;
import com.mendoza.touchims.models.Room;
import com.mendoza.touchims.utilities.Constants;
import com.mendoza.touchims.utilities.TouchimsSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AttendanceActivity extends AppCompatActivity {

    private String roomName;
    private TextView tvRoomName;

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

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            roomName = extras.getString("roomName");
        }
    }

    private void getRoomDetails() {


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_ATTENDANCE_CHECK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getBoolean("error")) {
                                Room room = new Room();
                                room.setRoomName(jsonObject.getString("roomName"));
                                room.setBldg(jsonObject.getString("bldg"));
                                room.setFloor(jsonObject.getInt("floor"));
                                room.setLegend(jsonObject.getInt("legend"));

                                if(room.getRoomName() != null){
                                    tvRoomName.setText(room.getRoomName().toUpperCase());
                                }


//                                if (user.getClassification().toUpperCase().equals("STUDENT")) {
//                                    Intent intent = new Intent(LoginActivity.this, StudentProfileActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else if (user.getClassification().toUpperCase().equals("CHAIRPERSON") || user.getClassification().toUpperCase().equals("FACULTY") || user.getClassification().toUpperCase().equals("ADMIN") || user.getClassification().toUpperCase().equals("DEAN")) {
//                                    Intent intent = new Intent(LoginActivity.this, FacultyProfileActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            } else {
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
                params.put("roomName", roomName);
                return params;
            }
        };
        TouchimsSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}
