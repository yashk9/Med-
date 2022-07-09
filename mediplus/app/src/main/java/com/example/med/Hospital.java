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
import java.util.Objects;
public class Hospital extends AppCompatActivity {

     TextInputLayout hosemail,hospass;

    private ProgressDialog mLoadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e)
        {
        }
        setContentView(R.layout.activity_hospital);

        mLoadingBar=new ProgressDialog(Hospital.this);



        Button buttonh1 =findViewById(R.id.btnHLogin);
        Button buttonh2 =findViewById(R.id.btnHregister);

        buttonh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Hospital.this,Register.class);
                startActivity(intent);
                finish();
            }
        });


        buttonh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingBar.setTitle("Hospital Login");
                mLoadingBar.setMessage("Please Wait...");
                mLoadingBar.setCanceledOnTouchOutside(false);
                mLoadingBar.show();
                isUser();

            }

            private void isUser() {
                hosemail=findViewById(R.id.Hemail);
                hospass=findViewById(R.id.Hpass);
                final String userEnteredUsername = hosemail.getEditText().getText().toString().trim();
                final String userEnteredPassword = hospass.getEditText().getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Hospitals").child(userEnteredUsername).child("Login Details");
                Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);


                 if( !userEnteredPassword.isEmpty() && !userEnteredUsername.isEmpty()) {

                     checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                         @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             if (dataSnapshot.exists()) {
                                 hosemail.setError(null);

                                 String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                                 if (Objects.equals(passwordFromDB, userEnteredPassword)) {
                                     hosemail.setError(null);

                                     Intent intent = new Intent(Hospital.this, HospitalHOME.class);
                                     intent.putExtra("keyusername",userEnteredUsername);
                                     startActivity(intent);
                                     mLoadingBar.dismiss();
                                     //mLoadingBar.dismiss();
                                     Toast.makeText(Hospital.this, "Success!", Toast.LENGTH_SHORT).show();
                                     finish();

                                 } else {
                                     mLoadingBar.dismiss();
                                     hospass.setError("Wrong Password");
                                     hospass.requestFocus();


                                 }
                             } else {
                                 mLoadingBar.dismiss();
                                 hosemail.setError("No such User exist");
                                 hosemail.requestFocus();

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
                     Toast.makeText(Hospital.this, "Please Enter Your Details", Toast.LENGTH_SHORT).show();
                 }
            }
        });




    }
}