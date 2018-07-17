package com.bjfdkj.singlecsl;

import android.app.Application;

import com.vondear.rxtools.RxTool;


/**
 * Created by Administrator on 2018/5/30 0030.
 */

public class MYssAppitacon extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRxtools();
    }
    private void initRxtools() {
        RxTool.init(getApplicationContext());
    }
}
