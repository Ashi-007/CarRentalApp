package com.example.finalcarrental;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class paytm extends AppCompatActivity {


    public static final String PAYTM_PACKAGE_NM = "net.one97.paytm";
    EditText pyname,pyupi,pynote,pyamt;
    TextView msg;
    Button paynow;

    public static String payername,vpa,msgnot,status,sendamount;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm);

        pyname = (EditText)findViewById(R.id.payname);
        pyupi = (EditText)findViewById(R.id.payuid);
        pynote = (EditText)findViewById(R.id.paytid);
        pyamt = (EditText)findViewById(R.id.payamt);
        msg = (TextView)findViewById(R.id.msg);
        paynow = (Button) findViewById(R.id.paybtn);



        //setting onclick event;

        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the text data;

                payername = pyname.getText().toString();
                vpa = pyupi.getText().toString();
                msgnot = pynote.getText().toString();
                sendamount = pyamt.getText().toString();


                if(payername!= null && vpa != null && msgnot!= null && sendamount!= null){

                    uri = getPayTmUri(payername,vpa,msgnot,sendamount);
                    payWithPayTm(PAYTM_PACKAGE_NM);

                }
                else {
                    Toast.makeText(paytm.this,"Please Fill all the details.",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    private static Uri getPayTmUri(String name,String upi,String note,String amount){
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa",upi)
                .appendQueryParameter("pn",name)
                .appendQueryParameter("tn",note)
                .appendQueryParameter("am",amount)
                .appendQueryParameter("cu","INR").build();
    }

    private void payWithPayTm(String packageName){

        if (isAppInstalled(this,packageName)){

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setPackage(packageName);
            startActivityForResult(i,0);
        }else {
            Toast.makeText(paytm.this,"PayTm is not Installed Please try Again.",Toast.LENGTH_SHORT).show();
        }


    }

    //checking app is install or not;
    public static boolean isAppInstalled(Context context,String packagename){
        try{
            context.getPackageManager().getApplicationInfo(packagename,0);
            return true;
        }catch (PackageManager.NameNotFoundException e){
            return false;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            status=data.getStringExtra("status").toLowerCase();
        }
        if (RESULT_OK == resultCode && status.equals("success")){

            Toast.makeText(this,"Transaction Successfull"+data.getStringExtra("ApprovalRefNo"),Toast.LENGTH_SHORT).show();
            msg.setText("Transaction Successful of INR"+sendamount);
            msg.setTextColor(Color.GREEN);
        }else {
            Toast.makeText(this, "Transaction Failed!", Toast.LENGTH_SHORT).show();
            msg.setText("Teansaction Failed of INR"+sendamount);
            msg.setTextColor(Color.RED);

        }

    }
}