package com.example.med;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OnlineConsulting extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    private DrawerLayout drawerLayout;
    private TextView setHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_online_consulting);
        drawerLayout=findViewById(R.id.drawerLayout);

        setHeader=findViewById(R.id.setHeader);
        setHeader.setText("Online Consultation");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyDoctor[] myDoctors = new MyDoctor[]{
                new MyDoctor("Dr. Mukesh Budhwani", "Physician", "MD - Physician", "18 Years Experience", R.drawable.d1),
                new MyDoctor("Dr. Pranami Mehta", "Pediatrician", "MBBS, Diploma in Child Health (DCH)", "14 Years Experience", R.drawable.d2),
                new MyDoctor("Dr. Ajay Kothadiya", "ENT Specialist", "MBBS, Diploma in Otorhinolaryngology (DLO)", "21 Years Experience", R.drawable.d3),
                new MyDoctor("Dr. Sanjay Jain", "Dentist", "BDS, MDS - Periodontology and Oral Implantology", "19 Years Experience", R.drawable.d4),
                new MyDoctor("Dr. Sneha A. Deshpande", "Gynecologist||Obstetrician", "MBBS, DNB - Obstetrics & Gynecology, DGO", "13 Years Experience", R.drawable.d5),
                new MyDoctor("Dr. Anirudha Deshpande", "Orthopedist", "MBBS, DNB - Orthopedics/Orthopedic Surgery", "16 Years Experience", R.drawable.d6),
                new MyDoctor("Dr. Akanksha Barthwal Thakre", "Dermatologist", "MBBS, Diploma in Dermatology", "8 Years Experience", R.drawable.d7),
                new MyDoctor("Dr. Shilpa Patil", "Eye Specialist", "MBBS, DOMS, DNB - Ophthalmology, FCPS- Ophthalmology", "14 Years Experience", R.drawable.d8),
                new MyDoctor("Dr. Bilkish Raje", "Dietitian||Nutritionist", "MSc - Dietitics / Nutrition", "16 Years Experience", R.drawable.d9),
                new MyDoctor("Dr. Deepa Karwa", "Ayurveda", "BAMS, Post Graduate Diploma In Yoga", "12 Years Experience", R.drawable.d10),
                new MyDoctor("Dr. Mahima Soni", "Homoeopath", "BHMS", "14 Years Experience", R.drawable.d11),
                new MyDoctor("Dr. Revathi Adhalakha", "Physiotherapist", "BPTh/BPT", "7 Years Experience", R.drawable.d12)

        };
        adapter = new Adapter(this, myDoctors); //,img
        recyclerView.setAdapter(adapter);
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
            Toast.makeText(OnlineConsulting.this, "Invalid Url", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(OnlineConsulting.this, "Invalid Url", Toast.LENGTH_SHORT).show();
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
        //recreate online consultation
        recreate();
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
                Toast.makeText(OnlineConsulting.this, "THANK YOU!", Toast.LENGTH_SHORT).show();
                redirectActivity(OnlineConsulting.this,MainActivity.class);

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
        Intent i=new Intent(OnlineConsulting.this,PatientHOME.class);
        startActivity(i);
    }
}