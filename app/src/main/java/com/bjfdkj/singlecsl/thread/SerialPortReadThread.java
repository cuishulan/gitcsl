package com.bjfdkj.singlecsl.thread;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cuishu on 2018/6/25.
 * 串口 消息读取线程
 */
public abstract class SerialPortReadThread extends Thread {

    private static final String TAG = SerialPortReadThread.class.getSimpleName();
    public abstract void onDataReceived(byte[] bytes);  //数据接收
    private InputStream mInputStream;
    private byte[] mReadBuffer;

    public SerialPortReadThread(InputStream inputStream) {
        mInputStream = inputStream;
        mReadBuffer = new byte[1024];
    }
    @Override
    public void run() {
        super.run();
        while (!isInterrupted()) {
            try {
                if (null == mInputStream) {
                    return;
                }
                Log.e(TAG, "run");
                int size = mInputStream.read(mReadBuffer);
                if (-1 == size || 0 >= size) {
                    //判断  数据个数  .siez
                    return ;
                }
                byte[] readBytes = new byte[size];
                System.arraycopy(mReadBuffer, 0, readBytes, 0, size);
                Log.i(TAG, "返回  读取  Bytes  ,return  readBytes=" + new String(readBytes));
                onDataReceived(readBytes);

                //操作  流文件 event Log gradler  Console
                /*   syter.exit(0);     // EVENT  lOCAHYNHC
                * Syttem.out.print("----开始输出数据集--"+DataListAdaper);*/
            } catch (IOException e) {
                //开始捕获
                e.printStackTrace();
                return;
            }
        }
    }
    //加锁开启  传输局
    public synchronized void start() {
        super.start();
    }

    //关闭线程
    public void release() {
        interrupt();
        if (null != mInputStream) {
            try {
                mInputStream.close();
                mInputStream = null;
                //填充功能
                Service service=new Service() {
                    @Nullable
                    @Override
                    public IBinder onBind(Intent intent) {
                        return null;
                    }
                };
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
