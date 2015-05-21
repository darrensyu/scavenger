package com.example.darren.scavenger;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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
    Activity act;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        context = getApplicationContext();
        act = this;

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
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        Intent intent = new Intent();
                        intent.setClass(MainScreen.this, Chest.class);

                        MainScreen.this.startActivity(intent);

                        // transition from splash to main menu
                        overridePendingTransition(R.anim.activityfadein,
                                R.anim.splashfadeout);

                    }
                }, 500);
            }
        });

        btn_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE","QR_CODE_MODE");
                    startActivityForResult(intent,0);
                } catch (ActivityNotFoundException e){
                    //showDialog();
                    Toast.makeText(context,"Download \"Barcode Scanner\" " +
                            "from the Google Play Store to continue!",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_scav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        Intent intent = new Intent();
                        intent.setClass(MainScreen.this, Scavenge.class);

                        MainScreen.this.startActivity(intent);

                        // transition from splash to main menu
                        overridePendingTransition(R.anim.activityfadein,
                                R.anim.splashfadeout);
                    }
                }, 500);
            }
        });

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        Intent intent = new Intent();
                        intent.setClass(MainScreen.this, Settings.class);

                        MainScreen.this.startActivity(intent);

                        // transition from splash to main menu
                        overridePendingTransition(R.anim.activityfadein,
                                R.anim.splashfadeout);
                    }
                }, 500);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if (requestCode == 0){
            if(resultCode == RESULT_OK){
                String content = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast.makeText(context,"Content: " + content +
                        "\n\nFormat: " + format, Toast.LENGTH_LONG).show();
            }
        }
    }
/*
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Scanner Found.");
        builder.setMessage("Please download \"Barcode Scanner\" " +
                "from the Play Store to continue!");
        builder.setPositiveButton("Download Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Log.d("MAIN_SCREEN_ERR: ", e.getLocalizedMessage());
                }
            }
        });
        builder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/
}
