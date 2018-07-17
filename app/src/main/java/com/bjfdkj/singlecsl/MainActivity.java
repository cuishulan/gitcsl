package com.bjfdkj.singlecsl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bjfdkj.singlecsl.activity.function.ExitStorageActivity;
import com.bjfdkj.singlecsl.activity.function.InputStorageActivity;
import com.bjfdkj.singlecsl.base.BaseActivity;
import com.bjfdkj.singlecsl.views.MyPresentation;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.rervision.api.Controler;


/**
 * 主界面
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.Main_top_text)
    TextView MainTopText;
    //入库 -mBookkeeper_input_Storage
    @Bind(R.id.mBookkeeper_input_Storage)
    LinearLayout mBookkeeperInputStorage;
    //档案出库
    @Bind(R.id.mBookkeeper_tow)
    LinearLayout mBookkeeperTow;
    private boolean mIsExit;
    private MyPresentation presentation;  //大屏 显示  以 Dialog 形式 展示  （副屏  ）。
    private Button startOrStop;
    String TAG = "MainActivity";
    private Display display;
    DisplayManager displayManager;
    public boolean onTouchEvent;   //事件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainToptext();
        displayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
//        获得displayManager对象，以便于获得显示屏幕的抽象对象Display
        initShuangping();
    }
    @Override
    protected void onStart() {
        super.onStart();
      //  mThread.start();  //开启线程  执行录像  拍照
    }
    //初始化双屏异显
    private void initShuangping() {
        if (null != presentation && presentation.isShowing()) {
            presentation.dismiss(); //释放
        } else {
            @SuppressLint({"NewApi", "LocalSuppress"})
            Display[] presentationDisplays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
            if (presentationDisplays.length > 0) {
                display = presentationDisplays[0];   //主（小的  ）屏
                presentation = new MyPresentation(MainActivity.this, display);
                presentation.setOnDismissListener(mOnDismissListener);
                //将MyPresentation显示在第二屏幕上
                presentation.show();
                Log.d(TAG, "显示报告显示!");
            } else {
                Log.d(TAG, "没有报告显示!");
            }
        }
    }
    private void initShuangping2() {
        @SuppressLint({"NewApi", "LocalSuppress"})
        Display[] presentationDisplays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        //DisplayManager.DISPLAY_CATEGORY_PRESENTATION
        if (presentationDisplays.length > 0) {
            // If there is more than one suitable presentation display, then we could consider
            // giving the user a choice.  For this example, we simply choose the first display
            // which is the one the system recommends as the preferred presentation display.
            display = presentationDisplays[0];   //主（小的  ）屏
            presentation = new MyPresentation(MainActivity.this, display);
            presentation.setOnDismissListener(mOnDismissListener);
            //将MyPresentation显示在第二屏幕上
            presentation.show();  //如果屏幕显示  弹框形式出现改变样式  连续 行
            //   Log.d(TAG, "返回组件是否正显示!");
            Log.d(TAG, "显示报告显示!");
        } else {
            Log.d(TAG, "没有报告显示!");
        }
    }

    //    监听到当对话框关闭
    private final DialogInterface.OnDismissListener mOnDismissListener =
            new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    MyPresentation presentation = (MyPresentation) dialog;
                    @SuppressLint({"NewApi", "LocalSuppress"})
                    String displayName = presentation.getDisplay().getName();
                    Log.d(TAG + " display", displayName + " was dismissed.");
                }
            };
    private final DisplayManager.DisplayListener mDisplayListener = new DisplayManager.DisplayListener() {
        @Override
        public void onDisplayAdded(int displayId) {
            Log.d(TAG, "Display #" + displayId + " added.");
        }

        @Override
        public void onDisplayChanged(int displayId) {
            Log.d(TAG, "Display #" + displayId + " changed.");
        }

        @Override
        public void onDisplayRemoved(int displayId) {
            Log.d(TAG, "Display #" + displayId + " 删除.");
        }
    };

    @SuppressLint("NewApi")
    @Override
    //暂停
    protected void onStop() {
        if (null != presentation)
            presentation.dismiss();  //暂停时解散
        displayManager.unregisterDisplayListener(mDisplayListener);
        initShuangping(); // 解散 第一个  双屏异显
        super.onStop();
    }
    @Override
    /**
     * 销毁
     */
    protected void onDestroy() {
        super.onDestroy();
        // this.finish();
    }
    //重新创建
    @Override
    protected void onRestart() {
        initShuangping2();  //第二次创建 重新加载 双屏
        super.onRestart();
    }

    @Override
    //事件分发
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //主屏幕的touch screen 名称是gt9xxf_ts
        Log.d(TAG, "dispatchTouchEvent #" + ev.getDevice().getName());
        //设备名称
        if (ev.getDevice().getName().toUpperCase().contains("ILITEK-TP") && null != presentation && presentation.isShowing()) {
            //将事件分发到MyPresentation去
            return presentation.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }
    @OnClick({R.id.mBookkeeper_tow, R.id.mBookkeeper_input_Storage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBookkeeper_input_Storage:
                startTomBookkeeper_input_Storage();
                break;
            case R.id.mBookkeeper_tow:
                startmExit_Storage();
                break;
        }
    }
    /**
     * 跳转到档案入库
     */
    private void startTomBookkeeper_input_Storage() {
        Intent intent = new Intent(MainActivity.this, InputStorageActivity.class);
        startActivity(intent);
    }
    /**
     * 跳转到档案出库
     */
    private void startmExit_Storage() {
        Intent intent = new Intent(MainActivity.this, ExitStorageActivity.class);
        startActivity(intent);
    }

    // 双击返回键退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);  //按下  事件
    }

    //spanableString
    public void MainToptext() {
        SpannableString spannableString = new SpannableString("请选择入库or出库");
        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.2f);
        RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.2f);
        RelativeSizeSpan sizeSpan03 = new RelativeSizeSpan(1.2f);
        RelativeSizeSpan sizeSpan04 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan05 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan06 = new RelativeSizeSpan(1.3f);
        RelativeSizeSpan sizeSpan07 = new RelativeSizeSpan(1.3f);
        RelativeSizeSpan sizeSpan08 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan09 = new RelativeSizeSpan(1.6f);
        spannableString.setSpan(sizeSpan01, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan02, 1, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan03, 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan04, 3, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan05, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan06, 5, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan07, 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan08, 7, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan09, 8, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        MainTopText.setText(spannableString);
    }


    //    -------------------2018----新添----依赖与  api-release', ext: 'aar'------------------
