package com.example.guge.exp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class good_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_info);

        //商品详情页面底部的ListView
        ListView bott_list = (ListView)findViewById(R.id.bot_list);
        final List<String> bot_list = new ArrayList<String>(4);
        String[] bot = new String[]{"一键下单","分享商品","不感兴趣","查看更多商品促销信息"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,bot);
        bott_list.setAdapter(arrayAdapter);


        //设定图片、名字、价格、信息
        information inf = (information) getIntent().getSerializableExtra("Info");
        ImageView pic = (ImageView)findViewById(R.id.shop_picture);
        pic.setImageResource(inf.getPicture());
        TextView name = (TextView)findViewById(R.id.shop_name);
        name.setText(inf.getName());
        TextView price = (TextView)findViewById(R.id.shop_price);
        price.setText(inf.getPrice());
        TextView message = (TextView)findViewById(R.id.shop_message);
        message.setText(inf.getMessage());
    }
}
