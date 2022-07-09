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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HospitalHOME extends AppCompatActivity {

   /* private FirebaseAuth mAuth;
    private Button btnout;*/
    private DrawerLayout drawerlayouthospital;
    private TextView setHeader;
    CardView cardViewHospital, cardViewPlasma, cardViewCovidTesting, cardViewAvailabilityOfBeds, cardViewAvailabilityOfMedicines, cardViewHospitalRules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }


        setContentView(R.layout.activity_hospital_h_o_m_e);

        initalizeViews();

        /*btnout=findViewById(R.id.out);

        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(HospitalHOME.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(HospitalHOME.this, "THANK YOU!", Toast.LENGTH_SHORT).show();
            }
        });*/

        //Hospital Details
        cardViewHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HospitalHOME.this, HospitalDetails.class);
                String temp= getIntent().getStringExtra("keyusername");
                String hos_name= getIntent().getStringExtra("hos_name");
                String hos_phone= getIntent().getStringExtra("hos_phone");
                intent.putExtra("key",temp);
                intent.putExtra("hos_name",hos_name);
                intent.putExtra("hos_phone",hos_phone);
                startActivity(intent);

            }
        });

        //Blood Availbility
        cardViewCovidTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?z=21&q=blood bank"));
                Intent chooser = Intent.createChooser(intent, "Launch Maps");
                if (chooser.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                } else {
                    Toast.makeText(getApplicationContext(), "No Application Found", Toast.LENGTH_SHORT).show();
                }
                /*Intent intent = new Intent( HospitalHOME.this, HospitalCovidTesting.class);
                String temp= getIntent().getStringExtra("keyusername");
                intent.putExtra("key",temp);

                startActivity(intent);*/

            }
        });

        //Order Equipments
        cardViewAvailabilityOfBeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HospitalHOME.this, OrderEquipments.class);
                startActivity(intent);
            }
        });


        //Order Medicines
        cardViewAvailabilityOfMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://pharmarack.com/";
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(HospitalHOME.this, "Invalid Url", Toast.LENGTH_SHORT).show();
                }

                /*Intent intent = new Intent( HospitalHOME.this, HospitalAccessibleDrugs.class);
                String temp= getIntent().getStringExtra("keyusername");
                intent.putExtra("key",temp);
                startActivity(intent);*/

            }
        });

        //Ambulance details
        cardViewPlasma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HospitalHOME.this, AmbulanceDetails.class);
                /*String temp= getIntent().getStringExtra("keyusername");
                intent.putExtra("key",temp);*/
                startActivity(intent);

            }
        });

        //Hospital Guidelines
        cardViewHospitalRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HospitalHOME.this, HospitalGuidelines.class);
                startActivity(intent);

            }
        });
    }
    void initalizeViews() {
        drawerlayouthospital = findViewById(R.id.drawerlayouthospital);
        setHeader = findViewById(R.id.setHeader);
        setHeader.setText("DashBoard");

        cardViewHospital = findViewById(R.id.cardViewHospital);
        cardViewPlasma = findViewById(R.id.cardViewPlasma);
        cardViewCovidTesting= findViewById(R.id.cardViewCovidTesting);
        cardViewAvailabilityOfBeds = findViewById(R.id.cardViewAvailabilityOfBeds);
        cardViewAvailabilityOfMedicines = findViewById(R.id.cardViewAvailabilityOfMedicines);
        cardViewHospitalRules = findViewById(R.id.cardViewHospitalRules);

    }

    //DRAWER LOGIC
    public void ClickMenu(View view) {
        //open drawer
        openDrawer(drawerlayouthospital);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        //close drawer
        closeDrawer(drawerlayouthospital);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //when drawer is open, close it
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickDashboard(View view) {
        //Re create activity
        recreate();
    }

    public void ClickEditDetails(View view) {
        //redirect activity to edit details
        redirectActivity(this, HospitalDetails.class);
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
                Toast.makeText(HospitalHOME.this, "THANK YOU!", Toast.LENGTH_SHORT).show();
                redirectActivity(HospitalHOME.this,MainActivity.class);

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
        closeDrawer(drawerlayouthospital);
    }
}