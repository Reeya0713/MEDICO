package com.reeyabhatt.medicoodac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolBarMenu;

    //Global Variables to hold user data inside this activity
    String _USERNAME, _NAME, _EMAIL, _PHONENUMBER, _PASSWORD;

    //AlertBox
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolBarMenu = findViewById(R.id.toolbarMenu);

        //Toolbar
        setSupportActionBar(toolBarMenu);

        Intent i = getIntent();
        _USERNAME = i.getStringExtra("username");
        _NAME = i.getStringExtra("name");
        _EMAIL = i.getStringExtra("email");
        _PHONENUMBER = i.getStringExtra("phonenumber");
        _PASSWORD = i.getStringExtra("password");

                /*Hide or show items
                Menu menu = navigationView.getMenu();
                menu.findItem(R.id.nav_logout).setVisible(false);*/

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolBarMenu, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
    }

    //To avoid closing the application on Back Pressed
    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //nav_logout
        builder = new AlertDialog.Builder(this);

        switch (item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_appointment:
                Intent i = new Intent(homepage.this, appointments.class);
                i.putExtra("name", _NAME);
                i.putExtra("email", _EMAIL);
                startActivity(i);
                Toast.makeText(this, "Appointments", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_covidtracker:
                Intent i2 = new Intent(homepage.this, covidtracker.class);
                startActivity(i2);
                Toast.makeText(this, "Covid Tracker", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_pro:
                Intent i3 = new Intent(homepage.this, profilepage.class);
                i3.putExtra("name", _NAME);
                i3.putExtra("username", _USERNAME);
                i3.putExtra("phonenumber", _PHONENUMBER);
                i3.putExtra("email", _EMAIL);
                i3.putExtra("password", _PASSWORD);
                startActivity(i3);
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;

            case  R.id.nav_post:
                Intent i4 = new Intent(homepage.this, post.class);
                startActivity(i4);
                Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                builder.setTitle("Log Out")
                        .setMessage("Do You Want To Log Out?")
                        .setCancelable(true)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i5 = new Intent(homepage.this, login.class);
                                startActivity(i5);
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void dr1(View view) {
        Intent i = new Intent(getApplicationContext(), dr1.class);
        i.putExtra("name", _NAME);
        i.putExtra("email", _EMAIL);
        startActivity(i);
    }

    public void dr2(View view) {
        Intent i = new Intent(getApplicationContext(), dr2.class);
        i.putExtra("name", _NAME);
        i.putExtra("email", _EMAIL);
        startActivity(i);
    }

    public void dr3(View view) {
        Intent i = new Intent(getApplicationContext(), dr3.class);
        i.putExtra("name", _NAME);
        i.putExtra("email", _EMAIL);
        startActivity(i);
    }

    public void dr4(View view) {
        Intent i = new Intent(getApplicationContext(), dr4.class);
        i.putExtra("name", _NAME);
        i.putExtra("email", _EMAIL);
        startActivity(i);
    }

    public void dr5(View view) {
        Intent i = new Intent(getApplicationContext(), dr5.class);
        i.putExtra("name", _NAME);
        i.putExtra("email", _EMAIL);
        startActivity(i);
    }

    public void dr6(View view) {
        Intent i = new Intent(getApplicationContext(), dr6.class);
        i.putExtra("name", _NAME);
        i.putExtra("email", _EMAIL);
        startActivity(i);
    }
}