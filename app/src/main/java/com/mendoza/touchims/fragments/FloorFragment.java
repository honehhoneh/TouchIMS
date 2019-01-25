package com.mendoza.touchims.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.mendoza.touchims.views.AttendanceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloorFragment extends Fragment implements View.OnClickListener {


    //MAIN BLDGS
    private Button btnEnggb1, btnEnggb2, btnEnggb3, btnEnggb4, btnEnggb5, btnEnggb6;

    //SEM BLDG
    private Button btnSem21, btnSem22, btnSem23, btnSem24, btnSem25, btnSem26, btnSem27;
    private Button btnSem31, btnSem32, btnSem33, btnSem34, btnSem35, btnSem36, btnSem37, btnSem38;
    private Button btnSem41, btnSem42, btnSem43, btnSem44, btnSem45, btnSem46, btnSem47, btnSem48, btnSem49;
    private Button btnSem51, btnSem52, btnSem53, btnSem54, btnSem55, btnSem56, btnSem57, btnSem58, btnSem59, btnSem510, btnSem511, btnSem512;

    private int floor;
    private String bldg;
    private TextView message;

    private List<Room> rooms = new ArrayList<>();
    private LinearLayout layout;

    public FloorFragment() {
    }


    public static FloorFragment newInstance(String bldg, int floor) {
        FloorFragment floorFragment = new FloorFragment();

        Bundle args = new Bundle();
        args.putString("BLDG", bldg);
        args.putInt("FLOOR", floor);
        floorFragment.setArguments(args);

        return floorFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bldg = getArguments().getString("BLDG");
            floor = getArguments().getInt("FLOOR");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if (bldg == "MAIN") {
            switch (floor) {
                case 0:
                    view = inflater.inflate(R.layout.fragment_engg_basement, container, false);
                    getFloorDetails();
                    enggBasementFloorViews(view);
                    break;
                case 3:
                    view = inflater.inflate(R.layout.fragment_main_3rdfloor, container, false);
                    break;
            }
            return view;
        } else if (bldg == "SEM") {
            switch (floor) {
                case 2:
                    view = inflater.inflate(R.layout.fragment_sem_2ndfloor, container, false);
                    getFloorDetails();
                    semSecondFloorViews(view);
                    break;
                case 3:
                    view = inflater.inflate(R.layout.fragment_sem_3rdfloor, container, false);
                    getFloorDetails();
                    semThirdFloorViews(view);
                    break;
                case 4:
                    view = inflater.inflate(R.layout.fragment_sem_4thfloor, container, false);
                    getFloorDetails();
                    semFourthFloorViews(view);
                    break;
                case 5:
                    view = inflater.inflate(R.layout.fragment_sem_5thfloor, container, false);
                    getFloorDetails();
                    semFifthFloorViews(view);
                    break;
            }
            return view;

        } else {
            view = inflater.inflate(R.layout.fragment_floor, container, false);
            return view;
        }


    }


    public void semSecondFloorViews(View view) {
        btnSem21 = view.findViewById(R.id.btnSem21);
        btnSem22 = view.findViewById(R.id.btnSem22);
        btnSem23 = view.findViewById(R.id.btnSem23);
        btnSem24 = view.findViewById(R.id.btnSem24);
        btnSem25 = view.findViewById(R.id.btnSem25);
        btnSem26 = view.findViewById(R.id.btnSem26);
        btnSem27 = view.findViewById(R.id.btnSem27);

        btnSem21.setOnClickListener(this);
        btnSem22.setOnClickListener(this);
        btnSem23.setOnClickListener(this);
        btnSem24.setOnClickListener(this);
        btnSem25.setOnClickListener(this);
        btnSem26.setOnClickListener(this);
        btnSem27.setOnClickListener(this);

    }

    public void semThirdFloorViews(View view) {
        btnSem31 = view.findViewById(R.id.btnSem31);
        btnSem32 = view.findViewById(R.id.btnSem32);
        btnSem33 = view.findViewById(R.id.btnSem33);
        btnSem34 = view.findViewById(R.id.btnSem34);
        btnSem35 = view.findViewById(R.id.btnSem35);
        btnSem36 = view.findViewById(R.id.btnSem36);
        btnSem37 = view.findViewById(R.id.btnSem37);
        btnSem38 = view.findViewById(R.id.btnSem38);

        btnSem31.setOnClickListener(this);
        btnSem32.setOnClickListener(this);
        btnSem33.setOnClickListener(this);
        btnSem34.setOnClickListener(this);
        btnSem35.setOnClickListener(this);
        btnSem36.setOnClickListener(this);
        btnSem37.setOnClickListener(this);
        btnSem38.setOnClickListener(this);


    }

    public void semFourthFloorViews(View view) {
        btnSem41 = view.findViewById(R.id.btnSem41);
        btnSem42 = view.findViewById(R.id.btnSem42);
        btnSem43 = view.findViewById(R.id.btnSem43);
        btnSem44 = view.findViewById(R.id.btnSem44);
        btnSem45 = view.findViewById(R.id.btnSem45);
        btnSem46 = view.findViewById(R.id.btnSem46);
        btnSem47 = view.findViewById(R.id.btnSem47);
        btnSem48 = view.findViewById(R.id.btnSem48);
        btnSem49 = view.findViewById(R.id.btnSem49);

        btnSem41.setOnClickListener(this);
        btnSem42.setOnClickListener(this);
        btnSem43.setOnClickListener(this);
        btnSem44.setOnClickListener(this);
        btnSem45.setOnClickListener(this);
        btnSem46.setOnClickListener(this);
        btnSem47.setOnClickListener(this);
        btnSem48.setOnClickListener(this);
        btnSem49.setOnClickListener(this);

    }

    public void semFifthFloorViews(View view) {
        btnSem51 = view.findViewById(R.id.btnSem51);
        btnSem52 = view.findViewById(R.id.btnSem52);
        btnSem53 = view.findViewById(R.id.btnSem53);
        btnSem54 = view.findViewById(R.id.btnSem54);
        btnSem55 = view.findViewById(R.id.btnSem55);
        btnSem56 = view.findViewById(R.id.btnSem56);
        btnSem57 = view.findViewById(R.id.btnSem57);
        btnSem58 = view.findViewById(R.id.btnSem58);
        btnSem59 = view.findViewById(R.id.btnSem59);
        btnSem510 = view.findViewById(R.id.btnSem510);
        btnSem511 = view.findViewById(R.id.btnSem511);
        btnSem512 = view.findViewById(R.id.btnSem512);

        btnSem51.setOnClickListener(this);
        btnSem52.setOnClickListener(this);
        btnSem53.setOnClickListener(this);
        btnSem54.setOnClickListener(this);
        btnSem55.setOnClickListener(this);
        btnSem56.setOnClickListener(this);
        btnSem57.setOnClickListener(this);
        btnSem58.setOnClickListener(this);
        btnSem59.setOnClickListener(this);
        btnSem510.setOnClickListener(this);
        btnSem511.setOnClickListener(this);
        btnSem512.setOnClickListener(this);

    }

    public void enggBasementFloorViews(View view) {
        btnEnggb1 = view.findViewById(R.id.btnEnggb1);
        btnEnggb2 = view.findViewById(R.id.btnEnggb2);
        btnEnggb3 = view.findViewById(R.id.btnEnggb3);
        btnEnggb4 = view.findViewById(R.id.btnEnggb4);
        btnEnggb5 = view.findViewById(R.id.btnEnggb5);
        btnEnggb6 = view.findViewById(R.id.btnEnggb6);

        btnEnggb1.setOnClickListener(this);
        btnEnggb2.setOnClickListener(this);
        btnEnggb3.setOnClickListener(this);
        btnEnggb4.setOnClickListener(this);
        btnEnggb5.setOnClickListener(this);
        btnEnggb6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                Button button= view.findViewById(view.getId());
                String room = button.getText().toString();

//                AttendanceCheckingFragment acf = new AttendanceCheckingFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("ROOM", room);
//                acf.setArguments(bundle);
//
//                getFragmentManager().beginTransaction().replace(R.id.student_frame_container, acf, "Attendance")
//                        .addToBackStack(null)
//                        .commit();

                Intent intent = new Intent(getActivity(), AttendanceActivity.class);
                intent.putExtra("ROOM",room);
                startActivity(intent);

                break;
        }
    }

    private void getFloorDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_FLOOR_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getBoolean("error")) {
                                JSONArray array = jsonObject.getJSONArray("details");
                                rooms.clear();

                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    Room room = new Room(object.getString("roomName"), object.getInt("floor"), object.getString("bldg"), object.getInt("legend"));
                                    rooms.add(room);
                                }
                                setButtonTexts();
                            } else {
                                Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("bldg", bldg);
                params.put("floor", Integer.toString(floor));

                return params;
            }
        };

        TouchimsSingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void setButtonTexts() {
        if(bldg == "MAIN"){
            switch (floor){
                case 0:
                    btnEnggb1.setText(rooms.get(0).getRoomName());
                    btnEnggb2.setText(rooms.get(1).getRoomName());
                    btnEnggb3.setText(rooms.get(2).getRoomName());
                    btnEnggb4.setText(rooms.get(3).getRoomName());
                    btnEnggb5.setText(rooms.get(4).getRoomName());
                    btnEnggb6.setText(rooms.get(5).getRoomName());
                    break;
            }
        }

        if (bldg == "SEM") {
            switch (floor) {
                case 2:
                    btnSem21.setText(rooms.get(0).getRoomName());
                    btnSem22.setText(rooms.get(1).getRoomName());
                    btnSem23.setText(rooms.get(2).getRoomName());
                    btnSem24.setText(rooms.get(3).getRoomName());
                    btnSem25.setText(rooms.get(4).getRoomName());
                    btnSem26.setText(rooms.get(5).getRoomName());
                    btnSem27.setText(rooms.get(6).getRoomName());
                    break;
                case 3:
                    btnSem31.setText(rooms.get(0).getRoomName());
                    btnSem32.setText(rooms.get(1).getRoomName());
                    btnSem33.setText(rooms.get(2).getRoomName());
                    btnSem34.setText(rooms.get(3).getRoomName());
                    btnSem35.setText(rooms.get(4).getRoomName());
                    btnSem36.setText(rooms.get(5).getRoomName());
                    btnSem37.setText(rooms.get(6).getRoomName());
                    btnSem38.setText(rooms.get(7).getRoomName());
                    break;
                case 4:
                    btnSem41.setText(rooms.get(0).getRoomName());
                    btnSem42.setText(rooms.get(1).getRoomName());
                    btnSem43.setText(rooms.get(2).getRoomName());
                    btnSem44.setText(rooms.get(3).getRoomName());
                    btnSem45.setText(rooms.get(4).getRoomName());
                    btnSem46.setText(rooms.get(5).getRoomName());
                    btnSem47.setText(rooms.get(6).getRoomName());
                    btnSem48.setText(rooms.get(7).getRoomName());
                    btnSem49.setText(rooms.get(8).getRoomName());
                    break;
                case 5:
                    btnSem51.setText(rooms.get(0).getRoomName());
                    btnSem52.setText(rooms.get(1).getRoomName());
                    btnSem53.setText(rooms.get(2).getRoomName());
                    btnSem54.setText(rooms.get(3).getRoomName());
                    btnSem55.setText(rooms.get(4).getRoomName());
                    btnSem56.setText(rooms.get(5).getRoomName());
                    btnSem57.setText(rooms.get(6).getRoomName());
                    btnSem58.setText(rooms.get(7).getRoomName());
                    btnSem59.setText(rooms.get(8).getRoomName());
                    btnSem510.setText(rooms.get(9).getRoomName());
                    btnSem511.setText(rooms.get(10).getRoomName());
                    btnSem512.setText(rooms.get(11).getRoomName());
                    break;
            }
        }
    }
}
