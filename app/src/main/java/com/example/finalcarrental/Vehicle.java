package com.example.finalcarrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Vehicle extends AppCompatActivity {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawar_layout);

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
        recreate();

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