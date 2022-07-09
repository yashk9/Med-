package com.example.med;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Precautions extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private TextView setHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_precautions);
        drawerLayout=findViewById(R.id.drawerLayout);

        setHeader=findViewById(R.id.setHeader);
        setHeader.setText("Precautions");
    }
    //DRAWER LOGIC
    public void ClickMenu(View view) {
        //open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        //close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //when drawer is open, close it
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickDashboard(View view) {
        //Redirect to dashboard
        redirectActivity(this,PatientHOME.class);
    }

    public void ClickHospitals(View view) {
        //redirect to Hospitals
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?z=21&q=hospitals"));
        Intent chooser = Intent.createChooser(intent, "Launch Maps");
        if (chooser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(getApplicationContext(), "No Application Found", Toast.LENGTH_SHORT).show();
        }
    }

    public void ClickBedsAvailable(View view) {
        //redirect activity to beds available
       // redirectActivity(this, BedsAvailable.class);
        String url = "https://divcommpunecovid.com/ccsbeddashboard/hsr";
        if (url.startsWith("https://") || url.startsWith("http://")) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else{
            Toast.makeText(Precautions.this, "Invalid Url", Toast.LENGTH_SHORT).show();
        }
    }

    public void ClickPlasmaAvailable(View view) {
        //redirect activity to plasma available
        //redirectActivity(this, PlasmaAvailable.class);
        String url = "http://friends2support.org/inner/news/searchresult.aspx";
        if (url.startsWith("https://") || url.startsWith("http://")) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else{
            Toast.makeText(Precautions.this, "Invalid Url", Toast.LENGTH_SHORT).show();
        }
    }


    public void ClickTestingCenters(View view) {
        //redirect activity to testing centers
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=testing center"));
        Intent chooser = Intent.createChooser(intent, "Launch Maps");
        if (chooser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(getApplicationContext(), "No Application Found", Toast.LENGTH_SHORT).show();
        }
    }

    public void ClickOnlineConsultation(View view) {
        //redirect activity to online consultation
        redirectActivity(this, OnlineConsulting.class);
    }

    public void ClickNearbyMedicals(View view) {
        //redirect activity to Medicals
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=medical"));
        Intent chooser = Intent.createChooser(intent, "Launch Maps");
        if (chooser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(getApplicationContext(), "No Application Found", Toast.LENGTH_SHORT).show();
        }
    }

    public void ClickLogout(View view) {
        //redirect activity to logout
        logout(this);
    }

    public void logout(Activity activity) {
        //alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        //set message
        builder.setMessage("Are you sure you want to logout?");
        //YES
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //Exit app
                /*System.exit(0);*/
                Toast.makeText(Precautions.this, "THANK YOU!", Toast.LENGTH_SHORT).show();
                redirectActivity(Precautions.this,MainActivity.class);

            }
        });
        //NO
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dissmiss
                dialog.dismiss();
            }
        });
        //show dialog
        builder.show();
    }


    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(Precautions.this,PatientHOME.class);
        startActivity(i);
    }
}