package com.reeyabhatt.medicoodac;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profilepage2 extends AppCompatActivity {

    //AlertBox
    AlertDialog.Builder builder;

    //Variables
    TextInputLayout drfullname, dremail, drphonenumber, drpassword;
    TextView drfullNamelabel, drusernameLabel;
    Button drproBtn;

    //Global Variables to hold user data inside this activity
    String _USERNAMEDR, _NAMEDR, _EMAILDR, _PHONENUMBERDR, _PASSWORDDR;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profilepage2);

        reference = FirebaseDatabase.getInstance().getReference("users");

        //Hooks
        drfullname = findViewById(R.id.pro_fullname);
        dremail = findViewById(R.id.pro_email);
        drphonenumber = findViewById(R.id.pro_phonenumber);
        drpassword = findViewById(R.id.pro_password);
        drfullNamelabel = findViewById(R.id.full_name);
        drusernameLabel = findViewById(R.id.pro_username);
        drproBtn = findViewById(R.id.probtn);

        //ShowAllData
        showAllUserData();

    }

    private void showAllUserData(){

        Intent i = getIntent();
        _USERNAMEDR = i.getStringExtra("username");
        _NAMEDR = i.getStringExtra("name");
        _EMAILDR = i.getStringExtra("email");
        _PHONENUMBERDR = i.getStringExtra("phonenumber");
        _PASSWORDDR = i.getStringExtra("password");

        drfullNamelabel.setText(_NAMEDR);
        drusernameLabel.setText(_USERNAMEDR);
        drfullname.getEditText().setText(_NAMEDR);
        dremail.getEditText().setText(_EMAILDR);
        drphonenumber.getEditText().setText(_PHONENUMBERDR);
        drpassword.getEditText().setText(_PASSWORDDR);
    }

    public void  proUpdate(View view){
        if(isNameChanged() || isPasswordChanged()){
            Toast.makeText(this, "Data Updated !", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Data is same and can't be updated.", Toast.LENGTH_SHORT).show();
    }

    private boolean isNameChanged() {
        if(!_NAMEDR.equals(drfullname.getEditText().getText().toString())){
            reference.child(_USERNAMEDR).child("name").setValue(drfullname.getEditText().getText().toString());
            _NAMEDR = drfullname.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if(!_PASSWORDDR.equals(drpassword.getEditText().getText().toString())){
            reference.child(_USERNAMEDR).child("password").setValue(drpassword.getEditText().getText().toString());
            _PASSWORDDR = drpassword.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void logout(View view) {
        builder = new AlertDialog.Builder(this);

        builder.setTitle("Log Out")
                .setMessage("Do You Want To Log Out?")
                .setCancelable(true)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(profilepage2.this, login.class);
                        startActivity(i);
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
    }
}