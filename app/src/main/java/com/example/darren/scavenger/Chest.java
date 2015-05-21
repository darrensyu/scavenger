package com.example.darren.scavenger;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.PointTarget;

import java.util.ArrayList;

/**
 * Created by Darren on 5/14/2015.
 * Copyright Darren Syu (C)2015
 */
public class Chest extends Activity {
    ListView chestList;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.chest);
        boolean chestft = this.getSharedPreferences("tutorial", Context.MODE_PRIVATE)
                .getBoolean("chestFirst", true);
        if(chestft){
            final ShowcaseView showcaseView = new ShowcaseView.Builder(this,true)
                    .setStyle(R.style.CustomShowcaseTheme)
                    .setTarget(new PointTarget(0, 1000000))
                    //.setTarget(new ViewTarget(chestList));
                    .setContentTitle("How to use Chest!")
                    .setContentText("Here is all the information you need to know about " +
                            "the Chest feature!")
                    .build();
            showcaseView.show();
            showcaseView.overrideButtonClick(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showcaseView.hide();
                    SharedPreferences sharedPreferences = context
                            .getSharedPreferences("tutorial",Context.MODE_PRIVATE);
                    SharedPreferences.Editor spe = sharedPreferences.edit();
                    spe.putBoolean("chestFirst",false);
                    spe.apply();
                }
            });
        }
        loadAdapter();
    }

    private void loadAdapter(){
        ArrayList<ChestItem> items = new ArrayList<>(10);
        items.add(0,new ChestItem(1,"$5 GIFT CARD TO JAMBA JUICE","url1"));
        items.add(1, new ChestItem(2, "10% OFF TAPIOCA EXPRESS","url2"));
        ChestAdapter chestAdapter = new ChestAdapter(this,items);
        ListView listView = (ListView) findViewById(R.id.chest_list);
        listView.setAdapter(chestAdapter);
    }
}
