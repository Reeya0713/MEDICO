package com.reeyabhatt.medicoodac;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class covidtracker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_covidtracker);
    }
}