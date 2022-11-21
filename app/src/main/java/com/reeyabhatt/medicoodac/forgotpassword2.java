package com.reeyabhatt.medicoodac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class forgotpassword2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgotpassword2);
    }

    public void otpfp(View view) {
        Intent i = new Intent(forgotpassword2.this, forgotpassword3.class);
        startActivity(i);
        finish();
    }
}