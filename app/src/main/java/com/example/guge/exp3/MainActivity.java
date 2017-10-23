package com.example.guge.exp3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //最难搞的RecyclerView！！！！！

        RecyclerView recyclerview = (RecyclerView)findViewById(R.id.Rec123);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        final List<items> itemsList = new ArrayList<items>(10);
        String[] firstlet = new String[] {"E", "A", "D","K","W","M","F","M","L","B"};
        String[] goodsnam = new String[] {"Enchated Forest", "Arla Milk", "Devondale Milk", "Kindle Oasis",
                "waitrose 早餐麦片", "Mcvitie's 饼干", "Ferrero Rocher", "Maltesers", "Lindt", "Borggreve"};
        for(int i=0;i<10;i++){
            items temp = new items();
            temp.setFirstletter(firstlet[i]);
            temp.setGoodsname(goodsnam[i]);
            itemsList.add(temp);
        }
        final CommonAdapter commonAdapter = new CommonAdapter(this,R.layout.item,itemsList) {
            @Override
            public void convert(ViewHolder holder,items item){
                TextView name = holder.getView(R.id.goona);
                name.setText(item.getGoodsname());
                TextView first = holder.getView(R.id.fislet);
                first.setText(item.getFirstletter());
            }
        };
        recyclerview.setAdapter(commonAdapter);

        final Intent intent = new Intent(MainActivity.this,good_info.class);
        commonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                startActivity(intent);

                //  setContentView(R.layout.goods_ifo);
            }

            @Override
            public void onLongClick(int position) {
                itemsList.remove(position);
                commonAdapter.notifyDataSetChanged();
            }
        });







    }



}



