package com.bjfdkj.singlecsl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/6/4 0004.
 *
 * 个人详细资料  适配
 */

public class Personal_Adapter extends BaseAdapter {
    Context      context;
    List<String> list;

    public Personal_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView==null){
        }
        return null;
    }
    class MyViewHolder  {

    }
}
