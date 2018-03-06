package com.mendoza.touchims.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mendoza.touchims.utilities.Constants;
import com.mendoza.touchims.R;
import com.mendoza.touchims.utilities.SharedPrefManager;
import com.mendoza.touchims.utilities.TouchimsSingleton;
import com.mendoza.touchims.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChangeRoomRequestFragment extends Fragment {


    public ChangeRoomRequestFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_change_room_request, container, false);
        findViews(rootView);
        viewClicks();
        return rootView;
    }

    ProgressDialog progressDialog;
    Spinner mDept, mDays;
    EditText mSubject, mRoom, mTimeStart, mTimeEnd;
    EditText mActivity, mActDate, mActVenue, mActStart, mActEnd;
    Button btnSend;


    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment(v);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    private void findViews(View view) {
        mDept = view.findViewById(R.id.spinnerDept);
        mDays = view.findViewById(R.id.spinnerDays);

        mSubject = view.findViewById(R.id.edtSubject);
        mRoom = view.findViewById(R.id.edtRoom);
        mTimeStart = view.findViewById(R.id.edtTimeStart);
        mTimeEnd = view.findViewById(R.id.edtTimeEnd);
        mActivity = view.findViewById(R.id.edtActivity);
        mActVenue = view.findViewById(R.id.edtActVenue);
        mActDate = view.findViewById(R.id.edtActDate);
        mActStart = view.findViewById(R.id.edtActStart);
        mActEnd = view.findViewById(R.id.edtActEnd);

        btnSend = view.findViewById(R.id.btnSend);
    }

    private void viewClicks() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    sendRequest();
                }
            }
        });
        mTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(view);
            }
        });
        mTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(view);
            }
        });
        mActStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(view);
            }
        });
        mActEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(view);
            }
        });
        mActDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;
        if (TextUtils.isEmpty(mSubject.getText().toString())) {
            mSubject.setError("Required.");
            valid = false;
        } else {
            mSubject.setError(null);
        }
        if (TextUtils.isEmpty(mRoom.getText().toString())) {
            mRoom.setError("Required.");
            valid = false;
        } else {
            mRoom.setError(null);
        }
        if (TextUtils.isEmpty(mTimeStart.getText().toString())) {
            mTimeStart.setError("Required.");
            valid = false;
        } else {
            mTimeStart.setError(null);
        }
        if (TextUtils.isEmpty(mTimeEnd.getText().toString())) {
            mTimeEnd.setError("Required.");
            valid = false;
        } else {
            mTimeEnd.setError(null);
        }
        if (TextUtils.isEmpty(mActivity.getText().toString())) {
            mActivity.setError("Required.");
            valid = false;
        } else {
            mActivity.setError(null);
        }
        if (TextUtils.isEmpty(mActVenue.getText().toString())) {
            mActVenue.setError("Required.");
            valid = false;
        } else {
            mActVenue.setError(null);
        }
        if (TextUtils.isEmpty(mActDate.getText().toString())) {
            mActDate.setError("Required.");
            valid = false;
        } else {
            mActDate.setError(null);
        }
        if (TextUtils.isEmpty(mActStart.getText().toString())) {
            mActStart.setError("Required.");
            valid = false;
        } else {
            mActStart.setError(null);
        }
        if (TextUtils.isEmpty(mActEnd.getText().toString())) {
            mActEnd.setError("Required.");
            valid = false;
        } else {
            mActEnd.setError(null);
        }
        if (mDept.getSelectedItem().toString().equals("Select department...") || mDays.getSelectedItem().toString().equals("Select schedule of class days...")) {
            Toast.makeText(getContext(), "Invalid Department/Schedule", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    private void sendRequest() {

        User user = SharedPrefManager.getInstance(getActivity()).getUser();
//        Toast.makeText(getContext(), "SEEEEEEEND", Toast.LENGTH_SHORT).show();
        final String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        final String dept, days, subj, rm, strt, end, act, actdate, actend, actstart, actvenue, fullName;
        fullName = user.getLastName() + "," + user.getFirstName();
        dept = mDept.getSelectedItem().toString();
        days = mDays.getSelectedItem().toString();
        subj = mSubject.getText().toString();
        rm = mRoom.getText().toString();
        strt = mTimeStart.getText().toString();
        end = mTimeEnd.getText().toString();
        act = mActivity.getText().toString();
        actdate = mActDate.getText().toString();
        actstart = mActStart.getText().toString();
        actend = mActEnd.getText().toString();
        actvenue = mActVenue.getText().toString();


//        progressDialog.setMessage("Registering user...");
//        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CHANGE_ROOM_REQUEST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getString("error").equals("false")){
                            Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            getFragmentManager().beginTransaction().replace(R.id.frame_container, new ReportsFragment()).commit();
                            }else{
                                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                progressDialog.hide();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("department", dept);
                params.put("dateOfNotif", date);
                params.put("subject", subj);
                params.put("sch_time", strt + " - "+end);
                params.put("sch_days", days);
                params.put("room", rm);
                params.put("classActivities", act);
                params.put("actDate", actdate);
                params.put("actTime", actstart + " - "+ actend);
                params.put("actVenue", actvenue);
                params.put("instructor", fullName);

                return params;
            }
        };

        TouchimsSingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }


}
