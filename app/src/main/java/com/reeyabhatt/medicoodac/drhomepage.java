package com.reeyabhatt.medicoodac;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class drhomepage extends AppCompatActivity {

    //Global Variables to hold user data inside this activity
    String _USERNAMEDR, _NAMEDR, _EMAILDR, _PHONENUMBERDR, _PASSWORDDR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_drhomepage);

        Intent i = getIntent();
        _USERNAMEDR = i.getStringExtra("username");
        _NAMEDR = i.getStringExtra("name");
        _EMAILDR = i.getStringExtra("email");
        _PHONENUMBERDR = i.getStringExtra("phonenumber");
        _PASSWORDDR = i.getStringExtra("password");
    }

    public void openProfile(View view) {
        Intent i = new Intent(drhomepage.this, profilepage2.class);
        i.putExtra("name", _NAMEDR);
        i.putExtra("username", _USERNAMEDR);
        i.putExtra("phonenumber", _PHONENUMBERDR);
        i.putExtra("email", _EMAILDR);
        i.putExtra("password", _PASSWORDDR);
        startActivity(i);
    }

    public void openConsultList(View view) {
        Intent i = new Intent(drhomepage.this, consultlist.class);
        i.putExtra("username", _USERNAMEDR);
        startActivity(i);
    }

    public void openMeet(View view) {
        Uri uri1= Uri.parse("https://meet.google.com/");
        startActivity(new Intent(Intent.ACTION_VIEW,uri1));
    }

    public void openMail(View view) {
        String mail = "mailto:";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(mail));
        startActivity(intent);
    }
}