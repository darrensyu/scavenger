package com.example.darren.scavenger;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Darren on 5/14/2015.
 * Copyright Darren Syu (C)2015
 */
public class Chest extends Activity {
    ListView chestList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chest);
        loadAdapter();
    }

    private void loadAdapter(){
        ArrayList<ChestItem> items = new ArrayList<>(10);
        items.add(0,new ChestItem(1,"$5 GIFT CARD TO JAMBA JUICE","url1"));
        items.add(1,new ChestItem(2,"10% OFF TAPIOCA EXPRESS","url2"));
        ChestAdapter chestAdapter = new ChestAdapter(this,items);
        ListView listView = (ListView) findViewById(R.id.chest_list);
        listView.setAdapter(chestAdapter);
    }
}
