package com.example.darren.scavenger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Darren on 5/14/2015.
 * Copyright Darren Syu (C)2015
 */
public class ChestAdapter extends ArrayAdapter<ChestItem>{
    public ChestAdapter(Context context, ArrayList<ChestItem> items){
        super(context,0,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ChestItem item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chest_row, parent, false);
        }
        TextView row_id = (TextView) convertView.findViewById(R.id.chest_row_number);
        TextView row_message = (TextView) convertView.findViewById(R.id.chest_row_text);
        row_id.setText(String.valueOf(item.getId()));
        row_message.setText(item.getMsg());

        return convertView;
    }
}
