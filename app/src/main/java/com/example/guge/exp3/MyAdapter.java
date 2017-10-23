package com.example.guge.exp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by GUGE on 2017/10/23.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<information> list;

    public MyAdapter(Context context,List<information> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount(){
        if(list == null){
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if(list == null){
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView;
        ViewHolder viewHolder;
        if(view == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item,null);
            viewHolder = new ViewHolder();
            viewHolder.firstletter = (TextView) convertView.findViewById(R.id.fisletc);
            viewHolder.name = (TextView) convertView.findViewById(R.id.goonac);
            viewHolder.price = (TextView) convertView.findViewById(R.id.cart_price);
            convertView.setTag(viewHolder);
        }
        else{
            convertView = view;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.firstletter.setText(list.get(i).getFirstletter());
        viewHolder.name.setText(list.get(i).getName());
        viewHolder.price.setText(list.get(i).getPrice());
        return convertView;
    }

    private class ViewHolder{
        public TextView firstletter;
        public TextView name;
        public TextView price;
    }
}
