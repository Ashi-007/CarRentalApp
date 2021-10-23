package com.example.finalcarrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Homeact extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private static final String BASE_URL= "http://192.168.43.112";
    private static final String FULL_URL= BASE_URL+"/tempdata/";

    class Cardtlphp{

        @SerializedName("id")
        private int id;

        @SerializedName("VehiclesTitle")
        private String VehiclesTitle;

        @SerializedName("VehiclesOverview")
        private String VehiclesOverview;

        @SerializedName("PricePerDay")
        private String PricePerDay;

        @SerializedName("FuelType")
        private String FuelType;


        @SerializedName("Vimage1")
        private String Vimage1;


        public Cardtlphp(int id, String vehiclesTitle, String vehiclesOverview, String pricePerDay, String fuelType, String vimage1) {
            this.id = id;
            this.VehiclesTitle = vehiclesTitle;
            this.VehiclesOverview = vehiclesOverview;
            this.PricePerDay = pricePerDay;
            this.FuelType = fuelType;
            this.Vimage1 = vimage1;
        }


        public int getId() {
            return id;
        }

        public String getVehiclesTitle() {
            return VehiclesTitle;
        }

        public String getVehiclesOverview() {
            return VehiclesOverview;
        }

        public String getPricePerDay() {
            return PricePerDay;
        }

        public String getFuelType() {
            return FuelType;
        }

        public String getVimage1() {
            return Vimage1;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setVehiclesTitle(String vehiclesTitle) {
            VehiclesTitle = vehiclesTitle;
        }

        public void setVehiclesOverview(String vehiclesOverview) {
            VehiclesOverview = vehiclesOverview;
        }

        public void setPricePerDay(String pricePerDay) {
            PricePerDay = pricePerDay;
        }

        public void setFuelType(String fuelType) {
            FuelType = fuelType;
        }

        public void setVimage1(String vimage1) {
            Vimage1 = vimage1;
        }
    }

    interface mycardtlapi{

        @GET("/car%20rental%20app/script/gethomepagedta.php")
        Call<List<Cardtlphp>> getcardtl();

    }

    static class RetrofitClientInstance{
        private static Retrofit retrofit;

        public static Retrofit getRetrofitInstance(){

            if (retrofit == null){
                retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

            }

            return retrofit;
        }


    }

    class ListviewAdapter extends BaseAdapter {

        private List<Cardtlphp> cardtlphps;
        private Context context;

        public ListviewAdapter(List<Cardtlphp> cardtlphps, Context context) {
            this.cardtlphps = cardtlphps;
            this.context = context;
        }


        @Override
        public int getCount() {
            return cardtlphps.size();
        }

        @Override
        public Object getItem(int pos) {
            return cardtlphps.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null){

                view= LayoutInflater.from(context).inflate(R.layout.model,viewGroup,false);

            }
            TextView car_nm = view.findViewById(R.id.cartitle);
            TextView car_pric = view.findViewById(R.id.carprice);
            TextView fueltyp = view.findViewById(R.id.fuletyp);
            ImageView hmimg = view.findViewById(R.id.carimg);


            final Cardtlphp thiscardtl = cardtlphps.get(position);

            car_nm.setText(thiscardtl.getVehiclesTitle());
            car_pric.setText(String.valueOf(thiscardtl.getPricePerDay()));
            fueltyp.setText(thiscardtl.getFuelType());

            if(thiscardtl.getVimage1() != null && thiscardtl.getVimage1().length()>0){

                Picasso.get().load(FULL_URL+"/image/"+thiscardtl.getVimage1()).placeholder(R.drawable.placeholder_img).into(hmimg);


            }else {

                Toast.makeText(context,"Empty Image Path",Toast.LENGTH_LONG).show();
                Picasso.get().load(R.drawable.placeholder_img).into(hmimg);
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,thiscardtl.getVehiclesTitle(),Toast.LENGTH_LONG).show();

                    String[] caralldtl = {
                            thiscardtl.getVehiclesTitle(),
                            thiscardtl.getVehiclesOverview(),
                            thiscardtl.getFuelType(),
                            thiscardtl.getPricePerDay(),
                            FULL_URL+"/image/"+thiscardtl.getVimage1()

                    };
                    opendetailactivity(caralldtl);
                }
            });


            return view;
        }
        public void opendetailactivity(String[] data){

            Intent intent = new Intent(Homeact.this,cardetail_activity.class);
            intent.putExtra("title",data[0]);
            intent.putExtra("oveview",data[1]);
            intent.putExtra("fuletyp",data[2]);
            intent.putExtra("ppd",data[3]);
            intent.putExtra("img",data[4]);
            startActivity(intent);
        }
    }


    private ListviewAdapter adapter;
    private ListView mListview;
    ProgressBar progressBar;


    private void populateListview(List<Cardtlphp> cardtlphpslst){

        mListview=findViewById(R.id.listview);
        adapter = new ListviewAdapter(cardtlphpslst, this);
        mListview.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeact);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawar_layout);


        //navigationView = (NavigationView) drawer.findViewById(R.id.nav_view);

        final ProgressBar progressBar = findViewById(R.id.progrsbar);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        mycardtlapi carapimy = RetrofitClientInstance.getRetrofitInstance().create(mycardtlapi.class);

        Call<List<Cardtlphp>> call = carapimy.getcardtl();
        call.enqueue(new Callback<List<Cardtlphp>>() {
            @Override
            public void onResponse(Call<List<Cardtlphp>> call, Response<List<Cardtlphp>> response) {
                progressBar.setVisibility(View.GONE);
                populateListview(response.body());


            }

            @Override
            public void onFailure(Call<List<Cardtlphp>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(Homeact.this,t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });


    }

    public void ClickMenu(View view){
//open Drawer
        openDrawer(drawerLayout);

    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //open Drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);
    }


    public void ClickLogo(View view){
        //Close Drawer
        closeDrawer(drawerLayout);

    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close Drawer Layout
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);

        }

    }

    public void ClickHome(View view){
        //recreate activity
        recreate();

    }

    public void ClickProfile(View view){
        //Redirect activity to Profile
//        getIntent().getStringExtra("prof_Eamil");
        redirectActivity(this,Profile.class);


    }


    public void ClickContactus(View view){
        //Redirect activity to Contact Us
        redirectActivity(this,ContactUs.class);

    }


    public void ClickAboutus(View view){
        //Redirect activity to About Us
        redirectActivity(this,AboutUs.class);

    }

    public void ClickVehicle(View view){
        //Redirect activity to Vehicle
        redirectActivity(this,Vehicle.class);

    }

    public void ClickShare(View view){
        //Redirect activity to Share
        redirectActivity(this,Share.class);

    }

    public void ClickFollowus(View view){
        //Redirect activity to Follow US
        redirectActivity(this,Followus.class);

    }

    public void ClickLogout(View view){
        //back to login Screen

        logout(this);


    }

    public static void logout(Activity activity) {
        //Initlize Alert Dialog Box
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set title
        builder.setTitle("Logout");
        //set Message
        builder.setMessage("Are you sure you want to Logout...?");
        //positive yes Button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Finesh Actitvity
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //Navigate No Button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Dismiss dialog box
                dialogInterface.dismiss();
            }
        });
        //Show Dialog
        builder.show();
    }


    public static void redirectActivity(Activity activity, Class aclass) {

        //Initilize Intent
        Intent intent = new Intent(activity,aclass);
        //set Flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start Activity
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close Drawer
        closeDrawer(drawerLayout);
    }
}