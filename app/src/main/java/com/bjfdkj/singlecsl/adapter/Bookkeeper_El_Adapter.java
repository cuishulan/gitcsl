package com.bjfdkj.singlecsl.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bjfdkj.singlecsl.model.BookkepperModel;
import com.bjfdkj.singlecsl.views.InnerExpandableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class Bookkeeper_El_Adapter extends BaseExpandableListAdapter {
    private List<BookkepperModel.BookkeeperInfo> list;
    private Context mContext;

    public Bookkeeper_El_Adapter(List<BookkepperModel.BookkeeperInfo> list, Context context) {
        if (list != null)
            this.list = list;
        else
            list = new ArrayList<>();
        mContext = context;
    }
    /**
     * 组数
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return list.size();
    }

    /**
     * 子数
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //当子条目ID相同时是否复用
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setLeft(20);
        textView.setPadding(90, 15, 10, 15);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setText(list.get(groupPosition).getName());
        return textView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        InnerExpandableListView expandableListView = new InnerExpandableListView(mContext);
        expandableListView.setLayoutParams(params);
        Bookkeeper_El_Adapter_s adapter = new Bookkeeper_El_Adapter_s(list.get(groupPosition).getList(), mContext);
        expandableListView.setAdapter(adapter);
        expandableListView.setPadding(90, 15, 5, 15);
        return expandableListView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
