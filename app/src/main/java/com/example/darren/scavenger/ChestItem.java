package com.example.darren.scavenger;

/**
 * Created by Darren on 5/14/2015.
 * Copyright Darren Syu (C)2015
 */
public class ChestItem {
    public int id;
    public String msg;
    public String link;

    public ChestItem(int id, String msg, String link){
        this.id = id;
        this.msg = msg;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public String getLink() {
        return link;
    }
}
