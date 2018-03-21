package com.mendoza.touchims.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mendoza.touchims.R;
import com.mendoza.touchims.models.Term;
import com.mendoza.touchims.models.User;
import com.mendoza.touchims.utilities.Constants;
import com.mendoza.touchims.utilities.SharedPrefManager;
import com.mendoza.touchims.utilities.TouchimsSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mIdNumView, mPasswordView;
    private Button mSignInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            User user = SharedPrefManager.getInstance(this).getUser();
            Term term = SharedPrefManager.getInstance(this).getTerm();


            if (user.getClassification().toUpperCase().equals("STUDENT")) {
                startActivity(new Intent(this, StudentProfileActivity.class));
            } else {
                startActivity(new Intent(this, FacultyProfileActivity.class));
            }
            return;
        }

        mIdNumView = findViewById(R.id.edtIdNum);
        mPasswordView = findViewById(R.id.edtPassword);

        mSignInButton = findViewById(R.id.btnSignin);
        mSignInButton.setOnClickListener(this);


    }

    private boolean validateForm() {
        boolean valid = true;
        String idNum = mIdNumView.getText().toString();
        if (TextUtils.isEmpty(idNum)) {
            mIdNumView.setError("Required.");
            valid = false;
        } else {
            mIdNumView.setError(null);
        }

        String password = mPasswordView.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Required.");
            valid = false;
        } else {
            mPasswordView.setError(null);
        }
        return valid;
    }

    private void userLogin() {
        final String idNum = mIdNumView.getText().toString().trim();
        final String pass = mPasswordView.getText().toString().trim();
//        final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        final String currentDate = "2016-10-03";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getBoolean("error")) {
                                User user = new User();
                                user.setFirstName(jsonObject.getString("firstName"));
                                user.setLastName(jsonObject.getString("lastName"));
                                user.setIdNum(jsonObject.getInt("idNum"));
                                user.setClassification(jsonObject.getString("classification"));
                                user.setCollege(jsonObject.getString("college"));

                                Term term = new Term();
                                term.setTerm_cd(jsonObject.getInt("term_cd"));
                                term.setPeriodStart(jsonObject.getString("periodStart"));
                                term.setPeriodEnd(jsonObject.getString("periodEnd"));
                                term.setTerm(jsonObject.getString("term"));


                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(user, term);

                                if (user.getClassification().toUpperCase().equals("STUDENT")) {
                                    Intent intent = new Intent(LoginActivity.this, StudentProfileActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else if (user.getClassification().toUpperCase().equals("CHAIRPERSON") || user.getClassification().toUpperCase().equals("FACULTY") || user.getClassification().toUpperCase().equals("ADMIN") || user.getClassification().toUpperCase().equals("DEAN")) {
                                    Intent intent = new Intent(LoginActivity.this, FacultyProfileActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idNum", idNum);
                params.put("password", pass);
                params.put("currentDate", Constants.CURRENT_DATE);
                return params;
            }
        };
        TouchimsSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignin:
                if (validateForm()) {
                    userLogin();
                }
                break;

        }
    }


}

