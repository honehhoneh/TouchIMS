package com.mendoza.touchims.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mendoza.touchims.R;
import com.mendoza.touchims.models.Course;
import com.mendoza.touchims.models.User;
import com.mendoza.touchims.utilities.Constants;
import com.mendoza.touchims.utilities.SharedPrefManager;
import com.mendoza.touchims.utilities.TouchimsSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChangeRoomRequestFragment extends Fragment {


    ProgressDialog progressDialog;
    TextView tvOffer;
    EditText mOfferNo, mDetails, mActivity, mActDate, mActVenue, mActStart, mActEnd;
    Button btnEnter, btnSend;
    Course course = new Course();
    String fullName = "";
    User user = SharedPrefManager.getInstance(getActivity()).getUser();
    ConstraintLayout clayout;


    public ChangeRoomRequestFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_change_room_request, container, false);

        findViews(rootView);
        viewClicks();
        fullName = user.getLastName() + ", " + user.getFirstName();
        return rootView;
    }


    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment(v);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    private void findViews(View view) {
        progressDialog = new ProgressDialog(getContext());
        clayout = view.findViewById(R.id.reqLayout);
        clayout.setVisibility(View.GONE);
        tvOffer = view.findViewById(R.id.textView);

        mOfferNo = view.findViewById(R.id.edtOfferNo);
        mDetails = view.findViewById(R.id.edtDetails);

        mActivity = view.findViewById(R.id.edtActivity);
        mActVenue = view.findViewById(R.id.edtActVenue);
        mActDate = view.findViewById(R.id.edtActDate);
        mActStart = view.findViewById(R.id.edtActStart);
        mActEnd = view.findViewById(R.id.edtActEnd);

        btnSend = view.findViewById(R.id.btnSend);
        btnEnter = view.findViewById(R.id.btnEnter);
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

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateOfferNo()) {
                    getSubjectDetails();

                }
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


    private boolean validateOfferNo() {
        boolean valid = true;

        if (TextUtils.isEmpty(mOfferNo.getText().toString())) {
            mOfferNo.setError("Required.");
            valid = false;
        } else {
            mActivity.setError(null);
        }

        return valid;
    }

    private boolean validateForm() {
        boolean valid = true;

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


        return valid;
    }

    private void getSubjectDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_SUBJECT_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (!jsonObject.getBoolean("error")) {
                                course.setFac_id(jsonObject.getInt("fac_id"));
                                course.setSubj_no(jsonObject.getString("subj_no"));
                                course.setCredit_units(jsonObject.getInt("credit_units"));
                                course.setSch_days(jsonObject.getString("sch_days"));
                                course.setSch_time(jsonObject.getString("sch_time"));
                                course.setRm(jsonObject.getString("rm"));
                                course.setFac_name(jsonObject.getString("fac_name"));
                                course.setDepartment(jsonObject.getString("department"));

                                setViewVisibility();

                            } else {
                                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
                params.put("fac_name", fullName);
                params.put("offer_no", mOfferNo.getText().toString());
                return params;
            }
        };

        TouchimsSingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void sendRequest() {
        final String date = Constants.CURRENT_DATE;
        final String act, actdate, actend, actstart, actvenue;

        act = mActivity.getText().toString();

        SimpleDateFormat oldFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date newDate = null;
        try {
            newDate = oldFormat.parse(mActDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        actdate = newFormat.format(newDate);

        actstart = mActStart.getText().toString();
        actend = mActEnd.getText().toString();
        actvenue = mActVenue.getText().toString();


        progressDialog.setMessage("Sending request...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CHANGE_ROOM_REQUEST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("error").equals("false")) {
                                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                getFragmentManager().beginTransaction().replace(R.id.faculty_frame_container, new RequestsFragment()).commit();
                            } else {
                                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("department", course.getDepartment());
                params.put("dateOfNotif", date);
                params.put("subject", course.getSubj_no());
                params.put("sch_time", course.getSch_time());
                params.put("sch_days", course.getSch_days());
                params.put("room", course.getRm());
                params.put("classActivities", act);
                params.put("actDate", actdate);
                params.put("actTime", actstart + " - " + actend);
                params.put("actVenue", actvenue);
                params.put("instructor", fullName);

                return params;
            }
        };

        TouchimsSingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void setViewVisibility() {
        tvOffer.setText("Offer No.");
        mOfferNo.setEnabled(false);
        clayout.setVisibility(View.VISIBLE);
        btnEnter.setVisibility(View.GONE);
        mDetails.setText(course.getSubj_no().toUpperCase() + " " + course.getSch_time() + " at " + course.getRm());
        mDetails.setEnabled(false);

    }
}
