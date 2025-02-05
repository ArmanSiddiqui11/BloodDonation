package com.example.blooddonate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.airbnb.lottie.LottieAnimationView;

//import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LottieAnimationView lottieView = findViewById(R.id.lottieView);

        // Fade-in animation
        Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1500);
        lottieView.startAnimation(fadeIn);

        // Splash duration and transition to MainActivity
        new Handler().postDelayed(() -> {
            Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
            fadeOut.setDuration(1000);
            lottieView.startAnimation(fadeOut);

            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {
                    lottieView.setVisibility(View.GONE);
                    startActivity(new Intent(splash.this, LoginActivity.class));
                    finish();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }, 3000);
    }
}