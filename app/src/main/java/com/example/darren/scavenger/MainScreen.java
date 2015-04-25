package com.example.darren.scavenger;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Darren on 4/21/2015.
 * Copyright 2015 Darren Syu
 */
public class MainScreen extends Activity {

    public static final String FONT_STYLE = "BGMedium.otf";

    ImageButton btn_chest;
    ImageButton btn_invite;
    ImageButton btn_scav;
    ImageButton btn_settings;
    TextView text_chest;
    TextView text_invite;
    TextView text_scav;
    TextView text_settings;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        context = getApplicationContext();

        btn_chest = (ImageButton) findViewById(R.id.main_btn_chest);
        btn_invite = (ImageButton) findViewById(R.id.main_btn_invite);
        btn_scav = (ImageButton) findViewById(R.id.main_btn_scav);
        btn_settings = (ImageButton) findViewById(R.id.main_btn_settings);
        text_chest = (TextView) findViewById(R.id.main_text_chest);
        text_invite = (TextView) findViewById(R.id.main_text_invite);
        text_scav = (TextView) findViewById(R.id.main_text_scav);
        text_settings = (TextView) findViewById(R.id.main_text_settings);

        text_chest.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_STYLE));
        text_invite.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_STYLE));
        text_scav.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_STYLE));
        text_settings.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_STYLE));

        btn_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CHEST CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

        btn_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "INVITE CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

        btn_scav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "SCAV CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "SETTINGS CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
