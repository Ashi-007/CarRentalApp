package com.example.finalcarrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUs extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawar_layout);

        Element aboutpg = new Element();
        aboutpg.setTitle("About Us");

        View aboutpage = new AboutPage(this).isRTL(false)
                .setImage(R.drawable.car_logo)
                .setDescription("This Launched version 1.1.0").setDescription("New Version will comming Soon...")
                .addItem(new Element().setTitle("Rental Car Version 1"))
                .addItem(aboutpg)
                .addGroup("Visit Us")
                .addEmail("panktip55@gmail.com")
                .addWebsite("www.google.com")
                .addItem(createCopyRight()).create();

        setContentView(aboutpage);
    }
        private Element createCopyRight(){
            Element copyright = new Element();
            String copyrightstring = String.format("Copyright %d by Parul University", Calendar.getInstance().get(Calendar.YEAR));

            copyright.setTitle(copyrightstring);
            //copyright.setIcon(R.mipmap.ic_launcher);
            copyright.setGravity(Gravity.CENTER);

            copyright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(AboutUs.this,copyrightstring,Toast.LENGTH_SHORT).show();
                }
            });
            return copyright;

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
      recreate();

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