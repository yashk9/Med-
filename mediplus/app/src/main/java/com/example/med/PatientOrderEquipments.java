package com.example.med;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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

public class PatientOrderEquipments extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private TextView setHeader;
    private CardView cardViewMask, cardViewSanitizer, cardViewEquipments, cardViewMachine, cardViewThermometers, cardViewTestKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_patient_order_equipments);
        initalizeViews();
        //Face Mask
        cardViewMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.hospitalsstore.com/face-mask/";
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(PatientOrderEquipments.this, "Invalid Url", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Sanitizer
        cardViewSanitizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.hospitalsstore.com/hand-sanitizer-and-disinfectant/";
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(PatientOrderEquipments.this, "Invalid Url", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Equipments
        cardViewEquipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.hospitalsstore.com/personal-protective-equipment/";
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(PatientOrderEquipments.this, "Invalid Url", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //Medical Equipment
        cardViewMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.hospitalsstore.com/covid19-medical-equipment/";
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(PatientOrderEquipments.this, "Invalid Url", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Thermometers
        cardViewThermometers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.hospitalsstore.com/infrared-non-contact-thermometers/";
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(PatientOrderEquipments.this, "Invalid Url", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Test Kit
        cardViewTestKit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.hospitalsstore.com/diagnostic-test-kit/";
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(PatientOrderEquipments.this, "Invalid Url", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    void initalizeViews() {
        drawerLayout = findViewById(R.id.drawerlayouthospital);
        setHeader = findViewById(R.id.setHeader);
        setHeader.setText("Equipments");

        cardViewMask = findViewById(R.id.cardViewMask);
        cardViewSanitizer = findViewById(R.id.cardViewSanitizer);
        cardViewEquipments= findViewById(R.id.cardViewEquipments);
        cardViewMachine = findViewById(R.id.cardViewMachine);
        cardViewThermometers = findViewById(R.id.cardViewThermometers);
        cardViewTestKit = findViewById(R.id.cardViewTestKit);

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
        //redirectActivity(this, BedsAvailable.class);
        String url = "https://divcommpunecovid.com/ccsbeddashboard/hsr";
        if (url.startsWith("https://") || url.startsWith("http://")) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }else{
            Toast.makeText(PatientOrderEquipments.this, "Invalid Url", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(PatientOrderEquipments.this, "Invalid Url", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(PatientOrderEquipments.this, "THANK YOU!", Toast.LENGTH_SHORT).show();
                redirectActivity(PatientOrderEquipments.this,MainActivity.class);

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
        Intent i=new Intent(PatientOrderEquipments.this,CovidRelated.class);
        startActivity(i);
    }
}