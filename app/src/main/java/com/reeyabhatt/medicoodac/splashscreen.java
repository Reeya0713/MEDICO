package com.reeyabhatt.medicoodac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class splashscreen extends AppCompatActivity {

        //Variables
        Animation topAnim, bottomAnim;
        ImageView medicoimage;
        TextView medicotext, vrs;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_splashscreen);

            //Animations
            topAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.top_animation);
            bottomAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

            //Hooks
            medicoimage = findViewById(R.id.medicoimg);
            medicotext = findViewById(R.id.medicotxt);
            vrs = findViewById(R.id.version);

            medicoimage.setAnimation(topAnim);
            medicotext.setAnimation(bottomAnim);
            vrs.setAnimation(bottomAnim);

            //splash
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(splashscreen.this, login.class);
                    startActivity(i);
                    finish();
                }
            }, 3500);
    }
}