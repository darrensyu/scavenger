package com.example.darren.scavenger;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by darrensyu on 5/13/15.
 * Copyright Darren Syu (C)2015
 */
public class Settings extends Activity {
    public static final String FONT_STYLE = "BGMedium.otf";

    TextView txt_notifications;
    TextView txt_vibrate;
    TextView txt_alert;
    TextView txt_push;
    TextView txt_info;
    TextView txt_support;
    TextView txt_privacy;
    TextView txt_feedback;

    Switch sw_vibrate;
    Switch sw_alert;
    Switch sw_push;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        context = getApplicationContext();
        sw_vibrate = (Switch) findViewById(R.id.settings_notifications_vibrate_switch);
        sw_alert = (Switch) findViewById(R.id.settings_notifications_alert_sounds_switch);
        sw_push = (Switch) findViewById(R.id.settings_notifications_push_switch);
        txt_notifications = (TextView) findViewById(R.id.settings_notifications_text);
        txt_vibrate = (TextView) findViewById(R.id.settings_notifications_vibrate);
        txt_alert = (TextView) findViewById(R.id.settings_notifications_alert_sounds);
        txt_push = (TextView) findViewById(R.id.settings_notifications_push);
        txt_info = (TextView) findViewById(R.id.settings_info_text);
        txt_support = (TextView) findViewById(R.id.settings_support_text);
        txt_privacy = (TextView) findViewById(R.id.settings_privacy_policy_text);
        txt_feedback = (TextView) findViewById(R.id.settings_feedback_text);

        txt_notifications.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_STYLE));
        txt_vibrate.setTypeface(Typeface.createFromAsset(context.getAssets(),FONT_STYLE));
        txt_alert.setTypeface(Typeface.createFromAsset(context.getAssets(),FONT_STYLE));
        txt_push.setTypeface(Typeface.createFromAsset(context.getAssets(),FONT_STYLE));
        txt_info.setTypeface(Typeface.createFromAsset(context.getAssets(),FONT_STYLE));
        txt_support.setTypeface(Typeface.createFromAsset(context.getAssets(),FONT_STYLE));
        txt_privacy.setTypeface(Typeface.createFromAsset(context.getAssets(),FONT_STYLE));
        txt_feedback.setTypeface(Typeface.createFromAsset(context.getAssets(),FONT_STYLE));

    }
}
