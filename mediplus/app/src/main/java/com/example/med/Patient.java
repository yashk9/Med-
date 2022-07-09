package com.example.med;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Patient extends AppCompatActivity {

    private TextInputLayout patemail,patpass;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e)
        {
        }



        patemail=findViewById(R.id.Pemail);
        patpass=findViewById(R.id.Ppass);
        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(Patient.this);

        Button buttonp1=findViewById(R.id.btnPLogin);
        Button buttonp2=findViewById(R.id.btnPregister);


        buttonp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Patient.this,patient_register.class);
                startActivity(intent);
                finish();
            }
        });



        buttonp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingBar.setTitle("Patient Login");
                mLoadingBar.setMessage("Please Wait...");
                mLoadingBar.setCanceledOnTouchOutside(false);
                mLoadingBar.show();
                isUser();

            }

            private void isUser() {
                patemail=findViewById(R.id.Pemail);
                patpass=findViewById(R.id.Ppass);
                final String userEnteredUsername = patemail.getEditText().getText().toString().trim();
                final String userEnteredPassword = patpass.getEditText().getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Patient");
                Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);



                if(!userEnteredPassword.isEmpty() && !userEnteredUsername.isEmpty()) {
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {

                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                patemail.setError(null);

                                String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                                if (Objects.equals(passwordFromDB, userEnteredPassword)) {
                                    patemail.setError(null);

                                    Intent intent = new Intent(Patient.this, PatientHOME.class);
                                    startActivity(intent);
                                    mLoadingBar.dismiss();
                                    Toast.makeText(Patient.this, "Success!", Toast.LENGTH_SHORT).show();

                                    finish();

                                } else {
                                    mLoadingBar.dismiss();
                                    patpass.setError("Wrong Password");
                                    patpass.requestFocus();


                                }
                            } else {
                                mLoadingBar.dismiss();
                                patemail.setError("No such User exist");
                                patemail.requestFocus();

                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    mLoadingBar.dismiss();
                    Toast.makeText(Patient.this, "Please Enter Your Details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}