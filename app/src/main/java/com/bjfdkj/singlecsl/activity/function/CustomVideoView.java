package com.bjfdkj.singlecsl.activity.function;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by cuishu on 2018/6/20.
 * 自定义VideoView 相机   录像
 */
public class CustomVideoView extends VideoView {
    //播放暂停  集合 
    private PlayPauseListener listener;
    public CustomVideoView(Context context) {
        super(context);
    }
    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setPlayPauseListener(PlayPauseListener listener){
        this.listener = listener;
    }
    //暂停
    @Override
    public void pause() {
        super.pause();
        if (listener!=null){
            listener.onPause();
        }
    }
    //录像跳转  占用  是否释放占用资源。
    //开始  录像
    @Override
    public void start() {
        super.start();
        //判断  播放集合是否为空
        if (listener!=null){
            listener.onPlay();
        }
    }
    //播放暂停 集合
    interface PlayPauseListener{
        void onPlay();
        void onPause();
    }
}
