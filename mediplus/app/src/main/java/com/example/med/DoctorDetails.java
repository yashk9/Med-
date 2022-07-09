package com.example.med;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DoctorDetails extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private TextView setHeader;
    private TextView txtNameDoc, txtProfessionDoc, txtStudyDoc, txtExperienceDoc, txtInquiryNumber;
    private ImageView imgDoc;
    private Button btnProceed, btnInquiry;

    public String upiName;

    TextView confirmDate,confirmTime;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_doctor_details);

        confirmDate=findViewById(R.id.confirmDate);
        confirmTime=findViewById(R.id.confirmTime);

        drawerLayout=findViewById(R.id.drawerLayout);

        setHeader=findViewById(R.id.setHeader);
        setHeader.setText("Confirm Booking");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        txtNameDoc = findViewById(R.id.txtDoctorName);
        txtNameDoc.setText(name);

        String profession = i.getStringExtra("prof");
        txtProfessionDoc = findViewById(R.id.txtDoctorProfession);
        txtProfessionDoc.setText(profession);

        String study = i.getStringExtra("study");
        txtStudyDoc = findViewById(R.id.txtDoctorStudy);
        txtStudyDoc.setText(study);

        String experience = i.getStringExtra("exp");
        txtExperienceDoc = findViewById(R.id.txtDoctorExperience);
        txtExperienceDoc.setText(experience);

        Integer img = i.getIntExtra("image", 0);
        imgDoc = findViewById(R.id.imgDoctor);
        imgDoc.setImageResource(img);


        //calender logic
        Calendar calender= Calendar.getInstance();
        final int year=calender.get(Calendar.YEAR);
        final int month=calender.get(Calendar.MONTH);
        final int day=calender.get(Calendar.DAY_OF_MONTH);

        confirmDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize the date picker dialog
                DatePickerDialog datePickerDialog =new DatePickerDialog(DoctorDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        //store the date in string
                        String date=day+"/"+month+"/"+year;
                        //set the date in text view
                        confirmDate.setText(date);

                    }
                },year,month,day);
                //to disable past date feature
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                //show the dialog
                datePickerDialog.show();
            }
        });


        //time logic
        confirmTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize the time picker dialog
                TimePickerDialog timePickerDialog=new TimePickerDialog(DoctorDetails.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
                        //initialize hour and minute
                        hour=hourOfDay;
                        minute=minuteOfDay;

                        //initialize calender
                        Calendar calendar=Calendar.getInstance();

                        //Set hour and minute
                        calendar.set(0,0,0,hour,minute);

                        //set selected time on text view
                        confirmTime.setText(DateFormat.format("hh:mm aa",calendar));
                    }
                }, 12,0,false);
                //display the previous selected time
                timePickerDialog.updateTime(hour,minute);
                //show the dialog
                timePickerDialog.show();
            }
        });


        //for Inquiry
        txtInquiryNumber = findViewById(R.id.txtInquiryNumber);
        btnInquiry = findViewById(R.id.btnInquiry);
        btnInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = txtInquiryNumber.getText().toString();
                inquiry(phoneNumber);
            }
        });


        //for UPI
        btnProceed = findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDetails.this, UpiPaymentGateway.class);
                startActivity(intent);
            }
        });
    }

    private void inquiry(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);

        if (phoneNumber.isEmpty()) {

            Toast.makeText(getApplicationContext(), "No number provided", Toast.LENGTH_SHORT).show();
        } else {
            String s = "tel:" + phoneNumber;
            intent.setData(Uri.parse(s));
        }

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(DoctorDetails.this, "Please Grant Permission", Toast.LENGTH_SHORT).show();
            requestPermission();
        } else {
            startActivity(intent);
        }
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(DoctorDetails.this, new String[]{Manifest.permission.CALL_PHONE}, 1);

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
            Toast.makeText(DoctorDetails.this, "Invalid Url", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(DoctorDetails.this, "Invalid Url", Toast.LENGTH_SHORT).show();
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
        redirectActivity(this,OnlineConsulting.class);
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
                Toast.makeText(DoctorDetails.this, "THANK YOU!", Toast.LENGTH_SHORT).show();
                redirectActivity(DoctorDetails.this,MainActivity.class);

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
        Intent i=new Intent(DoctorDetails.this,OnlineConsulting.class);
        startActivity(i);
    }
}