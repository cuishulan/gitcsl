package com.bjfdkj.singlecsl.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.db.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29 0029.
 * <p>
 * 初始适配布局
 */

public class Info_Rv_Adapter extends RecyclerView.Adapter<Info_Rv_Adapter.BookkeeperViewHolder> {

    List<UserInfo> list;
    Context context;
    //回调接口
    OnClickL onClickL;

    public interface OnClickL {
        void startItem(View view, int postion);
    }

    public void setOnClickL(OnClickL onClickL) {
        this.onClickL = onClickL;
    }

    public Info_Rv_Adapter(List<UserInfo> list, Context context) {
        if (list != null)
            this.list = list;
        else
            list = new ArrayList<>();
        this.context = context;
    }

    @Override
    public BookkeeperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //        View view = View.inflate(context, R.layout.bookkepper_item_rv, parent);
        View view = LayoutInflater.from(context).inflate(R.layout.bookkepper_item_rv, parent, false);
        BookkeeperViewHolder holder = new BookkeeperViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickL != null) {
                    onClickL.startItem(v, (Integer) v.getTag());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(BookkeeperViewHolder holder, int position) {
        UserInfo userInfo = list.get(position);
        holder.tv.setText(userInfo.getUserunit() + "");
        holder.tv2.setText(userInfo.getUsername() + "");
        holder.tv3.setText(userInfo.getUsersex() + "");
        holder.tv4.setText(userInfo.getUsernation() + "");
        holder.tv5.setText(userInfo.getUserduty() + "");
        holder.linearLayout.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BookkeeperViewHolder extends RecyclerView.ViewHolder {
        TextView tv, tv2, tv3, tv4, tv5;
        LinearLayout linearLayout;


        public BookkeeperViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            tv4 = itemView.findViewById(R.id.tv4);
            tv5 = itemView.findViewById(R.id.tv5);
            linearLayout = itemView.findViewById(R.id.linearLayout);

        }
    }

}
