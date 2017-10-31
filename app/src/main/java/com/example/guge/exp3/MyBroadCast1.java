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

/**
 * Created by GUGE on 2017/10/28.
 */

//自定义一个确定推荐通知内容的广播
public class MyBroadCast1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals("out")){
            Bundle bundle = intent.getExtras();
            //设置弹出信息的具体内容
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),bundle.getInt("pic"));
            Notification.Builder  builder = new Notification.Builder(context);
            builder.setContentTitle("新商品热卖")
                    .setContentText(bundle.getString("name") + "仅售" + bundle.getString("price"))
                    .setLargeIcon(bitmap)
                    .setSmallIcon(bundle.getInt("pic"))
                    .setTicker("买买买！！！"+"买买买")
                    .setSmallIcon(bundle.getInt("pic"));

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            //由于点击通知要跳转到和通知上信息一致的商品详情界面，所以要吧通知信息的bundle也一起发送给商品详情页面
            Intent intent1 = new Intent(context,good_info.class);
            intent1.putExtras(bundle);
            PendingIntent pi = PendingIntent.getActivity(context,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pi);

            Notification notification = builder.build();
            manager.notify(0,notification);

        }
    }
}
