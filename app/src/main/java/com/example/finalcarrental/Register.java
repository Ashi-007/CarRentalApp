package com.example.finalcarrental;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class Register extends AppCompatActivity {

    Button requestButton;
    TextView alreadySignin;
    private AwesomeValidation awesomeValidation;
    TextView dataName; // a text field to display the request response
    TextView dataEmail; // a text field where the data to be sent is entered
    //TextView dataMobile; // a text field where the data to be sent is entered
    TextView dataPassword; // a text field where the data to be sent is entered
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String NameHolder, EmailHolder, PasswordHolder ;

    // Storing server url into String variable.
    String HttpUrl = "http://192.168.43.112/loginwithphp/signup.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        requestButton = (Button) findViewById(R.id.reg_btn);
        alreadySignin = (TextView) findViewById(R.id.reg_already_signin);
        requestButton.setOnClickListener(mMyListener);
        alreadySignin.setOnClickListener(mMyListener);

        dataName = (TextView) findViewById(R.id.reg_username);
        dataEmail = (TextView) findViewById(R.id.reg_email);
//        dataMobile = (TextView) findViewById(R.id.editTextMobile);
        dataPassword = (TextView) findViewById(R.id.reg_Password);

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        //adding validation to edittexts
        awesomeValidation.addValidation(this, R.id.reg_username, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.reg_email, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        //awesomeValidation.addValidation(this, R.id.editTextMobile, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        awesomeValidation.addValidation(this, R.id.reg_Password, regexPassword, R.string.passworderror);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(Register.this);

        progressDialog = new ProgressDialog(Register.this);

    }

    private View.OnClickListener mMyListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.reg_btn:
                    if (awesomeValidation.validate()) {
                        regUser();
                    }
                    break;
                case R.id.reg_already_signin:
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                    break;
                default:
                    break;
            }
        }
    };
    public void regUser(){
        progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        progressDialog.show();

        NameHolder = dataName.getText().toString().trim();
        EmailHolder = dataEmail.getText().toString().trim();
       // MobileHolder = dataMobile.getText().toString().trim();
        PasswordHolder = dataPassword.getText().toString().trim();

        String myurl = "http://192.168.43.112/loginwithphp/signup.php?FullName=" + NameHolder +
                "&EmailId=" + EmailHolder + "&Password=" + PasswordHolder;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        // Showing response message coming from server.
                        Toast.makeText(Register.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        // Showing error message if something goes wrong.
                        Toast.makeText(Register.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(stringRequest);
    }

}