package com.mendoza.touchims.views;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.mendoza.touchims.R;
import com.mendoza.touchims.fragments.DatePickerFragment;
import com.mendoza.touchims.fragments.TimePickerFragment;

public class ChangeRoomRequestActivity extends AppCompatActivity {

    EditText mDept, mDate, mTimeStart, mTimeEnd, mSubject, mRoom;
    EditText mActivity, mActDate, mActVenue, mActStart, mActEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_room_request);
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment(v);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void findViews() {

    }
}
