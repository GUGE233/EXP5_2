package com.example.guge.exp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {

    static List<information> list_cart = new ArrayList<>(100);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        if(list_cart.size()==0){
            list_cart.add(new information("*",123,"价格","购物车","123"));
        }
        final information info = (information) getIntent().getSerializableExtra("Info_to_cart");
        list_cart.add(new information(info.getFirstletter(),info.getPicture(),info.getPrice(),info.getName(),info.getMessage()));

        ListView listView = (ListView)findViewById(R.id.cart_list);
        MyAdapter myAdapter = new MyAdapter(cart.this,list_cart);
        listView.setAdapter(myAdapter);

    }
}
