package com.example.finalcarrental;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.squareup.picasso.Picasso;

public class cardetail_activity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView crtitle,cardesc1,carprice,carfueltype;
    ImageView carimg;
    String cartit,crppd;
    Button map,book;


    private void initilizedwidgets(){

        crtitle = (TextView)findViewById(R.id.dtl_cartitle);
        cardesc1 = (TextView)findViewById(R.id.dtl_cardesc);
        carprice = (TextView)findViewById(R.id.dtl_carprice);
        carfueltype = (TextView)findViewById(R.id.dtl_carfuletyp);
        carimg=(ImageView)findViewById(R.id.dtl_imgcardtlview);

        //TextView[] data={crtitle,carprice};




    }

    private void receivedatanadshow(){
        Intent i = this.getIntent();
        cartit=i.getExtras().getString("title");
        String cardesc=i.getExtras().getString("oveview");
        String fultp=i.getExtras().getString("fuletyp");
         crppd=i.getExtras().getString("ppd");
        String dtlimg=i.getExtras().getString("img");


        crtitle.setText(cartit);
        cardesc1.setText(cardesc);
        carfueltype.setText(fultp);
        carprice.setText(crppd);
        Picasso.get().load(dtlimg).placeholder(R.drawable.placeholder_img).into(carimg);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardetail_activity);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawar_layout);

        initilizedwidgets();
        receivedatanadshow();

        map = (Button)findViewById(R.id.googlemapbtn);
        book = (Button)findViewById(R.id.rentbtn);


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cardetail_activity.this,Mapactivity.class);
                startActivity(intent);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(cardetail_activity.this,Checkout.class);
                Bundle data1 = new Bundle();
                data1.putString("crtit", cartit);
                data1.putString("crpric",crppd);
                intent1.putExtras(data1);
                startActivity(intent1);

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