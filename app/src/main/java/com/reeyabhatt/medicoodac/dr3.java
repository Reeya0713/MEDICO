package com.reeyabhatt.medicoodac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class dr3 extends AppCompatActivity {

    //Global Variables
    String _NAME, _EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dr3);

        Intent i = getIntent();
        _NAME = i.getStringExtra("name");
        _EMAIL = i.getStringExtra("email");
    }

    public void consult3(View view) {
        Intent i = new Intent(getApplicationContext(),scheduleappoint.class);
        i.putExtra("name", _NAME);
        i.putExtra("email", _EMAIL);
        i.putExtra("dr_uname", "Dr_Aditya");
        i.putExtra("appoint_type", "Dr_Aditya_video_appoint");
        startActivity(i);
    }
}