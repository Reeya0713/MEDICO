package com.reeyabhatt.medicoodac;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class scheduleappoint extends AppCompatActivity {

    private TextView tvDisplayDate;

    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 999;
    RelativeLayout relativeLayout;
    private String time ;


    // Block for declaring variables for insertion appointment details
    TextView tv1_8to9, tv2_9to10, tv3_10to11, tv4_5to6, tv5_6to7, tv6_7to8;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scheduleappoint);

        setCurrentDateOnView();
        addListenerOnButton();

        //  Block of insert data of appointment into database
        tv1_8to9 = findViewById(R.id.tv1_8to9);
        tv2_9to10 = findViewById(R.id.tv2_9to10);
        tv3_10to11 = findViewById(R.id.tv3_10to11);
        tv4_5to6 = findViewById(R.id.tv4_5to6);
        tv5_6to7 = findViewById(R.id.tv5_6to7);
        tv6_7to8 = findViewById(R.id.tv6_7to8);

        databaseUsers = FirebaseDatabase.getInstance().getReference();
        tv1_8to9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                RelativeLayout rl1 = findViewById(R.id.rl1);
                relativeLayout = rl1;
                time = "8-9 AM";
                InsertData();
            }

        });
        tv2_9to10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                RelativeLayout rl2 = findViewById(R.id.rl2);
                relativeLayout = rl2;
                time = "9-10 AM";
                InsertData();
            }

        });
        tv3_10to11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                RelativeLayout rl3 = findViewById(R.id.rl3);
                relativeLayout = rl3;
                time = "10-11 AM";
                InsertData();
            }

        });

        tv4_5to6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                RelativeLayout rl4 = findViewById(R.id.rl4);
                relativeLayout = rl4;
                time = "5-6 PM";
                InsertData();
            }

        });

        tv5_6to7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                RelativeLayout rl5 = findViewById(R.id.rl5);
                relativeLayout = rl5;
                time = "6-7 PM";
                InsertData();
            }

        });
        tv6_7to8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                RelativeLayout rl6 = findViewById(R.id.rl6);
                relativeLayout = rl6;
                time = "7-8 PM";
                InsertData();
            }

        });
    }
    // =====================================    CALENDER CODE  ============================================
    // display current date
    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));
    }

    public void addListenerOnButton() {

        tvDisplayDate =  findViewById(R.id.tvDate);

        tvDisplayDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
        }
    };


    // ======================= Block for INSERTION OF APPOINTMENT DETAILS  ================================

    private void InsertData() {

        String date = tvDisplayDate.getText().toString();
        Intent i2 = getIntent();
        String name = i2.getStringExtra("name");
        String email = i2.getStringExtra("email");
        String dr_uname = i2.getStringExtra("dr_uname");
        String app_type = i2.getStringExtra("appoint_type");
        relativeLayout.setBackgroundResource(R.color.rb);

        Intent i = new Intent(getApplicationContext(), payment.class);
        i.putExtra("name",name);
        i.putExtra("email", email);
        i.putExtra("dr_uname",dr_uname);
        i.putExtra("app_type",app_type);
        i.putExtra("date",date);
        i.putExtra("time",time);
        startActivity(i);
        finish();
    }
}