package com.bjfdkj.singlecsl.manager_seriaPort_device;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;


import com.bjfdkj.singlecsl.listener.OnOpenSerialPortListener;
import com.bjfdkj.singlecsl.listener.OnSerialPortDataListener;
import com.bjfdkj.singlecsl.thread.SerialPortReadThread;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by cuishu on 2018/6/25.
 * <p>  缺少  Cpp  文件   代码集成
 * <p>
 * 管理  manager   .c  .h  文件
 */

public class SerialPortManager extends SerialPort {

    //声明
    private static final String TAG = SerialPortManager.class.getSimpleName();
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;
    private FileDescriptor mFd;  //文件描述
    private OnOpenSerialPortListener mOnOpenSerialPortListener;  //打开串口
    private OnSerialPortDataListener mOnSerialPortDataListener;  //串口数据集合
    private HandlerThread mSendingHandlerThread;  //线程
    private Handler mSendingHandler; //线程通信
    private SerialPortReadThread mSerialPortReadThread;  //串口读取线程

//    打开串口   device:串口设备  baudRate: 波特率    是否成功打开
    public boolean openSerialPort(File device, int baudRate) {

        //打印串口  波特率
        Log.i(TAG, "openSerialPort: " + String.format("打开串口 %s  波特率 %s", device.getPath(), baudRate));

        if (!device.canRead() || !device.canWrite()) {
            boolean chmod777 = chmod777(device);
            if (!chmod777) {
                if (null != mOnOpenSerialPortListener) {
                    Log.i(TAG, "openSerialPort :没有读写权限");
                    mOnOpenSerialPortListener.onFail(device,
                            OnOpenSerialPortListener.Status.NO_READ_WRITE_PERMISSION);
                }
                return false;
            }
        }
        try {
            mFd = open(device.getAbsolutePath(), baudRate, 0);
            mFileInputStream = new FileInputStream(mFd);
            mFileOutputStream = new FileOutputStream(mFd);
            Log.i(TAG, "openSerialPort :串口以打开" + mFd);
            if (null != mOnOpenSerialPortListener) {
                mOnOpenSerialPortListener.onSuccess(device); //成功
            }
            startSendThread();  //开启发送消息的线程  send
            startReadThread(); //接收消息的线程   Read
        } catch (Exception e) {
            e.printStackTrace();
            if (null != mOnOpenSerialPortListener) {
                mOnOpenSerialPortListener.onFail(device,
                        OnOpenSerialPortListener.Status.OPEN_FAIL);
            }
        }
        return false;
    }
    //关闭
    public void closeSerialPort() {
        if (null != mFd) {
            close();
            mFd = null;  //读取Rfd :为空
        }
        // 停止发送消息的线程
        stopSendThread();
        // 停止接收消息的线程
        stopReadThread();
        if (null != mFileInputStream) {
            try {
                mFileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mFileInputStream = null;
        }
        if (null != mFileOutputStream) {
            try {
                mFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mFileOutputStream = null;
        }
        mOnOpenSerialPortListener = null;
        mOnSerialPortDataListener = null;
    }
    /**
     * 添加打开串口监听
     * @param listener listener
     * @return SerialPortManager
     */
    public SerialPortManager setOnOpenSerialPortListener(OnOpenSerialPortListener listener) {
        mOnOpenSerialPortListener = listener;
        return this;
    }
    /**
     * 添加数据通信监听
     * @param listener listener
     * @return SerialPortManager
     */
    public SerialPortManager setOnSerialPortDataListener(OnSerialPortDataListener listener) {
        mOnSerialPortDataListener = listener;
        return this;
    }
    /**
     * 开启发送消息的线程
     */
    private void startSendThread() {
        // 开启发送消息的线程
        mSendingHandlerThread = new HandlerThread("mSendingHandlerThread");
        mSendingHandlerThread.start();
        // Handler
        mSendingHandler = new Handler(mSendingHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                byte[] sendBytes = (byte[]) msg.obj;
                if (null != mFileOutputStream && null != sendBytes && 0 < sendBytes.length) {
                    try {
                        mFileOutputStream.write(sendBytes);
                        if (null != mOnSerialPortDataListener) {
                            mOnSerialPortDataListener.onDataSent(sendBytes);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
    /**
     * 停止发送消息线程
     */
    private void stopSendThread() {
        mSendingHandler = null;
        if (null != mSendingHandlerThread) {
            mSendingHandlerThread.interrupt();  //中断
            mSendingHandlerThread.quit();  //离开
            mSendingHandlerThread = null;
        }
    }
    /**
     * 开启接收消息的线程
     */
    private void startReadThread() {
        mSerialPortReadThread = new SerialPortReadThread(mFileInputStream) {
            @Override
            public void onDataReceived(byte[] bytes) {
                if (null != mOnSerialPortDataListener) {
                    mOnSerialPortDataListener.onDataReceived(bytes);
                }
            }
        };
        mSerialPortReadThread.start();  //开启  线程
    }
    /**
     * 停止接收消息的线程
     */
    private void stopReadThread() {
        if (null != mSerialPortReadThread) {
            mSerialPortReadThread.release();  //释放
        }
    }
    /**
     * 发送数据
     * @param sendBytes 发送数据
     * @return 发送是否成功
     */
    public boolean sendBytes(byte[] sendBytes) {
        if (null != mFd && null != mFileInputStream && null != mFileOutputStream) {
            if (null != mSendingHandler) {
                Message message = Message.obtain();
                //管理  message
                message.obj = sendBytes;
                return mSendingHandler.sendMessage(message);
            }
        }
        return false;
    }
}