//开启线程  创建双屏  依赖  API-releasse  .aar 文件。  执行双屏   开始录像  录入指纹
    Thread mThread = new Thread() {
        @Override
        public void run() {
            try {
                //crc核对
                Log.d(TAG, "CRCcheck return is crc核对:" + "" + Integer.toHexString(Controler.CRCcheck("6caa040600")));//CRCcheck
                Controler.openDevice(3);//打开指纹仪，参数默认是2
                Thread.sleep(30);
                Controler.startRecord(MainActivity.this); //开始录像

                Log.d(TAG, "开始 Start 录像");
                Log.d(TAG, "setpwd id is:" + "" + Controler.setpwd("1234"));//设置指纹仪密码，一次即可
                Thread.sleep(30);
                Log.d(TAG, "vfypwd id is:" + "" + Controler.vfypwd("1234"));
                //验证指纹仪密码，每次打开都要验证 重新验证
//--------------第一次 录入  指纹
                Log.d(TAG, "请把第一个手指放在设备上");
                Controler.getImage(1);//获取指纹并存储在指纹仪的地址1中.有效地址1-200
                //    Toast.makeText(MainActivity.this, "获取第一次指纹成功", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "第一次获取指纹成功!");
                Thread.sleep(3000);
//--------------第二次 录入  指纹
                Log.d(TAG, "请把第二个手指放在设备上");
                Controler.getImage(100);//获取指纹并存储在指纹仪的地址100中.有效地址1-200

                Log.d(TAG, "第二次获取指纹成功!");
                Thread.sleep(3000);
//--------------第三次 录入  指纹

                Log.d(TAG, "请把第三个手指放在设备上");
                Controler.getImage(101);//获取指纹并存储在指纹仪的地址101中.有效地址1-200
                Log.d(TAG, "第三次获取指纹成功!");
                Thread.sleep(3000);
//----------------------
                //匹配效验
                Log.d(TAG, "请把你想要匹配的手指放进去");
                int i = Controler.match();//获取指纹并与存储的指纹匹配，返回存储的指纹地址

                Log.d(TAG, "match id is:" + "" + i);
                Controler.closeDevice();//关闭指纹仪
                Log.d(TAG, "指纹仪关闭");
                Log.d(TAG, "readRFID is:" + Controler.readRFID());//读取rfid卡号
                Log.d(TAG, "readTemp is:" +
                        (float) Integer.parseInt(Controler.readFile(Controler.TEMP_PATH, 5)) / 1000);//读取温度

                Log.d(TAG, "readHumidity is:" +
                        (float) Integer.parseInt(Controler.readFile(Controler.HUMIDITY_PATH, 5)) / 1000);//读取湿度
                Log.d(TAG, "readLight is:" + Controler.readFile(Controler.LIGHT_PATH, 5));//读取光线强度

                Controler.writeGpio("PE4", "0");//关闭Gpio口PE4    //写入
                Controler.writeGpio("PE5", "1");//打开Gpio口PE5

                Controler.stopRecord(MainActivity.this);//停止录像
                Log.d(TAG, "暂停  Stop 录像");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.run();
        }
    };

//    -----------------------------------------------------当前END -----------------------------------------
}
