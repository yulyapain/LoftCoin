package com.azizova.loftcoin.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.azizova.loftcoin.R;
import com.azizova.loftcoin.prefs.Settings;
import com.azizova.loftcoin.ui.main.MainActivity;
import com.azizova.loftcoin.ui.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Settings settings = new Settings(getApplicationContext());
        new Handler().postDelayed(() -> {

            if (settings.shouldShowWelcomeScreen()) {
                startActivity(new Intent(this, WelcomeActivity.class));
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }

        }, 1000);
    }
}
