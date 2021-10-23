package com.example.finalcarrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Share extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button shrbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);


        drawerLayout = (DrawerLayout)findViewById(R.id.drawar_layout);

        shrbtn = (Button)findViewById(R.id.sharebtn);


        shrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareintent = new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                String sarebody = "Download this app now :- https://play.google.com/store/apps/details?id=com.mi.android.globalFileexplorer=en";
                String sharesub="Temprory app like File Manage";

                shareintent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                shareintent.putExtra(Intent.EXTRA_TEXT,sarebody);


                startActivity(Intent.createChooser(shareintent,"Share Using"));
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
        //Redirect activity to Contact Us
        Homeact.redirectActivity(this,ContactUs.class);

    }


    public void ClickAboutus(View view){
        Homeact.redirectActivity(this,AboutUs.class);

    }

    public void ClickVehicle(View view){
        //Redirect activity to Vehicle
        Homeact.redirectActivity(this,Vehicle.class);

    }

    public void ClickShare(View view){
        recreate();
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