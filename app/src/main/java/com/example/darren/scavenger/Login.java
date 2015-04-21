package com.example.darren.scavenger;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
        setContentView(R.layout.login);
        context = getApplicationContext();
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
                Toast.makeText(context, "PLAY CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

        log_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "REGISTER CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

        log_text_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "FORGOT CLICKED", Toast.LENGTH_SHORT).show();
            }
        });
    }
}