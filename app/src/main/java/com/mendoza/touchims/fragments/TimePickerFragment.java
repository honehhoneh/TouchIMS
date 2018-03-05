package com.mendoza.touchims.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.util.Calendar;

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    EditText mTime;

    public TimePickerFragment(View view) {
        mTime = (EditText) view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final java.util.Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour+12, minute,
                DateFormat.is24HourFormat(getActivity()));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        int hour = 0;
        String ampm;
        if (hourOfDay > 12) {
        hour = hourOfDay - 12;
        ampm = "pm";
        }else{
            hour = hourOfDay;
            ampm = "am";
        }
        mTime.setText(new DecimalFormat("00").format(hour)+ ":" + new DecimalFormat("00").format(minute) + " "+ ampm);
    }


}