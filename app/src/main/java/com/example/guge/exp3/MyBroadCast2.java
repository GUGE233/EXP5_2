package com.example.guge.exp3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by GUGE on 2017/10/28.
 */


//自定义一个确定马上下单通知内容的广播
public class MyBroadCast2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals("to_cart")){
            Bundle bundle = intent.getExtras();
            //Toast.makeText(context, "加入购物车", Toast.LENGTH_SHORT).show();
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),bundle.getInt("pic"));
            Notification.Builder  builder = new Notification.Builder(context);
            builder.setContentTitle("马上下单")
                    .setContentText(bundle.getString("name") + "已添加到购物车" )
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.drawable.shoplist)
                    .setTicker("买买买！！！"+"买买买")
                    .setSmallIcon(bundle.getInt("pic"));

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            //和推荐通知不同，由于更新购物车的操作已经完成，所以该通知不需要再传递信息给购物车，只需跳转到购物车页面即可
            Intent intent1 = new Intent(context,cart.class);
            PendingIntent pi = PendingIntent.getActivity(context,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pi);

            Notification notification = builder.build();
            manager.notify(0,notification);


        }
    }
}
