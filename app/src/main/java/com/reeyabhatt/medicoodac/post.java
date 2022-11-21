package com.reeyabhatt.medicoodac;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_post);
    }

    public void gmail(View view) {
        String mail = "mailto:";
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse(mail));
        startActivity(i);
    }
}