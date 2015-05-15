package com.example.darren.scavenger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Darren on 4/20/2015.
 * Copyright 2015 Darren Syu
 */
public class Login extends Activity{
    public static final String FONT_STYLE = "BGMedium.otf";

    ImageButton log_btn_play;
    ImageButton log_btn_register;
    EditText log_et_username;
    EditText log_et_password;
    CheckBox log_check_save;
    TextView log_text_forgot;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        FacebookSdk.sdkInitialize(context);
        setContentView(R.layout.login);
        log_btn_play = (ImageButton) findViewById(R.id.login_btn_play);
        log_btn_register = (ImageButton) findViewById(R.id.login_btn_register);
        log_et_username = (EditText) findViewById(R.id.login_et_user);
        log_et_password = (EditText) findViewById(R.id.login_et_password);
        log_check_save = (CheckBox) findViewById(R.id.login_check_save_pw);
        log_text_forgot = (TextView) findViewById(R.id.login_tv_forgot);

        log_et_username.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_STYLE));
        log_et_password.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_STYLE));

        log_btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        Intent intent = new Intent();
                        intent.setClass(Login.this, Splash.class);

                        Login.this.startActivity(intent);
                        Login.this.finish();

                        // transition from splash to main menu
                        overridePendingTransition(R.anim.activityfadein,
                                R.anim.splashfadeout);

                    }
                }, 500);
            }
        });

        log_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        Intent intent = new Intent();
                        intent.setClass(Login.this, Register.class);

                        Login.this.startActivity(intent);

                        // transition from splash to main menu
                        overridePendingTransition(R.anim.activityfadein,
                                R.anim.splashfadeout);

                    }
                }, 500);
            }
        });

        log_text_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "FORGOT CLICKED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }
}
