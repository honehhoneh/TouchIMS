package com.mendoza.touchims.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mendoza.touchims.R;
import com.mendoza.touchims.adapters.RequestsAdapter;
import com.mendoza.touchims.models.RoomRequest;
import com.mendoza.touchims.models.User;
import com.mendoza.touchims.utilities.Constants;
import com.mendoza.touchims.utilities.SharedPrefManager;
import com.mendoza.touchims.utilities.TouchimsSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RequestsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RequestsAdapter adapter;
    private Spinner spinnerSort, spinnerFilter;
    User user;

    private List<RoomRequest> requests;

    public RequestsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_requests, container, false);
        recyclerView = rootView.findViewById(R.id.requests_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        spinnerSort = rootView.findViewById(R.id.spnSort);
        spinnerFilter = rootView.findViewById(R.id.spnFilter);

        user = SharedPrefManager.getInstance(getActivity()).getUser();

        spinnersOnItemSelected();
        spinnerFilter.setSelection(3);

        requests = new ArrayList<>();
        adapter = new RequestsAdapter(getActivity(), requests);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    private void getRequestsFromServer(String url_req, final String sort, final String filter) {
        final String fName = user.getFirstName();
        final String lName = user.getLastName();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_req, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (!jsonObject.getBoolean("error")) {
                        JSONArray array = jsonObject.getJSONArray("requests");
                        requests.clear();

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            RoomRequest roomRequest = new RoomRequest(object.getString("department"),
                                    object.getString("dateOfNotif"), object.getString("subject"), object.getString("sch_time"),
                                    object.getString("sch_days"), object.getString("room"), object.getString("classActivities"),
                                    object.getString("actDate"), object.getString("actTime"), object.getString("actVenue"),
                                    object.getString("instructor"), object.getString("adminRemark"), object.getString("adminName"));

                            requests.add(roomRequest);
                        }

                        adapter = new RequestsAdapter(getActivity(), requests);
                        recyclerView.setAdapter(adapter);
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
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstName", fName);
                params.put("lastName", lName);
                params.put("sort", sort);
                params.put("filter", filter);
                return params;
            }
        };
        TouchimsSingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }

    private void spinnersOnItemSelected() {
        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getRequestsFromServer(Constants.URL_GET_REQUESTS,
                        spinnerSort.getSelectedItem().toString(), spinnerFilter.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getRequestsFromServer(Constants.URL_GET_REQUESTS,
                        spinnerSort.getSelectedItem().toString(), spinnerFilter.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}
