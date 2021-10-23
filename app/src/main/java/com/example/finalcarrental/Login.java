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

public class Login extends AppCompatActivity {

    Button loginButton;
    TextView alreadySignup;
    private AwesomeValidation awesomeValidation;
    ProgressDialog progressDialog;
    String EmailHolder, PasswordHolder ;
    TextView dataEmail; // a text field where the data to be sent is entered
    TextView dataPassword; // a text field where the data to be sent is entered


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        loginButton = (Button) findViewById(R.id.lgn_btn);
        alreadySignup = (TextView) findViewById(R.id.lgn_already_signup);
        loginButton.setOnClickListener(mMyListener);
        alreadySignup.setOnClickListener(mMyListener);

        dataEmail = (TextView) findViewById(R.id.lgn_email);
        dataPassword = (TextView) findViewById(R.id.lgn_pwd);

        awesomeValidation.addValidation(this, R.id.lgn_email, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        progressDialog = new ProgressDialog(Login.this);
    }
    private View.OnClickListener mMyListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId() /*to get clicked view id**/) {
                case R.id.lgn_btn:
                    if (awesomeValidation.validate()) {
//                        Toast.makeText(LoginActivity.this, "Checking", Toast.LENGTH_LONG).show();
                        regUser();
                    }
                    break;
                case R.id.lgn_already_signup:
                    Intent i = new Intent(getApplicationContext(), Register.class);
                    startActivity(i);
                    break;
                default:
                    break;
            }
        }
    };

    public void regUser(){
        progressDialog.setMessage("Please Wait, We are Checking Your Data on Server");
        progressDialog.show();

        EmailHolder = dataEmail.getText().toString().trim();
        PasswordHolder = dataPassword.getText().toString().trim();

        String myurl = "http://192.168.43.112/loginwithphp/login.php?EmailId=" +
               EmailHolder + "&Password=" + PasswordHolder;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, myurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        if (ServerResponse.equals("Successfully Login")){
                            Toast.makeText(Login.this, ServerResponse, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), Homeact.class);
  //                          i.putExtra("prof_Eamil",EmailHolder);
//                            i.putExtra("prof_pwd",PasswordHolder);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(Login.this, "Please Enter Valid Data!...", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        // Showing error message if something goes wrong.
                        Toast.makeText(Login.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(stringRequest);
    }
}