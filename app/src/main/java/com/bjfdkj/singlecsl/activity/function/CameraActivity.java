package com.bjfdkj.singlecsl.activity.function;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.bjfdkj.singlecsl.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.ButterKnife;


/**
 * Created by cuishu on 2018/6/19.
 * 相机  拍摄   检测系统拍照设备
 */
public class CameraActivity extends Activity implements View.OnClickListener {
    private Button btnOpenCamera, btnSavePhoto;
    private ImageView ivShowPicture; //是否显示图片照片
    private static int REQUEST_CAMERA_1 = 1;
    private static int REQUEST_CAMERA_2 = 2;
    private String mFilePath;  //路径

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        // 初始化控件
        init();
        // 控件绑定点击事件
        bindClick();
    }

    private void init() {
        btnOpenCamera = (Button) findViewById(R.id.btnOpenCamera);  //打开相机
        btnSavePhoto = (Button) findViewById(R.id.btnSavePhoto);  //保存
        ivShowPicture = (ImageView) findViewById(R.id.ivShowPicture);  //图片暂时
        mFilePath = Environment.getExternalStorageDirectory().getPath();// 获取SD卡路径
        mFilePath = mFilePath + "/" + "temp.png";// 指定路径
    }

    // 控件绑定点击事件
    private void bindClick() {
        btnOpenCamera.setOnClickListener(this);
        btnSavePhoto.setOnClickListener(this);  //保存图片
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOpenCamera:
                // 拍照并显示图片
                openCamera_1();
                break;
            case R.id.btnSavePhoto:
                // 拍照后存储并显示图片
                openCamera_2();
                break;
            default:
                break;
        }
    }

    // 拍照并显示图片
    private void openCamera_1() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
        startActivityForResult(intent, REQUEST_CAMERA_1);
    }

    // 调用第二次相机拍照  储存
    private void openCamera_2() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
        Uri photoUri = Uri.fromFile(new File(mFilePath)); // 传递路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);// 更改系统默认存储路径
        startActivityForResult(intent, REQUEST_CAMERA_2);
    }

    @Override
    //请求码  结果码   数据。
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回数据
            if (requestCode == REQUEST_CAMERA_1) { // 判断请求码是否为REQUEST_CAMERA,如果是代表是这个页面传过去的，需要进行获取
                Bundle bundle = data.getExtras(); // 从data中取出传递回来缩略图的信息，图片质量差，适合传递小图片
                Bitmap bitmap = (Bitmap) bundle.get("data"); // 将data中的信息流解析为Bitmap类型
                ivShowPicture.setImageBitmap(bitmap);// 显示图片
            } else if (requestCode == REQUEST_CAMERA_2) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(mFilePath); // 根据路径获取数据
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    ivShowPicture.setImageBitmap(bitmap);// 显示图片
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();// 关闭流
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}




