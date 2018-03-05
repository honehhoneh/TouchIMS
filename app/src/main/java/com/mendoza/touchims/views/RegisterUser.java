package com.mendoza.touchims.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mendoza.touchims.Constants;
import com.mendoza.touchims.R;
import com.mendoza.touchims.TouchimsSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private EditText mFname, mLname, mIdNum, mPass;
    private Spinner mClass, mCollege;
    private Button btnReg;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        mFname = findViewById(R.id.edtFname);
        mLname = findViewById(R.id.edtLname);
        mIdNum = findViewById(R.id.edtIdnum);
        mPass = findViewById(R.id.edtPassword);
        mClass = findViewById(R.id.spnClassification);
        mCollege = findViewById(R.id.spnCollege);
        btnReg = findViewById(R.id.btnRegister);

        btnReg.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }

    private boolean validateForm() {
        boolean valid = true;

        if (TextUtils.isEmpty(mFname.getText().toString())) {
            mFname.setError("Required.");
            valid = false;
        } else {
            mFname.setError(null);
        }

        if (TextUtils.isEmpty(mLname.getText().toString())) {
            mLname.setError("Required.");
            valid = false;
        } else {
            mLname.setError(null);
        }

        if (TextUtils.isEmpty(mIdNum.getText().toString())) {
            mIdNum.setError("Required.");
            valid = false;
        } else {
            mIdNum.setError(null);
        }

        if (TextUtils.isEmpty(mPass.getText().toString())) {
            mPass.setError("Required.");
            valid = false;
        } else {
            mPass.setError(null);
        }

        if (mCollege.getSelectedItem().toString().equals("Select department...")) {
            valid = false;
            Toast.makeText(this, "Invalid department", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    private void registerUser() {
        final String fname, lname, idNum, pass, classi, coll;
        fname = mFname.getText().toString();
        lname = mLname.getText().toString();
        idNum = mIdNum.getText().toString();
        pass = mPass.getText().toString();
        classi = mClass.getSelectedItem().toString();
        coll = mCollege.getSelectedItem().toString();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstName", fname);
                params.put("lastName", lname);
                params.put("idNum", idNum);
                params.put("password", pass);
                params.put("classification", classi);
                params.put("college", coll);
                return params;
            }
        };

        TouchimsSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                if (validateForm()) {
                    registerUser();
                }
                break;
        }

    }
}
