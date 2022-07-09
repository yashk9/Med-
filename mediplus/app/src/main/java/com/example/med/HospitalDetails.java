package com.example.med;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class HospitalDetails extends AppCompatActivity {

    private DrawerLayout drawerlayouthospital;
    private TextView setHeader;
    private TextInputLayout hadd,hphone,hweb,hemail,hdoc1,hdoc2,hdoc3,hemerg1,hemerg2;
    TextInputLayout hname;

    Button btnSaveDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_hospital_details);



        hname= findViewById(R.id.regname);
        hadd= findViewById(R.id.regaddress);
        hphone= findViewById(R.id.regphone);
        hweb = findViewById(R.id.regwebsite);
        hemail= findViewById(R.id.regemail);

        hdoc1= findViewById(R.id.regdoctor1);
        hdoc2= findViewById(R.id.regdoctor2);
        hdoc3= findViewById(R.id.regdoctor3);

        hemerg1= findViewById(R.id.regemerg1);
        hemerg2= findViewById(R.id.regemerg2);

        drawerlayouthospital = findViewById(R.id.drawerlayouthospital);
        setHeader = findViewById(R.id.setHeader);
        setHeader.setText("Hospital Details");

        btnSaveDetails=findViewById(R.id.btnSaveDetails);

        String hos_name= getIntent().getStringExtra("hos_name");
        String hos_phone= getIntent().getStringExtra("hos_phone");

        //hname.getEditText().setText(hos_name);
        //hphone.getEditText().setText(hos_phone);



        showAllUserData();






        String username= getIntent().getStringExtra("key");
        DatabaseReference HospitalDetailref = FirebaseDatabase.getInstance().getReference().child("Hospitals").child(username);




        btnSaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hosrname = hname.getEditText().getText().toString();
                String hosradd = hadd.getEditText().getText().toString();
                String hosrphone = hphone.getEditText().getText().toString();
                String hosrweb = hweb.getEditText().getText().toString();
                String hosremail = hemail.getEditText().getText().toString();

                String hd1 = hdoc1.getEditText().getText().toString();
                String hd2 = hdoc2.getEditText().getText().toString();
                String hd3 = hdoc3.getEditText().getText().toString();

                String he1 = hemerg1.getEditText().getText().toString();
                String he2 = hemerg2.getEditText().getText().toString();

                String username= getIntent().getStringExtra("key");

                HashMap hashMap=new HashMap();
                hashMap.put("Name",hosrname);
                hashMap.put("Address",hosradd);
                hashMap.put("Phone",hosrphone);
                hashMap.put("Website",hosrweb);
                hashMap.put("Email",hosremail);

                hashMap.put("Doc 1",hd1);
                hashMap.put("Doc 2",hd2);
                hashMap.put("Doc 3",hd3);

                hashMap.put("Emergency 1",he1);
                hashMap.put("Emergency 2",he2);



                //UserHelperClassHDetails helperClass1 = new UserHelperClassHDetails(hosrname, hosradd, hosrphone, hosrweb, hosremail, hd1,hd2,hd3,he1,he2);

                //FirebaseDatabase HospitalDetaildb= FirebaseDatabase.getInstance();


                HospitalDetailref.child("Current Details").updateChildren(hashMap);


               // HospitalDetailref.child(hosrname).setValue(helperClass1);

                Toast.makeText(getApplicationContext(),"Your Details Have Been Successfully Updated!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(HospitalDetails.this,HospitalHOME.class);

                startActivity(intent);
            }
        });
    }





     private void showAllUserData() {

         String username= getIntent().getStringExtra("key");
         DatabaseReference HospitalDetailref = FirebaseDatabase.getInstance().getReference().child("Hospitals").child(username);

        /* String hosrname = hname.getEditText().getText().toString();
         String hosradd = hadd.getEditText().getText().toString();
         String hosrphone = hphone.getEditText().getText().toString();
         String hosrweb = hweb.getEditText().getText().toString();
         String hosremail = hemail.getEditText().getText().toString();

         String hd1 = hdoc1.getEditText().getText().toString();
         String hd2 = hdoc2.getEditText().getText().toString();
         String hd3 = hdoc3.getEditText().getText().toString();

         String he1 = hemerg1.getEditText().getText().toString();
         String he2 = hemerg2.getEditText().getText().toString();

         */

        HospitalDetailref.child("Current Details").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    Map<String,Object> map=(Map<String,Object>) snapshot.getValue();

                    String name = (String) map.get("Name");
                    hname.getEditText().setText(name);
                    String add = (String) map.get("Address");
                    hadd.getEditText().setText(add);
                    String phone = (String) map.get("Phone");
                    hphone.getEditText().setText(phone);
                    String website = (String) map.get("Website");
                    hweb.getEditText().setText(website);
                    String email = (String) map.get("Email");
                    hemail.getEditText().setText(email);

                    String doc1 = (String) map.get("Doc 1");
                    hdoc1.getEditText().setText(doc1);
                    String doc2 = (String) map.get("Doc 2");
                    hdoc2.getEditText().setText(doc2);
                    String doc3 = (String) map.get("Doc 3");
                    hdoc3.getEditText().setText(doc3);

                    String emerg1 = (String) map.get("Emergency 1");
                    hemerg1.getEditText().setText(emerg1);
                    String emerg2 = (String) map.get("Emergency 2");
                    hemerg2.getEditText().setText(emerg2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
        //Redirect activity to dashboard
        redirectActivity(this,HospitalHOME.class);
    }

    public void ClickEditDetails(View view) {
        //Re create activity
        recreate();
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
                Toast.makeText(HospitalDetails.this, "THANK YOU!", Toast.LENGTH_SHORT).show();
                redirectActivity(HospitalDetails.this,MainActivity.class);

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