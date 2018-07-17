package com.bjfdkj.singlecsl.activity.function;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;


import com.bjfdkj.singlecsl.R;

import java.text.SimpleDateFormat;
import java.util.Date;

//  录像
public class CamearVideoActivity extends AppCompatActivity {
    private static int REQUST_VIDEO = 1;
    private Button btn;
    private Button btn_start;
    private FrameLayout fl;
    private CustomVideoView video;
    private ImageView iv;
    private String sdCard;
    private String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camear_voide);
        initVideo();  //初始化  控件
    }
    private void initVideo() {
        btn = ((Button) findViewById(R.id.btn_recoder));
        btn_start = ((Button) findViewById(R.id.btn_start));
        fl = ((FrameLayout) findViewById(R.id.fl_video));
        video = ((CustomVideoView) findViewById(R.id.video));
        iv = ((ImageView) findViewById(R.id.iv));  //展示拍照
        sdCard = Environment.getExternalStorageDirectory().getPath();   //得到Sd卡目录
        String currenTimeMillis = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date(System.currentTimeMillis()));  //得到系统当前时间
        videoPath = sdCard + "/" + "0000.mp4";  //指定
        MediaController controller = new MediaController(this);  //视频播放器
        video.setMediaController(controller); //视屏得到 播放
        if (video.isPlaying()) {
            iv.setVisibility(View.INVISIBLE);
        }
        video.setPlayPauseListener(new CustomVideoView.PlayPauseListener() {  //视屏点击事件
            @Override
            public void onPlay() {  //播放
                Toast.makeText(CamearVideoActivity.this, "播放", Toast.LENGTH_SHORT).show();
                iv.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onPause() {//暂停
                Toast.makeText(CamearVideoActivity.this, "暂停", Toast.LENGTH_SHORT).show();
                iv.setVisibility(View.VISIBLE);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, videoPath);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, REQUST_VIDEO);
            }
        });
    }
    //获取视频图片
    private Bitmap getVideoBitmap(String videoPath) {
        MediaMetadataRetriever retriever = null;
        try {
            retriever = new MediaMetadataRetriever();
            retriever.setDataSource(videoPath);
            Bitmap bitmap = retriever.getFrameAtTime();
            return bitmap;
        } finally {
            retriever.release();
        }
    }
    //MediaMetadaRetriever  ：提供 一个统一接口 用于 从  一个输入媒体文件中提取的帧和元数据
    // 可以的到视频的第一帧
    //
    private Bitmap getVideoBitmap2(Uri uri) {
        MediaMetadataRetriever retriever = null;
        try {
            retriever = new MediaMetadataRetriever();
            retriever.setDataSource(this, uri);  // 根据地址得到视频 资源
            Bitmap bitmap = retriever.getFrameAtTime();  //  得到视频的每一帧   既  图片  。
            return bitmap;
        } finally {
            retriever.release();  //替换
        }
    }

    //开始视频显示  播放
    public void start(View view) {
        iv.setVisibility(View.INVISIBLE);
        video.start();  //开始视频  播放
    }

    @Override
    //请求码   返回码   数据
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //根据请求码 判断
            if (requestCode == REQUST_VIDEO) {
                fl.setVisibility(View.VISIBLE);
                btn_start.setVisibility(View.VISIBLE);
                Uri uri = data.getData();
                video.setVideoURI(uri);  //传入 视频  地址
//                Bitmap bitmap = getVideoBitmap(videoPath);
                Bitmap bitmap = getVideoBitmap2(uri);
                iv.setImageBitmap(bitmap);
            }
        }
    }


}
