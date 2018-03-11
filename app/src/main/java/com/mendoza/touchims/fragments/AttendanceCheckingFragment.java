package com.mendoza.touchims.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mendoza.touchims.R;


public class AttendanceCheckingFragment extends Fragment {


    public AttendanceCheckingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attendance_checking, container, false);
        return rootView;
    }

}
