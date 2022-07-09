package com.example.med;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

//HOSPITAL REGISTRATION PAGE
public class Register extends AppCompatActivity {



    TextInputLayout rhname, rhusername, rhemail, rhphone, rhpassword;
    Button rhregister,rhlogin;
    private ProgressDialog mLoadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        try
        {
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e)
        {
        }



        rhname= findViewById(R.id.hosx_name);
        rhemail= findViewById(R.id.hosx_email);
        rhphone= findViewById(R.id.hosx_phone);
        rhpassword= findViewById(R.id.hosx_password);
        rhregister= findViewById(R.id.hosx_register);
        rhlogin=findViewById(R.id.hosx_login);

        mLoadingBar=new ProgressDialog(Register.this);




        rhlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Register.this, Hospital.class);
                startActivity(intent);
                finish();

            }
        });



        rhregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String hosname = rhname.getEditText().getText().toString();
                String hosusername = rhemail.getEditText().getText().toString();
                String hosphone = rhphone.getEditText().getText().toString();
                String hospassword = rhpassword.getEditText().getText().toString();



                if (!hosname.isEmpty() && !hosusername.isEmpty() && !hosphone.isEmpty() && !hospassword.isEmpty()) {


                    FirebaseDatabase Hdb= FirebaseDatabase.getInstance();
                    DatabaseReference HospitalDB=Hdb.getReference().child("Hospitals").child(hosusername).child("Login Details");

                    mLoadingBar.setTitle("Registration");
                    mLoadingBar.setMessage("Please Wait...");
                    mLoadingBar.setCanceledOnTouchOutside(false);
                    mLoadingBar.show();

                    UserHelperClassH helperClass1 = new UserHelperClassH(hosname, hosusername, hospassword, hosphone);
                    HospitalDB.child(hosusername).setValue(helperClass1);

                    mLoadingBar.dismiss();

                    Intent intenth = new Intent(Register.this, HospitalHOME.class);
                    intenth.putExtra("hos_name",hosname);
                    intenth.putExtra("hos_phone",hosphone);
                    startActivity(intenth);
                    Toast toast = Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();

                }



                else {
                    Toast toast=Toast.makeText(Register.this, "Please Check Your Details", Toast.LENGTH_SHORT);
                    toast.show();

                }

            }



    });
}
}