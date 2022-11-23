package com.example.myapplication;

import java.util.Date;

public class MovieModel {
    String name;
    int id;
    Date uploadDate;
    String thumbnail;

    MovieModel(String name, int id, String thumbnail){
        this.name = name;
        this.id = id;
        this.thumbnail = thumbnail;
    }

    MovieModel(){
        this.name = null;
        this.id = Integer.parseInt(null);
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }
}
