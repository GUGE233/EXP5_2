package com.example.guge.exp3;

import java.io.Serializable;

/**
 * Created by GUGE on 2017/10/23.
 */

public class information implements Serializable {
    private int picture;
    private String price;
    private String name;
    private String message;
    private String firstletter;


    public information(String firstletter,int picture,String price,String name,String message){
        this.firstletter = firstletter;
        this.picture = picture;
        this.price = price;
        this.name = name;
        this. message = message;
    }

    public void setFirstletter(String firstletter){
        this.firstletter = firstletter;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPicture(int picture){
        this.picture = picture;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public void setMessage(String message){
        this.message = message;
    }

    public String getFirstletter(){
        return firstletter;
    }
    public int getPicture(){
        return picture;
    }
    public String getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public String getMessage(){
        return message;
    }
}
