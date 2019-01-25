package com.mendoza.touchims.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mendoza.touchims.R;


public class AttendanceCheckingFragment extends Fragment {

    private TextView tvText;
    private LinearLayout layout;

    public AttendanceCheckingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attendance_checking, container, false);

        Bundle bundle = getArguments();
        String Room = bundle.getString("ROOM");
        tvText = rootView.findViewById(R.id.tvText);
        tvText.setText(Room);
        layout = getActivity().findViewById(R.id.tabs);
        layout.setVisibility(LinearLayout.GONE);
        return rootView;

    }





}
