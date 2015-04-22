package com.example.darren.scavenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Darren on 4/20/2015.
 * Copyright 2015 Darren Syu
 */
public class Splash extends Activity{
    private static final int SPLASH_DISPLAY_TIME = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent();
                intent.setClass(Splash.this, Login.class);

                Splash.this.startActivity(intent);
                Splash.this.finish();

                // transition from splash to main menu
                overridePendingTransition(R.anim.activityfadein,
                        R.anim.splashfadeout);

            }
        }, SPLASH_DISPLAY_TIME);
    }
}
