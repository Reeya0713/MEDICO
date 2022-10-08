package com.reeyabhatt.medicoodac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profilepage extends AppCompatActivity {

    //Variables
    TextInputLayout fullname, email, phonenumber, password;
    TextView fullNamelabel, usernameLabel;
    Button proBtn;

    //Global Variables to hold user data inside this activity
    String _USERNAME, _NAME, _EMAIL, _PHONENUMBER, _PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profilepage);

        reference = FirebaseDatabase.getInstance().getReference("users");

        //Hooks
        fullname = findViewById(R.id.pro_fullname);
        email = findViewById(R.id.pro_email);
        phonenumber = findViewById(R.id.pro_phonenumber);
        password = findViewById(R.id.pro_password);
        fullNamelabel = findViewById(R.id.full_name);
        usernameLabel = findViewById(R.id.pro_username);
        proBtn = findViewById(R.id.probtn);

        //ShowAllData
        showAllUserData();

    }

    private void showAllUserData(){

        Intent i = getIntent();
        _USERNAME = i.getStringExtra("username");
        _NAME = i.getStringExtra("name");
        _EMAIL = i.getStringExtra("email");
        _PHONENUMBER = i.getStringExtra("phonenumber");
        _PASSWORD = i.getStringExtra("password");

        fullNamelabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        fullname.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        phonenumber.getEditText().setText(_PHONENUMBER);
        password.getEditText().setText(_PASSWORD);
    }

    public void  proUpdate(View view){
        if(isNameChanged() || isPasswordChanged()){
            Toast.makeText(this, "Data Updated !", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Data is same and can't be updated.", Toast.LENGTH_SHORT).show();
    }

    private boolean isNameChanged() {
        if(!_NAME.equals(fullname.getEditText().getText().toString())){
            reference.child(_USERNAME).child("name").setValue(fullname.getEditText().getText().toString());
            _NAME = fullname.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if(!_PASSWORD.equals(password.getEditText().getText().toString())){
            reference.child(_USERNAME).child("password").setValue(password.getEditText().getText().toString());
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }
}