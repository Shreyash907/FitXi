package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    ImageView img1;
    Animation top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img1 = (ImageView)findViewById(R.id.logo);
        top = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.main_logo);
        img1.setAnimation(top);

        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}