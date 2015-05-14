package com.example.darren.scavenger;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by darrensyu on 5/13/15.
 * Copyright Darren Syu (C)2015
 */
public class Register extends Activity {
    public static final String FONT_STYLE = "BGMedium.otf";

    EditText reg_et_username;
    EditText reg_et_email;
    EditText reg_et_pw;
    ImageButton reg_btn_register;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        context = getApplicationContext();
        reg_et_username = (EditText) findViewById(R.id.reg_et_username);
        reg_et_email = (EditText) findViewById(R.id.reg_et_email);
        reg_et_pw = (EditText) findViewById(R.id.reg_et_pw);
        reg_btn_register = (ImageButton) findViewById(R.id.reg_btn_register);

        reg_et_username.setTypeface(Typeface.createFromAsset(context.getAssets(),FONT_STYLE));
        reg_et_email.setTypeface(Typeface.createFromAsset(context.getAssets(),FONT_STYLE));
        reg_et_pw.setTypeface(Typeface.createFromAsset(context.getAssets(),FONT_STYLE));

        reg_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
