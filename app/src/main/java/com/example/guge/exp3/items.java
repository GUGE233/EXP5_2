package com.example.guge.exp3;

/**
 * Created by GUGE on 2017/10/22.
 */

public class items {
    private String firstletter;
    private String goodsname;

    public items(){
        firstletter = "";
        goodsname = "";
    }

    public String getFirstletter(){
        return firstletter;
    }

    public String getGoodsname(){
        return goodsname;
    }

    public void setFirstletter(String firstletter1){
        firstletter = firstletter1;
    }

    public void setGoodsname(String goodsname1){
        goodsname = goodsname1;
    }
}
