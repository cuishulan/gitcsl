package com.bjfdkj.singlecsl.views;

import android.annotation.SuppressLint;
import android.app.Presentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.bjfdkj.singlecsl.MainActivity;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.activity.function.BookkeeperActivity;
import com.bjfdkj.singlecsl.activity.function.OrganizationalMActivity;
import com.bjfdkj.singlecsl.activity.function.QueryActivity;
import com.bjfdkj.singlecsl.activity.function.SetActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by cuishu on 2018/6/5.  双屏
 */
//  大屏

//屏蔽android lint错误   @SuppressLint("NewApi") 减少使用
@SuppressLint("NewApi")
public class MyPresentation extends Presentation {

    MainActivity mainActivity;
    //  档案管理
    @Bind(R.id.mBookkeeper)
    LinearLayout mBookkeeper_manage;
    //档案查询
    @Bind(R.id.mBookkeeper_throw)
    LinearLayout mBookkeeperThrow;
    //管理维护
    @Bind(R.id.mBookkeeper_weihu)
    LinearLayout mBookkeeperWeihu;
    //系统设置
    @Bind(R.id.mBookkeeper_set)
    LinearLayout mBookkeeperSet;
    private final static String TAG = "我的描述";
    private float mScaleX, mScaleY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation);
        ButterKnife.bind(this);
        //Log.d(TAG, "创建");
    }
    @OnClick({R.id.mBookkeeper, R.id.mBookkeeper_throw, R.id.mBookkeeper_set, R.id.mBookkeeper_weihu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBookkeeper:
                startToBokkeeper(); //档案管理
                //隐藏到后台
                break;
            case R.id.mBookkeeper_throw:
                startmQuery();
                break;
            case R.id.mBookkeeper_set:
                startToSet();
                break;
            case R.id.mBookkeeper_weihu:
                startToWeiHu();
                break;
        }
    }
    //跳转 ，显示 在大屏   小屏  处于 暂停

    //跳转到组织管理界面
    private void startToWeiHu() {
        Intent intent = new Intent(getContext().getApplicationContext(), OrganizationalMActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().getApplicationContext().startActivity(intent);
    }
    /**
     * 跳转到档案管理
     */
    private void startToBokkeeper() {
        Intent intent = new Intent(getContext(), BookkeeperActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().getApplicationContext().startActivity(intent);
    }
    //   跳转到系统设置
    private void startToSet() {
        Intent intent = new Intent(getContext(), SetActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     //flag activity new  task  设置启动方式
        getContext().getApplicationContext().startActivity(intent);
    }
    //   跳转到档案查询
    private void startmQuery() {
        Intent intent = new Intent(getContext(), QueryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().getApplicationContext().startActivity(intent);
    }

    public MyPresentation(Context outerContext, Display display) {
        super(outerContext, display);
        //获取屏幕宽高
        mScaleX = (float) display.getWidth() / 1920;
        mScaleY = (float) display.getHeight() / 1080;
    }

    @Override
    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        Log.d(TAG, "dispatchKeyEvent:" + event.toString());
        return super.dispatchKeyEvent(event);
    }

    //分发
    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        float x, y;
        x = ev.getX() * mScaleX;
        y = ev.getY() * mScaleY;
        //ev.setLocation(x, y);
        Log.d(TAG, "dispatchTouchEvent:" + ev.toString());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (null != mainActivity) {
            mainActivity.closeContextMenu();
        }
        super.onStop();
    }

   // --------------------------

}
