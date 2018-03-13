package com.mendoza.touchims.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mendoza.touchims.R;

public class RequestDetailsActivity extends AppCompatActivity {
    String dateOfNotif = "wala";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        getSupportActionBar().setTitle("Request Details");

        Bundle extras = getIntent().getExtras();
        if(extras != null){
             dateOfNotif = extras.getString("dateOfNotif");
        }

        Toast.makeText(this, dateOfNotif, Toast.LENGTH_LONG).show();


    }
}
