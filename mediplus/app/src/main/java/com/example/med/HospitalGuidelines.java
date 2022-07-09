package com.example.med;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class HospitalGuidelines extends AppCompatActivity {

    private DrawerLayout drawerlayouthospital;
    private TextView setHeader;
    private TextView txtHospitalRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_hospital_guidelines);

        drawerlayouthospital = findViewById(R.id.drawerlayouthospital);
        setHeader = findViewById(R.id.setHeader);
        setHeader.setText("Guidelines");

        txtHospitalRules = findViewById(R.id.txtHospitalRules);
        String s = "VISITING HOURS - In the Wards between 5:00 – 7:00 pm (summer) and 4:00 – 6:00 pm (winter) daily. In ICU, Morning visiting hours will be from 10:30 – 11:00 am and Evening from 5:00 – 5:30 pm.\n" +
                "\nSERVICES – Services of Medical, Nursing, Housekeeping and Maintenance staffs are available round the clock.\n" +
                "\nADMISSION ADVANCE – Admission advance must be paid at the time of admission to cover the expenses for surgery / treatment. Personal cheques are not accepted.\n" +
                "\nATTENDANTS – Only one attendant is allowed with all categories of patients.\n" +
                "\nOT/HDU/ICU TRANSFER – The attendants have to vacate the room whenever the patient is shifted to any critical care area or for a surgery / procedure.\n" +
                "\nDISCHARGE – Check out time is 12:00 Noon. Patients discharged between 12 Noon to 6 PM will be charged ½ day’s room rent. Please clear your bills before 12 noon and 6 PM.\n" +
                "\nFOOD AND BEVERAGES - No Outside food or beverages are allowed in the hospital premises. \n" +
                "\nTOBACCO - All Tobacco products are strictly prohibited (Smoking, Chewing, Pan / Pan Masala) in the Hospital premises.";

        txtHospitalRules.setText(s);

        YouTubePlayerView youTubePlayerView1 = findViewById(R.id.youtube_player_view1);
        getLifecycle().addObserver(youTubePlayerView1);

        YouTubePlayerView youTubePlayerView2 = findViewById(R.id.youtube_player_view2);
        getLifecycle().addObserver(youTubePlayerView2);
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
                Toast.makeText(HospitalGuidelines.this, "THANK YOU!", Toast.LENGTH_SHORT).show();
                redirectActivity(HospitalGuidelines.this,MainActivity.class);

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