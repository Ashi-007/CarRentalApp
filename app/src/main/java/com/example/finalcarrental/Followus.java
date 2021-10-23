package com.example.finalcarrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class Followus extends AppCompatActivity {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followus);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawar_layout);


        Element aboutpg = new Element();
        aboutpg.setTitle("Follow Us");

        View Followus = new AboutPage(this).isRTL(false)
                .setImage(R.drawable.followus)
                .setDescription("We are onw on Social Site")
                .addItem(new Element().setTitle("Click Below to Follow..."))
                .addItem(aboutpg)
                .addEmail("panktip55@gmail.com")
                .addWebsite("www.google.com")
                .addFacebook("www.facebook.com")
                .addInstagram("www.instagram.com")
                .addTwitter("www.Twitter.com")
                .addYoutube("www.youtube.com")
                .addGitHub("www.github.com")
                .setDescription("We are also on Play Store")
                .addPlayStore("www.playstore.com").create();


        setContentView(Followus);
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
    recreate();

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