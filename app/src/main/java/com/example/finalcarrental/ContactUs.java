package com.example.finalcarrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {

    DrawerLayout drawerLayout;
    EditText et_subject,et_message;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        drawerLayout = (DrawerLayout)findViewById(R.id.drawar_layout);

        et_subject = findViewById(R.id.email_subject);
        et_message = findViewById(R.id.email_message);
        btn = findViewById(R.id.email_send);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = et_subject.getText().toString().trim();
                String message = et_message.getText().toString().trim();
                String email = "panktip55@gmail.com";
                if(subject.isEmpty())
                {
                    Toast.makeText(ContactUs.this, "Please add Subject", Toast.LENGTH_SHORT).show();
                }
                else if(message.isEmpty())
                {
                    Toast.makeText(ContactUs.this, "Please add some Message", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String mail = "mailto:" + email +
                            "?&subject=" + Uri.encode(subject) +
                            "&body=" + Uri.encode(message);
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse(mail));
                    try {
                        startActivity(Intent.createChooser(intent,"Send Email.."));
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(ContactUs.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }





    public void ClickMenu(View view){
//open Drawer
        Homeact.openDrawer(drawerLayout);

    }

    public void ClickLogo(View view){
        //Close Drawer
        Homeact.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        //Redirect to home
        Homeact.redirectActivity(this,Homeact.class);

    }
    public void ClickProfile(View view){
        //Redirect activity to Profile
      Homeact.redirectActivity(this,Profile.class);
    }


    public void ClickContactus(View view){
        recreate();
    }


    public void ClickAboutus(View view){
        //Redirect activity to About Us
        Homeact.redirectActivity(this,AboutUs.class);

    }

    public void ClickVehicle(View view){
        //Redirect activity to Vehicle
        Homeact.redirectActivity(this,Vehicle.class);

    }

    public void ClickShare(View view){
        //Redirect activity to Share
        Homeact.redirectActivity(this,Share.class);

    }

    public void ClickFollowus(View view){
        //Redirect activity to Follow US
        Homeact.redirectActivity(this,Followus.class);

    }

    public void ClickLogout(View view){
        //back to login Screen

        Homeact.logout(this);


    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close Drawer
        Homeact.closeDrawer(drawerLayout);
    }
}