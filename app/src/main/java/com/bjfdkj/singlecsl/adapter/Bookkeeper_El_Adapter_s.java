package com.bjfdkj.singlecsl.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.bjfdkj.singlecsl.model.BookkepperModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class Bookkeeper_El_Adapter_s extends BaseExpandableListAdapter {
    private List<BookkepperModel.BokkeeperInfo2> list2;//省的集合
    private Context mContext;//创建布局使用

    //构造参数
    public Bookkeeper_El_Adapter_s(List<BookkepperModel.BokkeeperInfo2> list2, Context mContext) {
        if (list2 != null) {
            this.list2 = list2;
        } else
            list2 = new ArrayList<>();
        this.mContext = mContext;
    }

    //获取外层组个数
    @Override
    public int getGroupCount() {
        return list2.size();
    }

    //获取内层组个数
    @Override
    public int getChildrenCount(int i) {
        return list2.get(i).getList2().size();
    }

    //获取外层组
    @Override
    public Object getGroup(int i) {
        return list2.get(i);
    }

    //获取内层数据
    @Override
    public Object getChild(int i, int i1) {
        return list2.get(i).getList2().get(i1);
    }

    //组下标
    @Override
    public long getGroupId(int i) {
        return i;
    }

    //子View下标
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    //不知道做什么用
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //获取组布局
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(mContext);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setPadding(90, 15, 10, 15);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setText(list2.get(i).getName2());
        return textView;
    }

    //获取子View布局
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(mContext);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setPadding(110, 15, 10, 15);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setText(list2.get(i).getList2().get(i1).getName3());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 6; i++) {


                }
            }
        });
        return textView;
    }

    //子View是否可选
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}