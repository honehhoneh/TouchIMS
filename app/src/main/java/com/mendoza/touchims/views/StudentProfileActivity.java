package com.mendoza.touchims.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mendoza.touchims.R;
import com.mendoza.touchims.fragments.FloorFragment;
import com.mendoza.touchims.fragments.RemarksFragment;
import com.mendoza.touchims.models.User;
import com.mendoza.touchims.utilities.SharedPrefManager;

public class StudentProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView navFullName;
    Spinner spinnerBldgs, spinnerFloors;
    User user;
    LinearLayout layout;

    String[] floorArray;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        user = SharedPrefManager.getInstance(this).getUser();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        layout = findViewById(R.id.tabs);
        layout.setVisibility(LinearLayout.GONE);

        spinnerBldgs = findViewById(R.id.spnBldgs);
        spinnerFloors = findViewById(R.id.spnFloors);
        locationViews();


//        default page
        navigationView.setCheckedItem(R.id.nav_remarks);
        getSupportActionBar().setTitle("Remarks Made");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.student_frame_container, new RemarksFragment())
                .commit();


        View headerView = navigationView.getHeaderView(0);
        navFullName = headerView.findViewById(R.id.tvName);
        navFullName.setText(user.getFirstName() + " " + user.getLastName());


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_check) {
            getSupportActionBar().setTitle("Monitoring");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.student_frame_container, new FloorFragment())
                    .addToBackStack(null)
                    .commit();
            layout.setVisibility(LinearLayout.VISIBLE);
            spinnerFloors.setSelection(0);
            spinnerBldgs.setSelection(0);


        } else if (id == R.id.nav_remarks) {
            getSupportActionBar().setTitle("Remarks Made");
            layout.setVisibility(LinearLayout.GONE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.student_frame_container, new RemarksFragment())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_logout) {
            logOut();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?");
        builder.setMessage("You will be logged out of Touch IMS.");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void locationViews() {
        spinnerBldgs.setPrompt("Select Bldg(s)");

        spinnerBldgs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerBldgs.getSelectedItemPosition() == 0) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getBaseContext(),
                            R.array.main_floors, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerFloors.setPrompt("Select Floor");
                    spinnerFloors.setAdapter(adapter);
                } else if (spinnerBldgs.getSelectedItemPosition() == 1) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getBaseContext(),
                            R.array.sem_floors, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerFloors.setPrompt("Select Floor");
                    spinnerFloors.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerFloors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerBldgs.getSelectedItemPosition() == 0) {
                    switch (spinnerFloors.getSelectedItem().toString()){
                        case "Basement":
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.student_frame_container, FloorFragment.newInstance("MAIN", 0))
                                    .commit();
                            break;
                        case "Third Floor":
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.student_frame_container, FloorFragment.newInstance("MAIN", 3))
                                    .commit();
                            break;
                        default:
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.student_frame_container, new FloorFragment())
                                    .commit();
                            break;
                    }
                } else if (spinnerBldgs.getSelectedItemPosition() == 1) {
                    switch (spinnerFloors.getSelectedItem().toString()) {
                        case "Second Floor":
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.student_frame_container, FloorFragment.newInstance("SEM", 2))
                                    .commit();
                            break;
                        case "Third Floor":
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.student_frame_container, FloorFragment.newInstance("SEM", 3))
                                    .commit();
                            break;
                        case "Fourth Floor":
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.student_frame_container, FloorFragment.newInstance("SEM", 4))
                                    .commit();
                            break;
                        case "Fifth Floor":
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.student_frame_container, FloorFragment.newInstance("SEM", 5))
                                    .commit();
                            break;
                        default:
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.student_frame_container, new FloorFragment())
                                    .commit();
                            break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
