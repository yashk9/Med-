package com.example.med;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.med.PatientHOME;

public class PaymentSuccessful extends AppCompatActivity {

    Button btnGotoDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_payment_successful);
        btnGotoDashboard=findViewById(R.id.btnGotoDashboard);

        btnGotoDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PaymentSuccessful.this, PatientHOME.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(PaymentSuccessful.this,PatientHOME.class);
        startActivity(i);
    }
}