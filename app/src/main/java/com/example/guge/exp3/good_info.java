package com.example.guge.exp3;

import android.app.usage.UsageEvents;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.guge.exp3.cart.list_cart;


public class good_info extends AppCompatActivity {
    boolean flag = true;
    MyBroadCast2 myBroadCast2 = new MyBroadCast2();

    //    准备订阅者
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInformation(information infor){

        list_cart.add(infor);
        //Toast.makeText(this,infor.getName(),Toast.LENGTH_SHORT).show();


    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //注销订阅者
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_info);

        //注册订阅者
        EventBus.getDefault().register(this);




        //商品详情页面底部的ListView
        ListView bott_list = (ListView)findViewById(R.id.bot_list);
        final List<String> bot_list = new ArrayList<String>(4);
        String[] bot = new String[]{"一键下单","分享商品","不感兴趣","查看更多商品促销信息"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,bot);
        bott_list.setAdapter(arrayAdapter);


        //设定图片、名字、价格、信息
//        final information inf = (information) getIntent().getSerializableExtra("Info");
        final Bundle bundle = getIntent().getExtras();
        ImageView pic = (ImageView)findViewById(R.id.shop_picture);
        pic.setImageResource(bundle.getInt("pic"));
        TextView name = (TextView)findViewById(R.id.shop_name);
        name.setText(bundle.getString("name"));
        TextView price = (TextView)findViewById(R.id.shop_price);
        price.setText(bundle.getString("price"));
        TextView message = (TextView)findViewById(R.id.shop_message);
        message.setText(bundle.getString("message"));

        //返回按钮
        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //星星图标处理
        final Button star = (Button)findViewById(R.id.star);

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    star.setBackground(getDrawable(R.drawable.empty_star));
                    flag = false;
                }
                else{
                    star.setBackground(getDrawable(R.drawable.full_star));
                    flag = true;
                }
            }
        });


        //购物车按钮处理

        IntentFilter dynamic_filter = new IntentFilter();
        dynamic_filter.addAction("to_cart");

        registerReceiver(myBroadCast2, dynamic_filter);

        final information info = new information(bundle.getString("first_let"),bundle.getInt("pic"),bundle.getString("price"),bundle.getString("name"),bundle.getString("message"));

        final Intent intent = new Intent(good_info.this,cart.class);
        Button cart = (Button)findViewById(R.id.shop_cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //按下购物车按钮就把商品的信息加入到购物车信息表中，当通过浮动按钮启动购物车的activity时，Adapter才吧信息表中的内容填充到购物车的Listview中
                if(list_cart.size()==0){
                    list_cart.add(new information("*",123,"价格","购物车","123"));
                }
                //list_cart.add(info);

                //点击购物车按钮后广播信息
                Bundle bundle = new Bundle();
                bundle.putString("first_let",info.getFirstletter());
                bundle.putString("name",info.getName());
                bundle.putString("message",info.getMessage());
                bundle.putString("price",info.getPrice());
                bundle.putInt("pic",info.getPicture());
                Intent intentBoradcast = new Intent("to_cart");
                intentBoradcast.putExtras(bundle);
                sendBroadcast(intentBoradcast);


                //传递事件
                EventBus.getDefault().post(info);

                Toast.makeText(good_info.this,"成功将商品加入购物车",Toast.LENGTH_SHORT).show();


            }
        });

        unregisterReceiver(myBroadCast2);





    }
}
