package com.example.med;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AmbulanceDetails extends AppCompatActivity {

    private TextView EditTxtNumber1, EditTxtNumber2, EditTxtNumber3;
    private Button btnCallNow1, btnCallNow2, btnCallNow3;
    private DrawerLayout drawerLayout;
    private TextView setHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }


        setContentView(R.layout.activity_ambulance_details);
        initalizeViews();

        btnCallNow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = EditTxtNumber1.getText().toString();
                CallButton(phoneNumber);

            }
        });

        btnCallNow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = EditTxtNumber2.getText().toString();
                CallButton(phoneNumber);

            }
        });

        btnCallNow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = EditTxtNumber3.getText().toString();
                CallButton(phoneNumber);

            }
        });

    }
    void initalizeViews() {
        EditTxtNumber1 = findViewById(R.id.EditTxtNumber1);
        EditTxtNumber2 = findViewById(R.id.EditTxtNumber2);
        EditTxtNumber3 = findViewById(R.id.EditTxtNumber3);

        btnCallNow1 = findViewById(R.id.btnCallNow1);
        btnCallNow2 = findViewById(R.id.btnCallNow2);
        btnCallNow3 = findViewById(R.id.btnCallNow3);

        drawerLayout=findViewById(R.id.drawerLayout);

        setHeader=findViewById(R.id.setHeader);
        setHeader.setText("Ambulance Details");
    }

    //CALLING LOGIC
    private void CallButton(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);

        if (phoneNumber.isEmpty()) {

            Toast.makeText(getApplicationContext(), "No number provided", Toast.LENGTH_SHORT).show();
        } else {
            String s = "tel:" + phoneNumber;
            intent.setData(Uri.parse(s));
        }

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(AmbulanceDetails.this, "Please Grant Permission", Toast.LENGTH_SHORT).show();
            requestPermission();
        } else {
            startActivity(intent);
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(AmbulanceDetails.this, new String[]{Manifest.permission.CALL_PHONE}, 1);

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
        //Redirect activity to dashboard
        redirectActivity(this,HospitalHOME.class);
    }

    public void ClickEditDetails(View view) {
        //Redirect to Details page
        redirectActivity(this,HospitalDetails.class);
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
                Toast.makeText(AmbulanceDetails.this, "THANK YOU!", Toast.LENGTH_SHORT).show();
                redirectActivity(AmbulanceDetails.this,MainActivity.class);

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
        Intent i=new Intent(AmbulanceDetails.this,HospitalHOME.class);
        startActivity(i);
    }
}