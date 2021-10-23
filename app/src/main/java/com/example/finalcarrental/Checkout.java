package com.example.finalcarrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Checkout extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView ckcarnm,ckprice,ckcgst,cksgst,cktot;
    Button paybtn;
    String datanm,dataprc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawar_layout);

        ckcarnm = (TextView)findViewById(R.id.ckcrnm);
        ckprice = (TextView)findViewById(R.id.ckprice);
        ckcgst = (TextView)findViewById(R.id.ckcgst);
        cksgst = (TextView)findViewById(R.id.cksgst);
        cktot=(TextView) findViewById(R.id.cktotal);
        paybtn = (Button)findViewById(R.id.ckbtn);

        Intent intentReceived = getIntent();
        Bundle data = intentReceived.getExtras();
        //datanm = data.getString("crtit");
        //dataprc = data.getString("crpric");
        Intent i = this.getIntent();
        String cartit=i.getExtras().getString("crtit");
        String crppd=i.getExtras().getString("crpric");


        ckcarnm.setText(cartit);
        ckprice.setText(crppd);

       float cgst = Float.parseFloat(crppd.toString());
       float sgst = Float.parseFloat(crppd.toString());

       float ctotal = (cgst/100)*14;
       String ctl= Float.toString(ctotal);
       float stotal = (sgst/100)*14;
        String stl= Float.toString(stotal);

        float carprice = Float.parseFloat(crppd);

       float total = ctotal + stotal + carprice;
       String tl = Float.toString(total);

       ckcgst.setText(ctl);
       cksgst.setText(stl);
        cktot.setText(tl);

        // receivedatanadshow();

//        Intent i = this.getIntent();
//        String cartit=i.getExtras().getString("crtitle");
//        String crppd=i.getExtras().getString("crppd");
//
//        ckcarnm.setText(cartit);
//        ckprice.setText(crppd);

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Checkout.this,paytm.class);
                startActivity(i);
            }
        });



    }

   // private static Uri getpaytmuri(String)
//    private void initilizedwidgets(){
//
//        crtitle = (TextView)findViewById(R.id.dtl_cartitle);
//        cardesc1 = (TextView)findViewById(R.id.dtl_cardesc);
//        carprice = (TextView)findViewById(R.id.dtl_carprice);
//        carfueltype = (TextView)findViewById(R.id.dtl_carfuletyp);
//        carimg=(ImageView)findViewById(R.id.dtl_imgcardtlview);
//
//        //TextView[] data={crtitle,carprice};
//
//
//
//
//    }

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
        Homeact.redirectActivity(this,Followus.class);

    }

    public void ClickLogout(View view){
        //back to login Screen

        Homeact.logout(this);


    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        //Close Drawer
//        Homeact.closeDrawer(drawerLayout);
//    }
}