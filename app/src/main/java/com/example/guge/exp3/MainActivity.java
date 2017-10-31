package com.example.guge.exp3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import java.util.Random;

import static com.example.guge.exp3.cart.list_cart;

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

        //创建一个商品的信息表，点击按钮后根据position把对应位置的内容传入到good_info中
        final List<information> informations = new ArrayList<information>(){
            {
                add(new information("E",R.mipmap.ef,"¥ 5.00","Enchated Forest","作者 Johanna Basford"));
                add(new information("A",R.mipmap.arla,"¥ 59.00","Arla Milk","产地 德国"));
                add(new information("D",R.mipmap.devondale,"¥ 79.00","Devondale Milk","产地 澳大利亚"));
                add(new information("K",R.mipmap.kindle,"¥ 2399.00","Kindle Oasis","版本 8GB"));
                add(new information("W",R.mipmap.waitrose,"¥ 179.00","waitrose 早餐麦片","重量 2Kg"));
                add(new information("M",R.mipmap.mcvitie,"¥ 14.90","Mcvitie's 饼干","产地 英国"));
                add(new information("F",R.mipmap.ferrero,"¥ 132.59","Ferrero Rocher","重量 300g"));
                add(new information("M",R.mipmap.maltesers,"¥ 141.43","Maltesers","重量 118g"));
                add(new information("L",R.mipmap.lindt,"¥ 139.43","Lindt","重量 249g"));
                add(new information("B",R.mipmap.borggreve,"¥ 28.90","Borggreve","重量 640g"));
            }
        };

        //点击或长按列表的操作
        final Intent intent = new Intent(MainActivity.this,good_info.class);
        commonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                information info = informations.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("first_let",info.getFirstletter());
                bundle.putInt("pic",info.getPicture());
                bundle.putString("name",info.getName());
                bundle.putString("message",info.getMessage());
                bundle.putString("price",info.getPrice());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {
                itemsList.remove(position);
                informations.remove(position);
                commonAdapter.notifyDataSetChanged();
            }
        });

        //浮动按钮相关
        final FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.FAB);
        floatingActionButton.setImageResource(R.drawable.mainpage);
        final Intent intent2 = new Intent(MainActivity.this,cart.class);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionButton.setImageResource(R.drawable.shoplist);
                if(list_cart.size()==0){
                    list_cart.add(new information("*",123,"价格","购物车","123"));
                }
                startActivity(intent2);
            }
        });

        //生成随机数，
        Random random = new Random();
        int ran = random.nextInt(itemsList.size());

        //先定义一个bundle，往里面装要传递的信息
        information information =informations.get(ran);
        Bundle bundle = new Bundle();
        bundle.putString("first_let",information.getFirstletter());
        bundle.putString("name",information.getName());
        bundle.putString("message",information.getMessage());
        bundle.putString("price",information.getPrice());
        bundle.putInt("pic",information.getPicture());

        //广播信息，action设置为“out”
        Intent intentBoradcast = new Intent("out");
        intentBoradcast.putExtras(bundle);
        sendBroadcast(intentBoradcast);





    }



}


