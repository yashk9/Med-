package com.example.med;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class patient_register extends AppCompatActivity {



    TextInputLayout rpname, rpusername, rpemail, rpphone, rppassword;
    Button rpregister,rplogin;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);


        try
        {
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e)
        {
        }



        rpname= findViewById(R.id.patx_name);
        rpemail= findViewById(R.id.patx_email);
        rpphone= findViewById(R.id.patx_phone);
        rppassword= findViewById(R.id.patx_password);
        rpregister= findViewById(R.id.patx_register);
        rplogin=findViewById(R.id.patx_login);

        mLoadingBar=new ProgressDialog(patient_register.this);




        rplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(patient_register.this, Patient.class);
                startActivity(intent);
                finish();

            }
        });


        rpregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patname = rpname.getEditText().getText().toString();
                String patusername = rpemail.getEditText().getText().toString();
                String patphone = rpphone.getEditText().getText().toString();
                String patpassword = rppassword.getEditText().getText().toString();

                 if (!patname.isEmpty() && !patusername.isEmpty() && !patphone.isEmpty() && !patpassword.isEmpty()) {

                    FirebaseDatabase Pdb= FirebaseDatabase.getInstance();
                    DatabaseReference PatientDB=Pdb.getReference().child("Patient");

                    mLoadingBar.setTitle("Registration");
                    mLoadingBar.setMessage("Please Wait...");
                    mLoadingBar.setCanceledOnTouchOutside(false);
                    mLoadingBar.show();

                    UserHelperClassP helperClass2 = new UserHelperClassP(patname, patusername, patpassword, patphone);
                    PatientDB.child(patusername).setValue(helperClass2);

                    mLoadingBar.dismiss();

                    Intent intentp = new Intent(patient_register.this, PatientHOME.class);
                    startActivity(intentp);
                    Toast toast = Toast.makeText(patient_register.this, "Success", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();



                } else {
                    Toast toast=Toast.makeText(patient_register.this, "Please Check Your Details", Toast.LENGTH_SHORT);
                    toast.show();

                }
            }
        });





    }
}