package com.reeyabhatt.medicoodac;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class appointments extends AppCompatActivity {

    //Global Variables
    String _NAME, _EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_appointments);

        Intent i = getIntent();
        _NAME = i.getStringExtra("name");
        _EMAIL = i.getStringExtra("email");
    }

    public void xrayscanbook(View view) {
        Intent i = new Intent(appointments.this, scheduleappoint.class);
        i.putExtra("name", _NAME);
        i.putExtra("email", _EMAIL);
        i.putExtra("dr_uname", "Dr_Mosia");
        i.putExtra("appoint_type", "Xray_scan_book");
        startActivity(i);
    }

    public void labtestbook(View view) {
        Intent i = new Intent(appointments.this, scheduleappoint.class);
        i.putExtra("name", _NAME);
        i.putExtra("email", _EMAIL);
        i.putExtra("dr_uname", "Dr_Devina");
        i.putExtra("appoint_type", "Lab_test_book");
        startActivity(i);
    }

    public void healthcheckup(View view) {
        Intent i = new Intent(appointments.this, scheduleappoint.class);
        i.putExtra("name", _NAME);
        i.putExtra("email", _EMAIL);
        i.putExtra("dr_uname", "Dr_Siddhartha");
        i.putExtra("appoint_type", "Health_chechup_home");
        startActivity(i);
    }
}