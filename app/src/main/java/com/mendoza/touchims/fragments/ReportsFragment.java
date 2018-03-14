package com.mendoza.touchims.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.mendoza.touchims.R;
import com.mendoza.touchims.adapters.ReportsAdapter;
import com.mendoza.touchims.adapters.RequestsAdapter;
import com.mendoza.touchims.models.Report;
import com.mendoza.touchims.models.RoomRequest;
import com.mendoza.touchims.models.User;

import java.util.List;

public class ReportsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReportsAdapter adapter;
    private Spinner spinnerBldgs, spinnerFloors;
    private FloatingActionButton fab;
    User user;

    private List<Report> reports;

    public ReportsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reports, container, false);
        return rootView;
    }

}
