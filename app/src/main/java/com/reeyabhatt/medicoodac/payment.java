package com.reeyabhatt.medicoodac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class payment extends AppCompatActivity {

    Button pay;
    String TAG ="main";
    final int UPI_PAYMENT = 0;

    DatabaseReference databaseUsers;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(payment.this,homepage.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_payment);
        pay = (Button) findViewById(R.id.pay);
        String name = "Kaushik Kidecha";
        String upi_id = "kidechakaushik55@oksbi";
        String note = "Fees payment";
        String amount = "1";

        pay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //Getting the values from the EditTexts
                if (TextUtils.isEmpty(name.trim())){
                    Toast.makeText(payment.this," Name is invalid", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(upi_id.trim())){
                    Toast.makeText(payment.this," UPI ID is invalid", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(note.trim())){
                    Toast.makeText(payment.this," Note is invalid", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(amount.trim())){
                    Toast.makeText(payment.this," Amount is invalid", Toast.LENGTH_SHORT).show();
                }else{
                    payUsingUpi("Rahul Pravinbhai Bhilavala","rahulbhilavala@oksbi", note.toString(), amount.toString());
                }
            }
        });
    }
    void payUsingUpi(  String name,String upiId, String note, String amount) {
        Log.e("main ", "name "+name +"--up--"+upiId+"--"+ note+"--"+amount);
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                //.appendQueryParameter("mc", "")
                //.appendQueryParameter("tid", "02125412")
                //.appendQueryParameter("tr", "25584584")
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                //.appendQueryParameter("refUrl", "blueapp")
                .build();
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(payment.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response "+resultCode );
        /*
       E/main: response -1
       E/UPI: onActivityResult: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPIPAY: upiPaymentDataOperation: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPI: payment successfull: 922118921612
         */
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    //when user simply back without payment
                    Log.e("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }
    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(payment.this)) {
            String str = data.get(0);
            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(payment.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "payment successfull: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(payment.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Cancelled by user: "+approvalRefNo);
                // ==============================================================================================================================

                ConApp();
                Intent i = new Intent(payment.this,homepage.class);
                startActivity(i);
                finish();
            }
            else {
                Toast.makeText(payment.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "failed payment: "+approvalRefNo);

                ConApp();
                Intent i = new Intent(payment.this,homepage.class);
                startActivity(i);
                finish();
            }
        } else {
            Log.e("UPI", "Internet issue: ");
            Toast.makeText(payment.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void ConApp() {
        databaseUsers = FirebaseDatabase.getInstance().getReference();

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String email = i.getStringExtra("email");
        String dr_uname = i.getStringExtra("dr_uname");
        String app_type = i.getStringExtra("app_type");
        String date = i.getStringExtra("date");
        String time = i.getStringExtra("time");

        String id  = databaseUsers.push().getKey();

        User user = new User(name, email, dr_uname, app_type, date, time);

        databaseUsers.child("appointments").child(app_type+id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(payment.this, "Slot Booked on "+date+" at "+time, Toast.LENGTH_LONG).show();
//                    relativeLayout.setBackgroundResource(R.color.purple_200);
//                    Intent i = new Intent(payment.this, payment.class);
//                    startActivity(i);
//                    finish();
                }
            }
        });
    }

    public static boolean isConnectionAvailable(payment context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}